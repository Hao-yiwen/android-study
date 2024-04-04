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

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar_home);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btn_recycle = findViewById(R.id.btn_recycle);
        btn_recycle.setOnClickListener(this);

        Button btn_card = findViewById(R.id.btn_cardview);
        btn_card.setOnClickListener(this);

        Button btn_list = findViewById(R.id.btn_list);
        btn_list.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_recycle) {
            Intent intent = new Intent(this, RecycleView.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_cardview) {
        } else if (v.getId() == R.id.btn_list) {

        }
    }
}