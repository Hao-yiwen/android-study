package com.example.chapter08;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;

import com.example.chapter08.adapter.ImagePagerAdapter;
import com.example.chapter08.enity.GoodsInfo;
import com.example.chapter08.util.ToastUtil;

import java.util.ArrayList;

public class PageTabActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private ViewPager vp_content;
    private ArrayList<GoodsInfo> goodsInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_tab);
        initPageStrip();
        initViewPager();
    }

    private void initViewPager() {
        vp_content = findViewById(R.id.vp_content);
        goodsInfos = GoodsInfo.getDefaultList();
        ImagePagerAdapter adapter = new ImagePagerAdapter(this, goodsInfos);
        vp_content.setAdapter(adapter);
        vp_content.addOnPageChangeListener(this);
        vp_content.setCurrentItem(3);
    }

    private void initPageStrip() {
        PagerTabStrip pts_tab = findViewById(R.id.pts_tab);
        pts_tab.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        pts_tab.setTextColor(Color.BLACK) ;
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