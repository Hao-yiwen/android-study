package com.yiwen.recyclerviewtest.viewpager;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.yiwen.recyclerviewtest.R;

import io.github.haoyiwen.test.core.activity.BaseActivity;

public class ViewPagerTestActivity extends BaseActivity {
    ViewPager viewPager;

    @Override
    public void initView() {
        viewPager = findViewById(R.id.view_pager_test);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), 5));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_view_pager_test;
    }

    @Override
    protected String setTitle() {
        return "ViewPager测试";
    }
}