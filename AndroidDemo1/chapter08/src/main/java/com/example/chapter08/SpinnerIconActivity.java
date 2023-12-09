package com.example.chapter08;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.example.chapter08.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpinnerIconActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // 定义下拉列表需要显示的行星图标数组
    private int[] arr_images = {R.drawable.shuixing, R.drawable.jinxing, R.drawable.diqiu, R.drawable.huoxing,
            R.drawable.muxing, R.drawable.tuxing};

    //定义下拉列表要显示的行星名称数组
    private String[] arr_planets = {"水星", "金星", "地球", "火星", "木星", "土星"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_icon);
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < arr_images.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("icon", arr_images[i]);
            map.put("name", arr_planets[i]);
            list.add(map);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, list, R.layout.item_simple, new String[]{"icon", "name"}, new int[]{R.id.iv_icon, R.id.tv_name});
        Spinner sp_icon = findViewById(R.id.sp_icon);
        sp_icon.setAdapter(simpleAdapter);
        sp_icon.setSelection(0);
        sp_icon.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ToastUtil.showMsg(this, "选择了" + arr_planets[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}