package com.example.chapter06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.chapter06.database.UserDBHelper;
import com.example.chapter06.enity.User;
import com.example.chapter06.util.ToastUtil;

import java.util.List;

public class SQLiteHelperActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_name;
    private EditText et_age;
    private EditText et_weight;
    private EditText et_height;
    private CheckBox ck_marry;
    private UserDBHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_helper);
        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_weight = findViewById(R.id.et_weight);
        et_height = findViewById(R.id.et_height);
        ck_marry = findViewById(R.id.ck_marry);

        findViewById(R.id.btn_save).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_modify).setOnClickListener(this);
        findViewById(R.id.btn_find).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mHelper = UserDBHelper.getInstance(this);
        mHelper.openWriteLink();
        mHelper.openReadLink();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mHelper.closeLink();
    }

    @Override
    public void onClick(View v) {
        String name = et_name.getText().toString();
        int age = Integer.parseInt(et_age.getText().toString());
        float weight = Float.parseFloat(et_weight.getText().toString());
        long height = Long.parseLong(et_height.getText().toString());
        boolean married = ck_marry.isChecked();
        User user = null;
        if (v.getId() == R.id.btn_save) {
            user = new User(name, age, height, weight, married);
            if (mHelper.insert(user) > 0) {
                ToastUtil.showMsg(this, "数据添加成功");
            }
        } else if (v.getId() == R.id.btn_delete) {
            if (mHelper.deleteByName(name) > 0) {
                ToastUtil.showMsg(this, "数据删除成功");
            }
        } else if(v.getId() == R.id.btn_modify){
            user = new User(name, age, height, weight, married);
            if(mHelper.updateByName(user) > 0){
                ToastUtil.showMsg(this, "数据修改成功");
            }
        } else {
            List<User> users = mHelper.queryByName(name);
            for (User item : users) {
                Log.d("SQLiteHelperActivity", item.toString());
            }
        }
    }
}