package com.example.chapter03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ButtonEableActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_enable;
    private Button btn_disable;
    private Button btn_test;

    TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_eable);
        btn_enable = findViewById(R.id.btn_enable);
        btn_disable = findViewById(R.id.btn_disable);
        btn_test = findViewById(R.id.btn_test);
        tv_result = findViewById(R.id.tv_result);

        btn_enable.setOnClickListener(this);
        btn_disable.setOnClickListener(this);
        btn_test.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btn_enable.getId()) {
            btn_test.setEnabled(true);
        } else if (v.getId() == btn_disable.getId()) {
            btn_test.setEnabled(false);
        } else if (v.getId() == btn_test.getId()) {
            tv_result.setText("按钮被点击了");
        }
    }
}