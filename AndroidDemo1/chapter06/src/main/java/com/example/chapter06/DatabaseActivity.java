package com.example.chapter06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DatabaseActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        tv_result = findViewById(R.id.tv_result);
        findViewById(R.id.btn_create_database).setOnClickListener(this);
        findViewById(R.id.btn_delete_database).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_create_database) {
            // 创建或打开数据库
            openOrCreateDatabase()
        } else {

        }
    }
}