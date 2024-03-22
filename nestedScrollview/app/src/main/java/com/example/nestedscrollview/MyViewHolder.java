package com.example.nestedscrollview;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView textView;
    public View rootLayout;
    public MyViewHolder(View view) {
        super(view);
        imageView = view.findViewById(R.id.iv_image);
        textView = view.findViewById(R.id.tv_title);
        rootLayout = view.findViewById(R.id.ll_item);

        rootLayout.setBackgroundColor(Color.GREEN);
        textView.setBackgroundColor(Color.YELLOW);
    }
}
