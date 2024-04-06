package com.example.chapter09;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Chapter9BigHomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chapter9_big_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.alarm_activity).setOnClickListener(this);
        findViewById(R.id.broad_order_activity).setOnClickListener(this);
        findViewById(R.id.broad_standard_activity).setOnClickListener(this);
        findViewById(R.id.broad_static_activity).setOnClickListener(this);
        findViewById(R.id.change_direction_activity).setOnClickListener(this);
        findViewById(R.id.life_cycle_test_activity).setOnClickListener(this);
        findViewById(R.id.system_minute_activity).setOnClickListener(this);
        findViewById(R.id.system_network_activity).setOnClickListener(this);
        findViewById(R.id.return_desktop_activity).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v.getId() == R.id.alarm_activity) {
            intent = new Intent(this, AlarmActivity.class);
        } else if (v.getId() == R.id.broad_order_activity) {
            intent = new Intent(this, BroadOrderActivity.class);
        } else if (v.getId() == R.id.broad_standard_activity) {
            intent = new Intent(this, BroadStandardActivity.class);
        } else if (v.getId() == R.id.broad_static_activity) {
            intent = new Intent(this, BroadStaticActivity.class);
        } else if (v.getId() == R.id.change_direction_activity) {
            intent = new Intent(this, ChangeDirectionActivity.class);
        } else if (v.getId() == R.id.life_cycle_test_activity) {
            intent = new Intent(this, LifeCycleTestActivity.class);
        } else if (v.getId() == R.id.system_minute_activity) {
            intent = new Intent(this, SystemMinuteActivity.class);
        } else if (v.getId() == R.id.system_network_activity) {
            intent = new Intent(this, SystemNetworkActivity.class);
        } else if (v.getId() == R.id.return_desktop_activity) {
            intent = new Intent(this, ReturnDesktopActivity.class);
        }
        if (intent != null) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}