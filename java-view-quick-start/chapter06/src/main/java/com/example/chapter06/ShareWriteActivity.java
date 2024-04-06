package com.example.chapter06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class ShareWriteActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_age;
    private EditText et_weight;
    private EditText et_height;
    private CheckBox ck_marry;
    private SharedPreferences preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_write);

        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_weight = findViewById(R.id.et_weight);
        et_height = findViewById(R.id.et_height);
        ck_marry = findViewById(R.id.ck_marry);

        findViewById(R.id.btn_save).setOnClickListener(this);
        preference = getSharedPreferences("config", Context.MODE_PRIVATE);
        reload();
    }

    private void reload() {
        String name = preference.getString("name", null);
        if (name != null) {
            et_name.setText(name);
        }

        int age = preference.getInt("age", 0);
        if (age != 0) {
            et_age.setText(String.valueOf(age));
        }

        float height = preference.getFloat("height", 0f);
        if (height != 0f) {
            et_height.setText(String.valueOf(height));
        }

        float weight = preference.getFloat("weight", 0f);
        if (weight != 0f) {
            et_weight.setText(String.valueOf(weight));
        }

        boolean marry = preference.getBoolean("marry", false);
        ck_marry.setChecked(marry);
    }

    @Override
    public void onClick(View v) {
        String name = et_name.getText().toString();
        String age = et_age.getText().toString();
        String height = et_height.getText().toString();
        String weight = et_weight.getText().toString();

        SharedPreferences.Editor edit = preference.edit();
        edit.putString("name", name);
        edit.putInt("age", Integer.parseInt(age));
        edit.putFloat("height", Float.parseFloat(height));
        edit.putFloat("weight", Float.parseFloat(weight));
        edit.putBoolean("marry", ck_marry.isChecked());
        edit.commit();
    }
}