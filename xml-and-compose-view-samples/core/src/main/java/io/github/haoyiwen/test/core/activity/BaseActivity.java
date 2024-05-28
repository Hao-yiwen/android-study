package io.github.haoyiwen.test.core.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.github.haoyiwen.test.core.bus.EventBusManager;

/**
 * @description 基础Activity
 * @detail bus注册和删除
 * @detail loading效果添加
 * @detail 导航效果
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBusManager.getInstance().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBusManager.getInstance().unregister(this);
    }
}
