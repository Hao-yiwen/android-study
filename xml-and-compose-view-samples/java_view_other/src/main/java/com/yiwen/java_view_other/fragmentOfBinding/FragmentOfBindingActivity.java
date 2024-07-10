package com.yiwen.java_view_other.fragmentOfBinding;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.yiwen.java_view_other.R;

import io.github.haoyiwen.test.core.activity.BaseActivity;

public class FragmentOfBindingActivity extends BaseActivity {
    FrameLayout frameLayout;

    @Override
    public void initView() {
        frameLayout = findViewById(R.id.fragment_container);
        // 替换fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentBinding()).commit();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_fragment_of_binding;
    }

    @Override
    protected String setTitle() {
        return "fragment_of_binding";
    }
}