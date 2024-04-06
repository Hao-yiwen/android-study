package com.example.chapter09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.example.chapter09.receiver.StandardReceiver;

public class BroadStandardActivity extends AppCompatActivity implements View.OnClickListener {

    private StandardReceiver standardReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_standard);

        findViewById(R.id.btn_send_standard).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // 发送标准广播
        Intent intent = new Intent("com.example.chapter09.standard");
        sendBroadcast(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        standardReceiver = new StandardReceiver();
        IntentFilter filter = new IntentFilter("com.example.chapter09.standard");
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY); // 设置优先级，如果需要的话
        registerReceiver(standardReceiver, filter, RECEIVER_EXPORTED); // 使用四参数版本的 registerReceiver
    }


    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(standardReceiver);
    }
}