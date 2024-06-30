package com.yiwen.java_view_other.databinding;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.yiwen.java_view_other.R;
import com.yiwen.java_view_other.databinding.ActivityDataBindingBinding;

import io.github.haoyiwen.test.core.activity.BaseActivity;

public class DataBindingActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 使用 DataBindingUtil.inflate 来膨胀布局
        ActivityDataBindingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);

        // 设置事件监听
        binding.observableBinding.setOnClickListener(v -> {
            Log.d("DataBindingFragment", "observableBinding clicked");
            startActivity(new Intent(this, ObservableDataBindingActivity.class));
        });

        binding.viewmodelBinding.setOnClickListener(v -> {
            Log.d("DataBindingFragment", "viewmodelBinding clicked");
            Intent intent = new Intent(this, ViewModelDataBindingActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_data_binding;
    }

    @Override
    protected String setTitle() {
        return "DataBindingActivity";
    }

}