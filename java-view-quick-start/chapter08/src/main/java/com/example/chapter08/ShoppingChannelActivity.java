package com.example.chapter08;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.chapter08.adapter.GoodsAdapter;
import com.example.chapter08.database.ShoppingDBHelper;
import com.example.chapter08.enity.GoodsInfo;
import com.example.chapter08.util.ToastUtil;

import java.util.List;

public class ShoppingChannelActivity extends AppCompatActivity implements View.OnClickListener, GoodsAdapter.AddCartListener {
    // 声明一个商品数据库的帮助对象
    private ShoppingDBHelper mDBHelper;
    private TextView tv_count;
    private GridView gv_channel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_channel);
        TextView tv_title = findViewById(R.id.tv_title);
        tv_title.setText("手机商城");

        tv_count = findViewById(R.id.tv_count);
        gv_channel = findViewById(R.id.gv_channel);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.iv_cart).setOnClickListener(this);
        // 获取商品数据库的帮助器对象
        mDBHelper = ShoppingDBHelper.getInstance(this);
        mDBHelper.openReadLink();
        mDBHelper.openWriteLink();

        // 冲数据库查询出商品信息并展示
        showGoods();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showCartInfoTotal();
    }

    private void showCartInfoTotal() {
        int count = mDBHelper.queryCartInfoCount();
        MyApplication.getInstance().goodsCount = count;
        tv_count.setText(String.valueOf(count));
    }

    private void showGoods() {
        // 查询数据库中所有的商品记录
        List<GoodsInfo> list = mDBHelper.queryAllGoodsInfo();
        GoodsAdapter adapter = new GoodsAdapter(this, list, this);
        gv_channel.setAdapter(adapter);
    }


    // 把指定编号的商品添加到购物车
    @Override
    public void addToCart(int id, String name) {
        int count = ++MyApplication.getInstance().goodsCount;
        tv_count.setText(String.valueOf(count));
        mDBHelper.insertGoodsInfos(id);
        ToastUtil.showMsg(this, "已添加一条" + name + "商品记录");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDBHelper.closeLink();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back) {
            finish();
        } else if (v.getId() == R.id.iv_cart) {
            Intent intent = new Intent(this, ShoppingCartActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            // 跳转到购物车页面
            startActivity(intent);
        }
    }
}