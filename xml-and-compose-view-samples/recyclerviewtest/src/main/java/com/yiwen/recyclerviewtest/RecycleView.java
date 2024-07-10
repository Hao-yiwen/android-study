package com.yiwen.recyclerviewtest;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.yiwen.recyclerviewtest.recycleview.CustomAdapter;

import io.github.haoyiwen.test.core.activity.BaseActivity;

public class RecycleView extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] dataSet = new String[100];
        for (int i = 0; i < dataSet.length; i++) {
            dataSet[i] = "Item " + i;
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new CustomAdapter(dataSet));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_recycleview_test;
    }

    @Override
    protected String setTitle() {
        return "recyclerViewActivity";
    }
}