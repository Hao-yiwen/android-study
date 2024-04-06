package com.example.chapter05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class CheckBoxActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box);
        CheckBox ck_custom = findViewById(R.id.ck_custom);
        CheckBox ck_system = findViewById(R.id.ck_system);

        ck_custom.setOnCheckedChangeListener(this);
        ck_system.setOnCheckedChangeListener(this);
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String desc = String.format("您%s了CheckBox", isChecked ? "勾选" : "取消勾选");
        buttonView.setText(desc);
    }
}