package com.example.chapter05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class EditHideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_hide);
        EditText et_phone = findViewById(R.id.et_phone);
        EditText et_password = findViewById(R.id.et_password);

        et_phone.addTextChangedListener(new HideTextWatcher(et_phone, 11, EditHideActivity.this));
        et_password.addTextChangedListener(new HideTextWatcher(et_password, 6, EditHideActivity.this));
    }
}