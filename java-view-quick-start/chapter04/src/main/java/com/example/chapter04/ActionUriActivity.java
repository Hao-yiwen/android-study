package com.example.chapter04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class ActionUriActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_uri);

        findViewById(R.id.btn_dial).setOnClickListener(this);
        findViewById(R.id.btn_sms).setOnClickListener(this);
        findViewById(R.id.btn_my).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if (v.getId() == R.id.btn_dial) {
            // 跳转到拨号页面
            intent.setAction(Intent.ACTION_DIAL);
            Uri uri = Uri.parse("tel:10086");
            intent.setData(uri);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_sms) {
            intent.setAction(Intent.ACTION_SENDTO);
            Uri uri = Uri.parse("smsto:10086");
            intent.setData(uri);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_my) {
            intent.setAction("android.intent.action.NING");
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            startActivity(intent);
        }
    }
}