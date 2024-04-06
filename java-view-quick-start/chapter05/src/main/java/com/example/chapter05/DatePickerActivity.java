package com.example.chapter05;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class DatePickerActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private DatePicker date_picker;
    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);
        findViewById(R.id.btn_ok).setOnClickListener(this);
        date_picker = findViewById(R.id.date_picker);
        tv_result = findViewById(R.id.tv_result);
        findViewById(R.id.btn_dake).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_ok){
            String desc = String.format("您选择的日期是%d年%d月%d日", date_picker.getYear(), date_picker.getMonth()+1, date_picker.getDayOfMonth());
            tv_result.setText(desc);
        } else if(v.getId() == R.id.btn_dake){
//            Calendar calendar = Calendar.getInstance();
//            calendar.get(Calendar.YEAR);
//            calendar.get(Calendar.MONTH);
//            calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, 2090, 5, 12);
            datePickerDialog.show();
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String desc = String.format("您选择的日期是%d年%d月%d日", year, month+1, dayOfMonth);
        tv_result.setText(desc);
    }
}