package com.yiwen.recyclerviewtest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.orhanobut.logger.Logger;

import io.github.haoyiwen.test.core.activity.BaseActivity;
import io.github.haoyiwen.test.core.listener.PermissionListener;

public class SimpleCursorAdapterActivity extends BaseActivity {
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_simple_cursor;
    }

    @Override
    protected String setTitle() {
        return "simple_cursor";
    }

    @Override
    public void initView() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            loadContacts();
        }
    }

    private void loadContacts() {
        ListView ll = findViewById(R.id.simple_list);
        String[] fromColumns = {ContactsContract.Data.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
        int[] toViews = {R.id.text_display_name, R.id.text_phone};
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        Logger.i("SimpleCursorAdapterActivity: " + "Cursor has " + cursor.getCount() + " items");
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.fragment_simple_cursor, cursor, fromColumns, toViews, 0);
        ll.setAdapter(adapter);
    }


    @Override
    public void requestRuntimePermission(String[] permissions, PermissionListener permissionListener) {
        super.requestRuntimePermission(permissions, permissionListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadContacts();
            }
        }
    }
}
