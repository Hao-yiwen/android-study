package com.example.chapter06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.chapter06.util.FileUtil;
import com.example.chapter06.util.ToastUtil;

import java.io.File;

public class FileWriteActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_name;
    private EditText et_age;
    private EditText et_weight;
    private EditText et_height;
    private CheckBox ck_marry;
    private String path;
    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_write);

        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_weight = findViewById(R.id.et_weight);
        et_height = findViewById(R.id.et_height);
        ck_marry = findViewById(R.id.ck_marry);
        tv_result = findViewById(R.id.tv_result);

        findViewById(R.id.btn_save).setOnClickListener(this);
        findViewById(R.id.btn_read).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_save) {
            String name = et_name.getText().toString();
            int age = Integer.parseInt(et_age.getText().toString());
            float weight = Float.parseFloat(et_weight.getText().toString());
            float height = Float.parseFloat(et_height.getText().toString());
            boolean marry = ck_marry.isChecked();

            StringBuilder sb = new StringBuilder();
            sb.append("姓名：").append(name).append("\n");
            sb.append("年龄：").append(age).append("\n");
            sb.append("体重：").append(weight).append("\n");
            sb.append("身高：").append(height).append("\n");
            sb.append("已婚：").append(marry ? "是" : "否").append("\n");
            String directory = null;
            String fileName = System.currentTimeMillis() + ".txt";
            // 外部存储的私有控件
            directory = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString();
            // 外部存储的公有空间
            directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
            // 内部私有控件
            directory = getFilesDir().toString();
            path = directory + File.separatorChar + fileName;
            Log.d("FileWriteActivity", "path=" + path);
            FileUtil.saveText(path, sb.toString());
            ToastUtil.showMsg(this, "保存成功");
        } else if (v.getId() == R.id.btn_read) {
            String s = FileUtil.openText(path);
            tv_result.setText(s);
        }
    }
}