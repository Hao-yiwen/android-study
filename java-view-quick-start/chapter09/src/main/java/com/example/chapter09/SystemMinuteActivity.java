package com.example.chapter09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.example.chapter09.receiver.TimerReceiver;

public class SystemMinuteActivity extends AppCompatActivity {

    private TimerReceiver timerReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_minute);
    }

    @Override
    protected void onStart() {
        super.onStart();
        timerReceiver = new TimerReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_TIME_TICK);
        registerReceiver(timerReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(timerReceiver);
    }
}