package com.example.chapter08;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.chapter08.adapter.ImagePagerAdapter;
import com.example.chapter08.enity.GoodsInfo;
import com.example.chapter08.util.ToastUtil;

import java.util.ArrayList;

public class ViewPagerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager vp_content;
    private ArrayList<GoodsInfo> goodsInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        vp_content = findViewById(R.id.vp_content);
        goodsInfos = GoodsInfo.getDefaultList();
        ImagePagerAdapter adapter = new ImagePagerAdapter(this, goodsInfos);
        vp_content.setAdapter(adapter);
        vp_content.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        ToastUtil.showMsg(this, "您翻到的手机品牌是" + goodsInfos.get(position).name);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}