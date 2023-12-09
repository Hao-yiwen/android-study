package com.example.chapter08.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

import com.example.chapter08.enity.GoodsInfo;

import java.util.ArrayList;
import java.util.List;

public class ImagePagerAdapter extends PagerAdapter {
    private List<GoodsInfo> goodsInfoList;
    private Context mContext;

    // 声明一个图像列表
    private List<ImageView> mViewList = new ArrayList<>();

    public ImagePagerAdapter(Context mContext, List<GoodsInfo> goodsInfoList) {
        this.goodsInfoList = goodsInfoList;
        this.mContext = mContext;
        for (GoodsInfo info : goodsInfoList) {
            ImageView iv = new ImageView(mContext);
            iv.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            iv.setImageResource(info.pic);
            mViewList.add(iv);
        }
    }

    @Override
    public int getCount() {
        return mViewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView item = mViewList.get(position);
        container.addView(item);
        return item;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(mViewList.get(position));
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return goodsInfoList.get(position).name;
    }
}
