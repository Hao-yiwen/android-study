package com.example.chapter04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ActReceiveActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_receive);
        textView = findViewById(R.id.tv_receive);
        // 从上一个页面传来的意图中获取快递包裹
        Bundle extras = getIntent().getExtras();
        String requestTime = extras.getString("request_time");
        String requestContent = extras.getString("request_content");
        String desc = String.format("收到请求时间：%s\n请求内容：%s", requestTime, requestContent);
        textView.setText(desc);
    }
}