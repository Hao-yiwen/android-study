package com.example.chapter03;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TextColorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_color);
        TextView tv_color_system = findViewById(R.id.tv_color_system);
        // 设置成系统自带绿色
        tv_color_system.setTextColor(Color.GREEN);

        TextView tv_color_eight = findViewById(R.id.tv_color_eight);
        tv_color_eight.setTextColor(0xffff0000);

        TextView tv_color_six = findViewById(R.id.tv_color_six);
        tv_color_six.setTextColor(0x0000ff00);

        TextView tv_code_background = findViewById(R.id.tv_code_background);
        tv_code_background.setBackgroundColor(0xff00ff00);
    }
}