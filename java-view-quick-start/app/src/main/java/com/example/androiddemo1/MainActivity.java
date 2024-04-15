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
        Button chapter08 = findViewById(R.id.chapter08);
        Button chapter09 = findViewById(R.id.chapter09);

        chapter03.setOnClickListener(this);
        chapter04.setOnClickListener(this);
        chapter05.setOnClickListener(this);
        chapter06.setOnClickListener(this);
        chapter07_client.setOnClickListener(this);
        chapter08.setOnClickListener(this);
        chapter09.setOnClickListener(this);

        findViewById(R.id.java_view_other).setOnClickListener(this);
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
            Intent intent = new Intent(this, com.example.chapter06.Chapter6BigHomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        } else if (v.getId() == R.id.chapter07_client) {
            Intent intent = new Intent(this, com.example.chapter07_client.Chapter7ClinetBigHomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (v.getId() == R.id.chapter08) {
            Intent intent = new Intent(this, com.example.chapter08.Chapter8BigHomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (v.getId() == R.id.chapter09) {
            Intent intent = new Intent(this, com.example.chapter09.Chapter9BigHomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (v.getId() == R.id.java_view_other) {
            Intent intent = new Intent(this, io.github.haoyiwen.javaviewothertest.BigHomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}