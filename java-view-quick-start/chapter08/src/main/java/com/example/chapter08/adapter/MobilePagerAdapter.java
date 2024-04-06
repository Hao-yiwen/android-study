package com.example.chapter08.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.chapter08.enity.GoodsInfo;
import com.example.chapter08.fragment.DynamicFragment;

import java.util.List;

public class MobilePagerAdapter extends FragmentPagerAdapter {
    private final List<GoodsInfo> mGoodList;

    public MobilePagerAdapter(@NonNull FragmentManager fragment, List<GoodsInfo> goodsInfoList) {
        super(fragment, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.mGoodList = goodsInfoList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        GoodsInfo goodsInfo = mGoodList.get(position);
        return DynamicFragment.newInstance(position, goodsInfo.pic, goodsInfo.description);
    }

    @Override
    public int getCount() {
        return mGoodList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mGoodList.get(position).name;
    }
}
