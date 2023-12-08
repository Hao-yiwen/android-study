package com.example.chapter07_client;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.chapter07_client.entity.User;
import com.example.chapter07_client.util.ToastUtil;

public class ContentWriteActivity extends AppCompatActivity implements View.OnClickListener {

    private CheckBox ck_marry;
    private EditText et_weight;
    private EditText et_age;
    private EditText et_name;
    private EditText et_height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_write);

        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_height = findViewById(R.id.et_height);
        et_weight = findViewById(R.id.et_weight);
        ck_marry = findViewById(R.id.ck_marry);

        findViewById(R.id.btn_save).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_read).setOnClickListener(this);
    }

    @SuppressLint("Range")
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_save) {
            ContentValues values = new ContentValues();
            values.put(UserInfoContent.USER_NAME, et_name.getText().toString());
            values.put(UserInfoContent.USER_AGE, Integer.parseInt(et_age.getText().toString()));
            values.put(UserInfoContent.USER_HEIGHT, Integer.parseInt(et_height.getText().toString()));
            values.put(UserInfoContent.USER_WEIGHT, Float.parseFloat(et_weight.getText().toString()));
            values.put(UserInfoContent.USER_MARRIED, ck_marry.isChecked());
            values.put(UserInfoContent.USER_UPDATE_TIME, System.currentTimeMillis());
            getContentResolver().insert(UserInfoContent.CONTENT_URI, values);
            ToastUtil.showMsg(this, "数据已写入");
        } else if (v.getId() == R.id.btn_delete) {
            Uri uri = ContentUris.withAppendedId(UserInfoContent.CONTENT_URI, 2);
            int count = getContentResolver().delete(uri, "name=?", new String[]{et_name.getText().toString()});
            if (count > 0) {
                ToastUtil.showMsg(this, "数据已删除");
            }
        } else if (v.getId() == R.id.btn_read) {
            Cursor cursor = getContentResolver().query(UserInfoContent.CONTENT_URI, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    User info = new User();
                    info.id = cursor.getInt(cursor.getColumnIndex("_id"));
                    info.name = cursor.getString(cursor.getColumnIndex("name"));
                    info.age = cursor.getInt(cursor.getColumnIndex("age"));
                    info.height = cursor.getInt(cursor.getColumnIndex("height"));
                    info.weight = cursor.getFloat(cursor.getColumnIndex("weight"));
                    info.married = cursor.getInt(cursor.getColumnIndex("married")) == 1;
                    Log.d("ning", "query user info: " + info.toString());
                }
                cursor.close();
            }
        }
    }
}