package com.example.chapter08.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chapter08.R;
import com.example.chapter08.ShoppingChannelActivity;
import com.example.chapter08.ShoppingDetailActivity;
import com.example.chapter08.enity.GoodsInfo;

import java.util.List;

public class GoodsAdapter extends BaseAdapter {

    private Context mContext;
    private List<GoodsInfo> mGoodsInfo;

    public GoodsAdapter(Context context, List<GoodsInfo> goodsInfo, AddCartListener mAddCartListener) {
        this.mContext = context;
        this.mGoodsInfo = goodsInfo;
        this.mAddCartListener = mAddCartListener;
    }

    @Override
    public int getCount() {
        return mGoodsInfo.size();
    }

    @Override
    public Object getItem(int position) {
        return mGoodsInfo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GoodsInfo info = mGoodsInfo.get(position);
        ViewHodler hodler;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_goods, null);
            hodler = new ViewHodler();
            hodler.iv_thumb = convertView.findViewById(R.id.iv_thumb);
            hodler.tv_name = convertView.findViewById(R.id.tv_name);
            hodler.tv_price = convertView.findViewById(R.id.tv_price);
            hodler.btn_add = convertView.findViewById(R.id.btn_add);
            convertView.setTag(hodler);
        } else {
            hodler = (ViewHodler) convertView.getTag();
        }

        hodler.iv_thumb.setImageURI(Uri.parse(info.pic_path));
        hodler.tv_name.setText(info.name);
        hodler.tv_price.setText(String.valueOf((int) info.price));

        hodler.btn_add.setOnClickListener(v -> {
//            ShoppingChannelActivity activity = (ShoppingChannelActivity) mContext;
//            activity.addToCart(info.id, info.name);
            mAddCartListener.addToCart(info.id, info.name);
        });

        // 点击商品图片 跳转到商品详情页面
        hodler.iv_thumb.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, ShoppingDetailActivity.class);
            intent.putExtra("goods_id", info.id);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            mContext.startActivity(intent);
        });
        return convertView;
    }

    public final class ViewHodler {
        public ImageView iv_thumb;
        public TextView tv_name;
        public TextView tv_price;
        public Button btn_add;
    }

    private AddCartListener mAddCartListener;

    // 声明一个假如购物车的监听器对象
    public interface AddCartListener {
        void addToCart(int id, String name);
    }
}
