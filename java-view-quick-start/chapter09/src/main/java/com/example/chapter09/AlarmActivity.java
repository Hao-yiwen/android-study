package com.example.chapter09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.example.chapter09.receiver.AlarmReceiver;

public class AlarmActivity extends AppCompatActivity implements View.OnClickListener {

    private AlarmReceiver alarmReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        findViewById(R.id.btn_setting).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        alarmReceiver.sendAlarm();
    }

    @Override
    protected void onStart() {
        super.onStart();
        alarmReceiver = new AlarmReceiver(getApplicationContext());
        IntentFilter filter = new IntentFilter("com.example.chapter09.ALARM_ACTION");
        registerReceiver(alarmReceiver, filter, RECEIVER_EXPORTED);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(alarmReceiver);
    }
}