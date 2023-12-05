package com.example.chapter06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class AppWriteActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_name;
    private EditText et_age;
    private EditText et_weight;
    private EditText et_height;
    private CheckBox ck_marry;
    private MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_write);

        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_weight = findViewById(R.id.et_weight);
        et_height = findViewById(R.id.et_height);
        ck_marry = findViewById(R.id.ck_marry);

        findViewById(R.id.btn_save).setOnClickListener(this);
        app = MyApplication.getInstance();
        reload();
    }

    private void reload() {
        String name = app.infoMap.get("name");
        if (name == null) {
            return;
        }
        String age = app.infoMap.get("age");
        String height = app.infoMap.get("height");
        String weight = app.infoMap.get("weight");
        String marry = app.infoMap.get("marry");
        et_name.setText(name);
        et_age.setText(age);
        et_height.setText(height);
        et_weight.setText(weight);
        if (marry != null) {
            ck_marry.setChecked(marry.equals("是"));
        }
    }

    @Override
    public void onClick(View v) {
        String name = et_name.getText().toString();
        String age = et_age.getText().toString();
        String height = et_height.getText().toString();
        String weight = et_weight.getText().toString();

        app.infoMap.put("name", name);
        app.infoMap.put("age", age);
        app.infoMap.put("height", height);
        app.infoMap.put("weight", weight);
        app.infoMap.put("marry", ck_marry.isChecked() ? "是" : "否");
    }
}