package com.yiwen.java_view_other.delegate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.yiwen.java_view_other.R;

import io.github.haoyiwen.test.core.activity.BaseActivity;

public class DelegateActivity extends BaseActivity implements DownloadListener, View.OnClickListener {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_delegate;
    }

    @Override
    public void initView() {
        Button button = findViewById(R.id.btn_delegate);
        button.setOnClickListener(this);
    }

    @Override
    protected String setTitle() {
        return "委托模式测试";
    }

    @Override
    public void onDownloadComplete(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        Downlodaer downlodaer = new Downlodaer(this);
        downlodaer.download();
    }
}