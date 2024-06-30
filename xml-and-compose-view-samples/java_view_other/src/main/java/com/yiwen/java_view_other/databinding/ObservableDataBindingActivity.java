package com.yiwen.java_view_other.databinding;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.yiwen.java_view_other.R;

public class ObservableDataBindingActivity extends AppCompatActivity {

    private User user = new User("yiwen", "yiwen@qq.com");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        ActivityObservableDataBindingBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_observable_data_binding);

        viewDataBinding.setUser(user);

        viewDataBinding.buttonAdd.setOnClickListener(v -> {
            User tmp = new User("lihua", "lihua@qq.com");
            viewDataBinding.setUser(tmp);
        });

    }
}