package com.example.chapter08;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chapter08.util.ToastUtil;

public class SpinnerDialogActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //定义下拉列表需要显示的文本数组
    private String[] arr = {"北京", "上海", "广州", "深圳", "杭州", "南京", "天津", "重庆"};
    private Spinner sp_dropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_dialog);
        sp_dropdown = findViewById(R.id.sp_dropdown);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.item_select, arr);
        sp_dropdown.setAdapter(adapter);
        sp_dropdown.setSelection(0);
        sp_dropdown.setPrompt("请选择城市");
        sp_dropdown.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ToastUtil.showMsg(this, "选择了" + arr[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}