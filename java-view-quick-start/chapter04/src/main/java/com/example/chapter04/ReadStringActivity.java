package com.example.chapter04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ReadStringActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_string);
        TextView tv_resource = findViewById(R.id.tv_resource);
        String weather_str = getString(R.string.weather_str);
        tv_resource.setText(weather_str);
    }
}