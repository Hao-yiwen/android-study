package com.yiwen.java_view_other.rxjava;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

import com.yiwen.java_view_other.R;
import com.yiwen.java_view_other.databinding.ActivityRxJavaBinding;

public class RxJavaActivity extends AppCompatActivity {
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRxJavaBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_rx_java);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        viewDataBinding.setViewModel(userViewModel);
        viewDataBinding.setLifecycleOwner(this);
    }
}