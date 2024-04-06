package com.example.androiddemo1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("大首页");


        Button chapter03 = findViewById(R.id.chapter03);
        Button chapter04 = findViewById(R.id.chapter04);
        Button chapter05 = findViewById(R.id.chapter05);
        Button chapter06 = findViewById(R.id.chapter06);
        Button chapter07_client = findViewById(R.id.chapter07_client);
        Button chapter07_server = findViewById(R.id.chapter07_server);
        Button chapter08 = findViewById(R.id.chapter08);
        Button chapter09 = findViewById(R.id.chapter09);

        chapter03.setOnClickListener(this);
        chapter04.setOnClickListener(this);
        chapter05.setOnClickListener(this);
        chapter06.setOnClickListener(this);
        chapter07_client.setOnClickListener(this);
        chapter07_server.setOnClickListener(this);
        chapter08.setOnClickListener(this);
        chapter09.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.chapter03) {
            Intent intent = new Intent(this, com.example.chapter03.Chapter3BigHome.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (v.getId() == R.id.chapter04) {
            Intent intent = new Intent(this, com.example.chapter04.Chapter4BigHome.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (v.getId() == R.id.chapter05) {
            Intent intent = new Intent(this, com.example.chapter05.Chapter5BigHomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (v.getId() == R.id.chapter06) {
            Intent intent = new Intent(this, com.example.chapter06.MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        } else if (v.getId() == R.id.chapter07_client) {
            Intent intent = new Intent(this, com.example.chapter07_client.MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (v.getId() == R.id.chapter07_server) {
            Intent intent = new Intent(this, com.example.chapter07_server.MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (v.getId() == R.id.chapter08) {
            Intent intent = new Intent(this, com.example.chapter08.MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (v.getId() == R.id.chapter09) {
            Intent intent = new Intent(this, com.example.chapter09.MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}