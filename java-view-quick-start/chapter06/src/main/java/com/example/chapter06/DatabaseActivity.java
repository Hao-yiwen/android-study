package com.example.chapter06;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DatabaseActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_result;
    private String mDatabaseName;
    private String desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        tv_result = findViewById(R.id.tv_result);
        findViewById(R.id.btn_create_database).setOnClickListener(this);
        findViewById(R.id.btn_delete_database).setOnClickListener(this);
        mDatabaseName = getFilesDir() + "/test.db";
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_create_database) {
            // 创建或打开数据库
            SQLiteDatabase db = openOrCreateDatabase(mDatabaseName, MODE_PRIVATE, null);
            desc = String.format("数据库%s创建%s", db.getPath(), (db != null) ? "成功" : "失败");
            tv_result.setText(desc);
        } else {
            // 删除数据库
            boolean result = deleteDatabase(mDatabaseName);
            desc = String.format("数据库%s删除%s", mDatabaseName, result ? "成功" : "失败");
            tv_result.setText(desc);
        }
    }
}