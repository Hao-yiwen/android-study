package com.example.chapter06;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;

import com.example.chapter06.util.FileUtil;
import com.example.chapter06.util.ToastUtil;

import java.io.File;

public class ImageWriteActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_image;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_write);
        iv_image = findViewById(R.id.iv_image);

        findViewById(R.id.btn_read).setOnClickListener(this);
        findViewById(R.id.btn_save).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_save) {
            String fileName = System.currentTimeMillis() + ".png";
            path = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + File.separatorChar + fileName;
            Bitmap b1 = BitmapFactory.decodeResource(getResources(), R.drawable.ting2);
            FileUtil.saveImage(path, b1);
            ToastUtil.showMsg(this, "保存成功");
        } else {
//            Bitmap b2 = FileUtil.openImage(path);
//            iv_image.setImageBitmap(b2);
//            Bitmap b2 = BitmapFactory.decodeFile(path);
//            iv_image.setImageBitmap(b2);
            iv_image.setImageURI(Uri.parse(path));
        }
    }
}