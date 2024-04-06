package com.example.chapter07_client;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Chapter7ClinetBigHomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chapter7_clinet_big_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.content_write_activity).setOnClickListener(this);
        findViewById(R.id.contact_add_activity).setOnClickListener(this);
        findViewById(R.id.montior_sms_activity).setOnClickListener(this);
        findViewById(R.id.permission_hungry_activity).setOnClickListener(this);
        findViewById(R.id.permission_lazy_activity).setOnClickListener(this);
        findViewById(R.id.provider_apk_activity).setOnClickListener(this);
        findViewById(R.id.provider_mms_activity).setOnClickListener(this);
        findViewById(R.id.send_mms_activity).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v.getId() == R.id.content_write_activity) {
            intent = new Intent(this, ContentWriteActivity.class);
        } else if (v.getId() == R.id.contact_add_activity) {
            intent = new Intent(this, ContactAddActivity.class);
        } else if (v.getId() == R.id.montior_sms_activity) {
            intent = new Intent(this, MonitorSmsActivity.class);
        } else if (v.getId() == R.id.permission_hungry_activity) {
            intent = new Intent(this, PermissionHungryActivity.class);
        } else if (v.getId() == R.id.permission_lazy_activity) {
            intent = new Intent(this, PermissionLazyActivity.class);
        } else if (v.getId() == R.id.provider_apk_activity) {
            intent = new Intent(this, ProviderApkActivity.class);
        } else if (v.getId() == R.id.provider_mms_activity) {
            intent = new Intent(this, ProviderMmsActivity.class);
        } else if (v.getId() == R.id.send_mms_activity) {
            intent = new Intent(this, SendMmsActivity.class);
        }

        if (intent != null) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}