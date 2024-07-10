package com.yiwen.recyclerviewtest.quickRecyclerView;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter4.BaseQuickAdapter;
import com.chad.library.adapter4.viewholder.QuickViewHolder;
import com.yiwen.recyclerviewtest.R;

import java.util.List;

public class MyAdapter extends BaseQuickAdapter<Item, QuickViewHolder> {
    public MyAdapter(List<Item> items) {
        super(items);
    }

    @Override
    protected void onBindViewHolder(@NonNull QuickViewHolder holder, int i, @Nullable Item item) {
        ((TextView) holder.getView(R.id.qr_title)).setText(item.getTitle());
        ((TextView) holder.getView(R.id.qr_description)).setText(item.getContent());
    }

    @NonNull
    @Override
    protected QuickViewHolder onCreateViewHolder(@NonNull Context context, @NonNull ViewGroup viewGroup, int i) {
        return new QuickViewHolder(R.layout.quick_recycle_item, viewGroup);
    }
}
