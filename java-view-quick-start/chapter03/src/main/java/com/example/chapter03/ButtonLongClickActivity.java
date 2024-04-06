package com.example.chapter03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ButtonLongClickActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_long_click);
        TextView textView = findViewById(R.id.tv_result);
        Button button = findViewById(R.id.btn_click);
        button.setOnLongClickListener(v -> {
            textView.setText("按钮被长按了");
            return true;
        });
    }
}