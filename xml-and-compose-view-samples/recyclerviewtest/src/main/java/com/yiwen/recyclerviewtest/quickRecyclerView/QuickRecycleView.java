package com.yiwen.recyclerviewtest.quickRecyclerView;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yiwen.recyclerviewtest.R;

import java.util.ArrayList;

import io.github.haoyiwen.test.core.activity.BaseActivity;

/**
 * @description: BaseRecyclerViewAdapterHelper测试
 * @linking: https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 * @date: 2024/7/10 13:21
 */

public class QuickRecycleView extends BaseActivity {
    RecyclerView recyclerView;

    @Override
    public void initView() {
        recyclerView = findViewById(R.id.rv_quick_recycle_view);
        ArrayList<Item> items = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            items.add(new Item("title" + i, "content" + i));
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter myAdapter = new MyAdapter(items);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_quick_recycle_view;
    }

    @Override
    protected String setTitle() {
        return "quick_recycle_view";
    }
}