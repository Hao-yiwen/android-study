package com.example.chapter06;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Chapter6BigHomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chapter6_big_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.app_write_activity).setOnClickListener(this);

        findViewById(R.id.database_activity).setOnClickListener(this);

        findViewById(R.id.file_write_activity).setOnClickListener(this);

        findViewById(R.id.image_write_activity).setOnClickListener(this);

        findViewById(R.id.login_main_activity).setOnClickListener(this);

        findViewById(R.id.login_sqlite_activity).setOnClickListener(this);

        findViewById(R.id.room_write_activity).setOnClickListener(this);

        findViewById(R.id.share_write_activity).setOnClickListener(this);

        findViewById(R.id.shopping_cart_activity).setOnClickListener(this);

        findViewById(R.id.shopping_channel_activity).setOnClickListener(this);

        findViewById(R.id.shopping_detail_activity).setOnClickListener(this);

        findViewById(R.id.sqlite_helper_activity).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v.getId() == R.id.app_write_activity) {
            intent = new Intent(this, AppWriteActivity.class);
        } else if (v.getId() == R.id.database_activity) {
            intent = new Intent(this, DatabaseActivity.class);
        } else if (v.getId() == R.id.file_write_activity) {
            intent = new Intent(this, FileWriteActivity.class);
        } else if (v.getId() == R.id.image_write_activity) {
            intent = new Intent(this, ImageWriteActivity.class);
        } else if (v.getId() == R.id.login_main_activity) {
            intent = new Intent(this, LoginMainActivity.class);
        } else if (v.getId() == R.id.login_sqlite_activity) {
            intent = new Intent(this, LoginSQLiteActivity.class);
        } else if (v.getId() == R.id.room_write_activity) {
            intent = new Intent(this, RoomWriteActivity.class);
        } else if (v.getId() == R.id.share_write_activity) {
            intent = new Intent(this, ShareWriteActivity.class);
        } else if (v.getId() == R.id.shopping_cart_activity) {
            intent = new Intent(this, ShoppingCartActivity.class);
        } else if (v.getId() == R.id.shopping_channel_activity) {
            intent = new Intent(this, ShoppingChannelActivity.class);
        } else if (v.getId() == R.id.shopping_detail_activity) {
            intent = new Intent(this, ShoppingDetailActivity.class);
        } else if (v.getId() == R.id.sqlite_helper_activity) {
            intent = new Intent(this, SQLiteHelperActivity.class);
        }
        if (intent != null) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}