package com.example.chapter09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BroadStaticActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_static);
        findViewById(R.id.btn_send_standard).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent("com.example.chapter09.shock");
        String fullName = "com.example.chapter09.receiver.ShockReceiver";
        ComponentName componentName = new ComponentName(this, fullName);
        intent.setComponent(componentName); // 清除组件信息，让系统自动匹配
        sendBroadcast(intent);
    }
}