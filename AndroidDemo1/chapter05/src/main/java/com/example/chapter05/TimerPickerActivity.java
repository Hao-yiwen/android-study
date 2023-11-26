package com.example.chapter05;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.util.Calendar;

public class TimerPickerActivity extends AppCompatActivity implements View.OnClickListener, TimePickerDialog.OnTimeSetListener {

    private TimePicker tp_time;
    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_picker);
        findViewById(R.id.btn_ok).setOnClickListener(this);
        findViewById(R.id.btn_dake).setOnClickListener(this);
        tp_time = findViewById(R.id.tp_time);
        tp_time.setIs24HourView(true);
        tv_result = findViewById(R.id.tv_result);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_ok) {
            String desc = String.format("您选择的时间是%d时%d分", tp_time.getHour(), tp_time.getMinute());
            tv_result.setText(desc);
        } else if (v.getId() == R.id.btn_dake) {
            Calendar calendar = Calendar.getInstance();
            TimePickerDialog dialog = new TimePickerDialog(this, com.google.android.material.R.style.Theme_MaterialComponents_Dialog,this, calendar.HOUR_OF_DAY, calendar.MINUTE, true);
            dialog.show();
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String desc = String.format("您选择的时间是%d时%d分", hourOfDay, minute);
        tv_result.setText(desc);
    }
}