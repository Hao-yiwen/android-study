package com.yiwen.java_view_other;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.nukc.stateview.StateView;

import io.github.haoyiwen.test.core.activity.BaseActivity;

public class StateViewActivity extends BaseActivity implements View.OnClickListener {

    private StateView stateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        stateView = StateView.inject(findViewById(R.id.content_id));

        stateView.setLoadingResource(R.layout.view_loading);

        Button btnloading = findViewById(R.id.btn_loading);
        Button btnError = findViewById(R.id.btn_error);
        Button btnEmpty = findViewById(R.id.btn_empty);
        Button btnContent = findViewById(R.id.btn_content);

        btnloading.setOnClickListener(this);
        btnError.setOnClickListener(this);
        btnEmpty.setOnClickListener(this);
        btnContent.setOnClickListener(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_state_view;
    }

    @Override
    protected String setTitle() {
        return "stateView";
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_loading) {
            stateView.showLoading();
        } else if (v.getId() == R.id.btn_error) {
            stateView.showRetry();
        } else if (v.getId() == R.id.btn_empty) {
            stateView.showEmpty();
        } else if (v.getId() == R.id.btn_content) {
            stateView.showContent();
        }
    }
}