package com.example.javaviewtest;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.yiwen.compose_views.ComposeActivity;
import com.yiwen.recyclerviewtest.HomeActivity;

public class BigHomeActivity extends AppCompatActivity {
    private final int OVERLAY_PERMISSION_REQ_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activitu_constraint_layout_view1);

        // 打印activity context
        Log.d("BigHomeActivity", "onCreate: " + this);
        // 打印application context
        Log.d("BigHomeActivity", "onCreate: " + getApplicationContext());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("大首页");
        setSupportActionBar(toolbar);

        Button button = findViewById(R.id.btn_jump_home);

        button.setOnClickListener(v -> {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        Button btn_compose = findViewById(R.id.btn_jump_compose);
        btn_compose.setOnClickListener(v -> {
            Intent intent = new Intent(this, ComposeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        Button btn_jump_thrid_aar = findViewById(R.id.btn_jump_thrid_aar);
        btn_jump_thrid_aar.setOnClickListener(v -> {
            Intent intent = new Intent(this, com.example.chapter03.Chapter3BigHome.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        Button btn_jump_rn = findViewById(R.id.btn_jump_rn);
        btn_jump_rn.setOnClickListener(v -> {
            Intent intent = new Intent(this, io.github.haoyiwen.react_native_container.ReactNativeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        Button btn_qr_code = findViewById(R.id.btn_jump_javaview_other);
        btn_qr_code.setOnClickListener(v -> {
            Intent intent = new Intent(this, com.yiwen.java_view_other.BigHomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
    }
}