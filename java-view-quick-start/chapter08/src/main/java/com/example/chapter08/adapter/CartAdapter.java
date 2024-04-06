package com.example.chapter08.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chapter08.R;
import com.example.chapter08.enity.CartInfo;

import java.util.List;

public class CartAdapter extends BaseAdapter {
    private Context mContext;
    private List<CartInfo> mCartlist;

    public CartAdapter(Context mContext, List<CartInfo> mCartlist) {
        this.mContext = mContext;
        this.mCartlist = mCartlist;
    }

    @Override
    public int getCount() {
        return mCartlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mCartlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_cart, null);
            holder.iv_thumb = convertView.findViewById(R.id.iv_thumb);
            holder.tv_name = convertView.findViewById(R.id.tv_name);
            holder.tv_desc = convertView.findViewById(R.id.tv_desc);
            holder.tv_count = convertView.findViewById(R.id.tv_count);
            holder.tv_price = convertView.findViewById(R.id.tv_price);
            holder.tv_sum = convertView.findViewById(R.id.tv_sum);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        CartInfo cartInfo = mCartlist.get(position);
        holder.iv_thumb.setImageURI(Uri.parse(cartInfo.goods.pic_path));
        holder.tv_name.setText(cartInfo.goods.name);
        holder.tv_desc.setText(cartInfo.goods.description);
        holder.tv_count.setText(String.valueOf(cartInfo.count));
        holder.tv_price.setText(String.valueOf((int) cartInfo.goods.price));
        // 设置总价
        holder.tv_sum.setText(String.valueOf((int) cartInfo.goods.price * cartInfo.count));

        return convertView;
    }

    public final class ViewHolder {
        public ImageView iv_thumb;
        public TextView tv_name;
        public TextView tv_desc;
        public TextView tv_count;
        public TextView tv_price;
        public TextView tv_sum;
    }
}
