package com.example.chapter04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ActStartActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ning";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "ActStartActivity onCreate");
        setContentView(R.layout.activity_act_start);
        findViewById(R.id.btn_start).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // 1.在Intent的构造函数中指定
        // Intent intent = new Intent(this, ActFinshActivity.class);
        // 2.调用意图对象的setClass方法指定
         Intent intent = new Intent();
        // intent.setClass(this, ActFinshActivity.class);
        // 3.调用意图对象的setComponnet方法指定
        ComponentName component = new ComponentName(this, ActFinshActivity.class);
        intent.setComponent(component);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "ActStartActivity onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "ActStartActivity onResume");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "ActStartActivity onRestart");
        super.onRestart();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "ActStartActivity onStop");
        super.onStop();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "ActStartActivity onPause");
        super.onPause();
    }

}