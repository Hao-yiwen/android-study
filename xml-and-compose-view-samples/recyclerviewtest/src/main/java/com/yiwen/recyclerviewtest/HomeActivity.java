package com.yiwen.recyclerviewtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import io.github.haoyiwen.test.core.activity.BaseActivity;

public class HomeActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button btn_recycle = findViewById(R.id.btn_recycle);
        btn_recycle.setOnClickListener(this);

        Button btn_card = findViewById(R.id.btn_cardview);
        btn_card.setOnClickListener(this);

        Button btn_list = findViewById(R.id.btn_list);
        btn_list.setOnClickListener(this);

        Button btn_include = findViewById(R.id.btn_include);
        btn_include.setOnClickListener(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_home;
    }

    @Override
    protected String setTitle() {
        return "ScrollviewActivity";
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_recycle) {
            Intent intent = new Intent(this, RecycleView.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_cardview) {
            Intent intent = new Intent(this, CardViewActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_list) {
            Intent intent = new Intent(this, ListViewActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_include) {
            Intent intent = new Intent(this, IncludeTestView.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}