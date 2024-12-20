package com.yiwen.java_view_other;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.yiwen.java_view_other.databinding.ActivityLlmactivityBinding;

public class LLMActivity extends AppCompatActivity {
    ActivityLlmactivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLlmactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}