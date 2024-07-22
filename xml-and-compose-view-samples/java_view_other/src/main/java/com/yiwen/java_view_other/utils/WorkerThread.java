package com.yiwen.java_view_other.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;

import java.util.logging.LogRecord;

import io.github.haoyiwen.react_native_container.MyReactNativeApplication;

public class WorkerThread extends Thread {
    public static final String TAG = WorkerThread.class.getSimpleName();
    public Handler mHandler;

    public WorkerThread() {
        super("WorkerThread");
    }

    public void prepareHandler(){
        mHandler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                String str = (String) msg.obj;
                Log.d(TAG, "handleMessage: " + str);
                Toast.makeText(MyReactNativeApplication.getContext(), str, Toast.LENGTH_SHORT).show();
            }
        };
    }
}
