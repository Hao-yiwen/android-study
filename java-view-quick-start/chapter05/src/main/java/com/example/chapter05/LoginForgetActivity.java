package com.example.chapter05;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class LoginForgetActivity extends AppCompatActivity implements View.OnClickListener {

    private String phone;
    private String verfyCode;
    private EditText et_password_first;
    private EditText et_password_second;
    private Button btn_verifycode;
    private EditText et_verifycode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_forget);
        phone = getIntent().getStringExtra("phone");
        et_password_first = findViewById(R.id.et_password_first);
        et_password_second = findViewById(R.id.et_password_second);
        btn_verifycode = findViewById(R.id.btn_verifycode);
        et_verifycode = findViewById(R.id.et_verifycode);
        findViewById(R.id.btn_verifycode).setOnClickListener(this);
        findViewById(R.id.btn_confirm).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_verifycode) {
            verfyCode = String.format("%06d", new Random().nextInt(999999));
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("请记住验证码");
            builder.setMessage("手机号" + phone + "，本次验证码是" + verfyCode + "，请输入验证码");
            builder.setPositiveButton("好的", null);
            AlertDialog dialog = builder.create();
            dialog.show();
        } else if (v.getId() == R.id.btn_confirm) {
            String password_first = et_password_first.getText().toString();
            String password_second = et_password_second.getText().toString();
            if (password_first.length() < 6) {
                Toast.makeText(this, "请输入6位密码", Toast.LENGTH_SHORT).show();
                return;
            }
            if(!password_first.equals(password_second)){
                Toast.makeText(this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                return;
            }
            if(!verfyCode.equals(et_verifycode.getText().toString())){
                Toast.makeText(this, "请输入正确的验证码", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "密码修改成功", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent();
            intent.putExtra("new_password", password_first);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}