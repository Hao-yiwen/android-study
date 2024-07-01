package com.yiwen.java_view_other;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.github.haoyiwen.react_native_container.MyReactNativeApplication;
import io.github.haoyiwen.test.core.activity.BaseActivity;

public class HandlerTestActivity extends BaseActivity {
    Handler handler;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = findViewById(R.id.textView);

        handler = MyReactNativeApplication.getMainHandler();
        handler = new Handler(handler.getLooper()) {
            @Override
            public void handleMessage(android.os.Message msg) {
                super.handleMessage(msg);
                textView.setText("handler");
            }
        };

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(2000);
//                    handler.sendEmptyMessage(0);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }).start();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    handler.sendEmptyMessage(0);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_hadnler_test;
    }

    @Override
    protected String setTitle() {
        return null;
    }
}
