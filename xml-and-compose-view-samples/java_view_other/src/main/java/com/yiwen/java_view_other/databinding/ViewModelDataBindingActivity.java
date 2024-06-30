package com.yiwen.java_view_other.databinding;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;

import com.yiwen.java_view_other.R;

public class ViewModelDataBindingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataViewModel viewModel = new DataViewModel();
        ActivityViewModelDataBindingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_view_model_data_binding);

        binding.setViewmodel(viewModel);
        binding.setLifecycleOwner(this);
    }
}