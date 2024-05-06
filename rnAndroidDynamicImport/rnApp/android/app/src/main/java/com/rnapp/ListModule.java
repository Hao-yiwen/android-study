package com.rnapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rnapp.model.ModuleItem;

import java.util.List;

public class ListModule extends RecyclerView.Adapter<ListModule.ItemViewHolder> {
    private List<ModuleItem.Bundle> modules;
    private Context context;
    private OnItemClickListener listener;

    public ListModule(Context context, List<ModuleItem.Bundle> modules) {
        this.context = context;
        this.modules = modules;
    }

    /**
     * 添加数据
     *
     * @param data
     */
    public void addModules(List<ModuleItem.Bundle> data) {
        modules.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 数据清空
     */
    public void clearModules() {
        modules.clear();
        notifyDataSetChanged();
    }

    /**
     * 设置点击事件
     *
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化出列表的每一个Item对象
        View view = LayoutInflater.from(context)
                .inflate(R.layout.fragment_list_module, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        //绑定数据
        holder.textView.setText(modules.get(position).name);
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick((modules.get(position)));
            }
        });

    }

    @Override
    public int getItemCount() {
        return modules.size();
    }

    //继承RecyclerView.ViewHolder抽象类的自定义ViewHolder
    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.itemName);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(ModuleItem.Bundle bundle);
    }

}