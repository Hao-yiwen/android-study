package com.example.chapter05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditFocusActivity extends AppCompatActivity implements View.OnFocusChangeListener {
    EditText et_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_focus);
        et_phone = findViewById(R.id.et_phone);
        EditText et_password = findViewById(R.id.et_password);
        et_password.setOnFocusChangeListener(this);
//        et_phone.setOnFocusChangeListener(this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            String phone = et_phone.getText().toString();
            if (phone.length() == 0 || phone.length() != 11 || !phone.startsWith("1")) {
                et_phone.requestFocus();
                Toast.makeText(this, "手机号码格式不对", Toast.LENGTH_SHORT).show();
            }
        }
    }
}