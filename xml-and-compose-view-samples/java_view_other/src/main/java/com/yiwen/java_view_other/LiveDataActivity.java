package com.yiwen.java_view_other;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.yiwen.java_view_other.data.LiveDataViewModel;
import com.yiwen.java_view_other.databinding.ActivityLiveDataBinding;
import com.yiwen.java_view_other.model.Car;

public class LiveDataActivity extends AppCompatActivity {
    private ActivityLiveDataBinding binding;
    private LiveDataViewModel liveDataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLiveDataBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        liveDataViewModel = new ViewModelProvider(this).get(LiveDataViewModel.class);

        liveDataViewModel.getCar().observe(this, v -> {
            binding.textView.setText(v.getNumber() + "");
        });

        binding.button.setOnClickListener(v -> {
            Car tmp = new Car();
            tmp.setNumber(liveDataViewModel.getCar().getValue().getNumber() + 1);
            tmp.setTextData("2");
            liveDataViewModel.update(tmp);
        });

    }
}