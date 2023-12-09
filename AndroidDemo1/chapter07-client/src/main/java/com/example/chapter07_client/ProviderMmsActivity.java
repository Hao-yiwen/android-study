package com.example.chapter07_client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.example.chapter07_client.entity.ImageInfo;
import com.example.chapter07_client.util.FileUtil;
import com.example.chapter07_client.util.PermissionUtil;
import com.example.chapter07_client.util.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProviderMmsActivity extends AppCompatActivity {
    private static final String[] PERMISSIONS = new String[]{
            "android.permission.READ_MEDIA_IMAGES",
    };

    private static final int READ_EXTERNAL_STORAGE = 1;
    private List<ImageInfo> imageInfoList = new ArrayList<>();
    private GridLayout gl_appendix;
    private EditText et_title;
    private EditText et_message;
    private EditText et_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_mms);
        gl_appendix = findViewById(R.id.gl_appendix);
        et_title = findViewById(R.id.et_title);
        et_phone = findViewById(R.id.et_phone);
        et_message = findViewById(R.id.et_message);

        // 手动让mediastore扫描入库
        MediaScannerConnection.scanFile(this, new String[]{Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()}, null, null);

        Log.d("ning", "oooooooooooooo");
        loadImageList();
        showImageGrid();
        if (PermissionUtil.checkPermission(this, PERMISSIONS, READ_EXTERNAL_STORAGE)) {
            Log.d("ning", "lalallalalal");

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == READ_EXTERNAL_STORAGE && PermissionUtil.checkGrant(grantResults)) {
            loadImageList();
            showImageGrid();
        }
    }

    private void showImageGrid() {
        gl_appendix.removeAllViews();
        for (ImageInfo imageInfo : imageInfoList) {
            ImageView iv = new ImageView(this);
            Bitmap bitmap = BitmapFactory.decodeFile(imageInfo.path);
            iv.setImageBitmap(bitmap);
            iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
            int px1 = Utils.dip2px(this, 110);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(px1, px1);
            iv.setLayoutParams(params);
            int padding = Utils.dip2px(this, 5);
            iv.setPadding(padding, padding, padding, padding);
            iv.setOnClickListener(v -> {
                sendMms(imageInfo.path);
            });
            gl_appendix.addView(iv);
        }
    }

    @SuppressLint("Range")
    private void loadImageList() {
        String[] columns = new String[]{
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.TITLE,
                MediaStore.Images.Media.SIZE,
                MediaStore.Images.Media.DATA
        };
        // mediastore
        Cursor cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                columns,
                "_size < 307200",
                null,
                "_size DESC"
        );
        int count = 0;
        if (cursor != null) {
            while (cursor.moveToNext() && count < 6) {
                ImageInfo imageInfo = new ImageInfo();
                imageInfo.id = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media._ID));
                imageInfo.name = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.TITLE));
                imageInfo.size = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media.SIZE));
                imageInfo.path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                if(FileUtil.checkFileUri(this, imageInfo.path)) {
                    count++;
                    imageInfoList.add(imageInfo);
                }
                Log.d("ning", "imageInfo: " + imageInfo);
            }
            cursor.close();
        }
    }

    private void sendMms(String path) {
        Uri uri = Uri.parse(path);
        // 兼容7.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(this, getString(R.string.file_provider), new File(path));
            Log.d("ning", "uri: " + uri);
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra("address", et_phone.getText().toString());
        intent.putExtra("subject", et_title.getText().toString());
        intent.putExtra("sms_body", et_message.getText().toString());
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setType("image/*");
        startActivity(intent);
    }
}