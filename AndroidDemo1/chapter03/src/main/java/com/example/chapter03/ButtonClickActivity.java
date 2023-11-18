package com.example.chapter03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ButtonClickActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_click);
        tv_result = findViewById(R.id.tv_result1);
        Button button = findViewById(R.id.btn_click);
        button.setOnClickListener(new MyViewOnClickListener(tv_result));
        Button button1 = findViewById(R.id.btn_click_public);
        button1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_click_public) {
            tv_result.setText("啦啦啦啦");
        }
    }

    static class MyViewOnClickListener implements View.OnClickListener {
        private final TextView tv_result;

        public MyViewOnClickListener(TextView tvResult) {
            this.tv_result = tvResult;
        }

        @Override
        public void onClick(View v) {
            tv_result.setText("按钮被点击了");
        }
    }
}