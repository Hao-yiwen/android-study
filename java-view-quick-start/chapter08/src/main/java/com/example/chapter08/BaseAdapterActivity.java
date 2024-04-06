package com.example.chapter08;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.chapter08.adapter.PlanetBaseAdapter;
import com.example.chapter08.enity.Planet;
import com.example.chapter08.util.ToastUtil;

import java.util.List;

public class BaseAdapterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private List<Planet> defaultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_adapter);

        Spinner sp_planet = findViewById(R.id.sp_planet);
        defaultList = Planet.getDefaultList();
        PlanetBaseAdapter adapter = new PlanetBaseAdapter(this, defaultList);
        sp_planet.setAdapter(adapter);
        sp_planet.setSelection(0);
        sp_planet.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ToastUtil.showMsg(this, "您选择的是" + defaultList.get(position).name);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}