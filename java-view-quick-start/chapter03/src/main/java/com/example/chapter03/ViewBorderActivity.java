package com.example.chapter03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chapter03.utils.Utils;

public class ViewBorderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_border);
        TextView tv_code = findViewById(R.id.tv_code);
        // 获取布局参数
        ViewGroup.LayoutParams layoutParams = tv_code.getLayoutParams();
        // 修改布局参数中的宽度数值，默认为px
        layoutParams.width = Utils.dip2px(this, 300);
        tv_code.setLayoutParams(layoutParams);
    }
}