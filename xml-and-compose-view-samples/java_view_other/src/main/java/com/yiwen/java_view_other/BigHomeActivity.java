package com.yiwen.java_view_other;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.yiwen.java_view_other.databinding.ActivityBigHomeBinding;

public class BigHomeActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityBigHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityBigHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.btn_qr_code).setOnClickListener(this);

        findViewById(R.id.btn_intent_filter).setOnClickListener(this);

        findViewById(R.id.btn_color_colors).setOnClickListener(this);

        findViewById(R.id.btn_jump_viewModel).setOnClickListener(this);

        findViewById(R.id.btn_jump_animation).setOnClickListener(this);

        binding.btnJumpViewbinding.setOnClickListener(this);

        binding.btnLiveData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if (v.getId() == R.id.btn_qr_code) {
            intent.setClass(this, ScanActivity.class);
        } else if (v.getId() == R.id.btn_intent_filter) {
            intent.setClass(this, ShareActivity.class);
        } else if (v.getId() == R.id.btn_color_colors) {
            intent.setClass(this, ColorAndColorsActivity.class);
        } else if (v.getId() == R.id.btn_jump_viewModel) {
            intent.setClass(this, ViewModelWithXml.class);
        } else if (v.getId() == R.id.btn_jump_animation) {
            intent.setClass(this, AnimationActivity.class);
        } else if (v.getId() == R.id.btn_jump_viewbinding) {
            intent.setClass(this, ViewBindingActivity.class);
        } else if (v.getId() == R.id.btn_live_data) {
            intent.setClass(this, LiveDataActivity.class);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}