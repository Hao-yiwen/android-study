package com.example.chapter05;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chapter05.util.ViewUtil;

import java.util.Random;

public class LoginMainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private TextView tv_password;
    private EditText et_password;
    private Button btn_forget;
    private CheckBox ck_remember;
    private EditText et_phone;
    private RadioButton rb_verifycode;
    private RadioButton rb_password;
    private ActivityResultLauncher<Intent> register;
    private Button btn_login;
    private String mPassword = "111111";
    private String verfyCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        RadioGroup rg_login = findViewById(R.id.rg_login);
        tv_password = findViewById(R.id.tv_password);
        et_password = findViewById(R.id.et_password);
        et_phone = findViewById(R.id.et_phone);
        btn_forget = findViewById(R.id.btn_forget);
        btn_login = findViewById(R.id.btn_login);
        ck_remember = findViewById(R.id.ck_remember);
        rb_password = findViewById(R.id.rb_password);
        rb_verifycode = findViewById(R.id.rb_verifycode);
        rg_login.setOnCheckedChangeListener(this);
        et_phone.addTextChangedListener(new HideTextWatcher1(et_phone, 11));
        et_password.addTextChangedListener(new HideTextWatcher1(et_password, 6));
        btn_forget.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        register = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            Intent data = result.getData();
            if (data != null && result.getResultCode() == Activity.RESULT_OK) {
                mPassword = data.getStringExtra("new_password");
            }
        });
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.rb_password) {
            tv_password.setText(getString(R.string.login_password));
            et_password.setHint(getString(R.string.input_password));
            btn_forget.setText(getString(R.string.forget_password));
            ck_remember.setVisibility(View.VISIBLE);
        } else if (checkedId == R.id.rb_verifycode) {
            tv_password.setText(getString(R.string.verifycode));
            et_password.setHint(getString(R.string.input_vezifycode));
            btn_forget.setText(getString(R.string.get_verifycode));
            ck_remember.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        String phone = et_phone.getText().toString();
        if (v.getId() == R.id.btn_forget) {
            if (phone.length() < 11) {
                Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                return;
            }
            if (rb_password.isChecked()) {
                Intent intent = new Intent(this, LoginForgetActivity.class);
                intent.putExtra("phone", phone);
                register.launch(intent);
            } else if (rb_verifycode.isChecked()) {
                verfyCode = String.format("%06d", new Random().nextInt(999999));
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("请记住验证码");
                builder.setMessage("手机号" + phone + "，本次验证码是" + verfyCode + "，请输入验证码");
                builder.setPositiveButton("好的", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        } else if (v.getId() == R.id.btn_login) {
            if (rb_password.isChecked()) {
                if (!mPassword.equals(et_password.getText().toString())) {
                    Toast.makeText(this, "请输入正确的密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                loginSuccess();
            } else if (rb_verifycode.isChecked()) {
                if (!verfyCode.equals(et_password.getText().toString())) {
                    Toast.makeText(this, "请输入正确的验证码", Toast.LENGTH_SHORT).show();
                    return;
                }
                loginSuccess();
            }
        }
    }

    private void loginSuccess() {
        String desc = String.format("手机号%s，恭喜你通过本次登录验证", et_phone.getText().toString());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("登录成功");
        builder.setMessage(desc);
        builder.setPositiveButton("确定", (dialog, which) -> {
            finish();
        });
        builder.setNegativeButton("取消", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private class HideTextWatcher1 implements TextWatcher {
        private int maxLength;
        private EditText et;

        public HideTextWatcher1(EditText et, int maxLength) {
            this.et = et;
            this.maxLength = maxLength;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.toString().length() == maxLength) {
                ViewUtil.HideOneInputMethod(LoginMainActivity.this, et);
            }
        }
    }
}