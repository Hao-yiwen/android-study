package com.example.javaviewtest;

import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import io.github.haoyiwen.test.core.activity.BaseActivity;

public class BaseLoadingTestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        showLoading();
//        showErrorPage("test");

        new Handler().postDelayed(() -> {
            hideLoading();
        }, 2000);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_base_loading_test;
    }

    @Override
    protected String setTitle() {
        return "BaseLoadingTestActivity";
    }
}