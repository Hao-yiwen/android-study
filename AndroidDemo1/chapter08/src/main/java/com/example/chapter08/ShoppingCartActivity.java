package com.example.chapter08;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chapter08.adapter.CartAdapter;
import com.example.chapter08.database.ShoppingDBHelper;
import com.example.chapter08.enity.CartInfo;
import com.example.chapter08.enity.GoodsInfo;
import com.example.chapter08.util.ToastUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCartActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {

    private TextView tv_count;
    private ListView ll_cart;
    private ShoppingDBHelper mDBHelper;
    private List<CartInfo> mCartList;

    // 声明一个根据商品编号查找信息的映射，把商品信息缓存起来，这样不用每一次都去查询数据库
    private Map<Integer, GoodsInfo> mGoodsMap = new HashMap<>();
    private TextView tv_total_price;
    private LinearLayout ll_empty;
    private LinearLayout ll_content;
    private CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        TextView tv_title = findViewById(R.id.tv_title);
        tv_title.setText("购物车");
        ll_cart = findViewById(R.id.ll_cart);
        tv_total_price = findViewById(R.id.tv_total_price);

        tv_count = findViewById(R.id.tv_count);
        tv_count.setText(String.valueOf(MyApplication.getInstance().goodsCount));

        mDBHelper = ShoppingDBHelper.getInstance(this);

        findViewById(R.id.iv_back).setOnClickListener(this);
        ll_empty = findViewById(R.id.ll_empty);
        ll_content = findViewById(R.id.ll_content);

        findViewById(R.id.btn_shopping_channel).setOnClickListener(this);
        findViewById(R.id.btn_clear).setOnClickListener(this);
        findViewById(R.id.btn_settle).setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        showCart();
    }

    // 展示购物车列表
    private void showCart() {
        // 查询购物车数据库中所有的商品记录
        mCartList = mDBHelper.queryAllCartInfo();
        if (mCartList.size() == 0) {
            return;
        }
        for (CartInfo info : mCartList) {
            // 根据商品编号查询商品数据库中的商品记录
            GoodsInfo goods = mDBHelper.queryGoodsInfoById(info.goodsId);
            mGoodsMap.put(info.goodsId, goods);
            info.goods = goods;
            cartAdapter = new CartAdapter(this, mCartList);
            ll_cart.setAdapter(cartAdapter);
            ll_cart.setOnItemClickListener(this);
            ll_cart.setOnItemLongClickListener(this);
        }

        // 重新计算购物车中的商品总金额
        refrestTotalAmount();
    }

    private void deleteGoods(CartInfo info) {
        MyApplication.getInstance().goodsCount -= info.count;
        // 从购物策划数据库中删除商品
        mDBHelper.deleteCartInfoByGoodsId(info.id);
        // 从购物车的列表中删除商品
        CartInfo removed = null;
        for (CartInfo cartInfo : mCartList) {
            if (cartInfo.goodsId == info.goodsId) {
                removed = cartInfo;
                break;
            }
        }
        mCartList.remove(removed);
        //显示最新的商品数量
        showCount();
        ToastUtil.showMsg(this, "已从购物车删除" + mGoodsMap.get(info.goodsId).name);
        mGoodsMap.remove(info.goodsId);
    }

    private void showCount() {
        tv_count.setText(String.valueOf(MyApplication.getInstance().goodsCount));
        // 购物车中没有物品 显示空空如也
        if (MyApplication.getInstance().goodsCount == 0) {
            ll_empty.setVisibility(View.VISIBLE);
            ll_content.setVisibility(View.GONE);
            cartAdapter.notifyDataSetChanged();
        } else {
            ll_content.setVisibility(View.VISIBLE);
            ll_empty.setVisibility(View.GONE);
        }
    }

    private void refrestTotalAmount() {
        int totalPrice = 0;
        for (CartInfo info : mCartList) {
            GoodsInfo goods = mGoodsMap.get(info.goodsId);
            totalPrice += goods.price * info.count;
        }
        tv_total_price.setText(String.valueOf(totalPrice));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back) {
            finish();
        } else if (v.getId() == R.id.btn_shopping_channel) {
            Intent intent = new Intent(this, ShoppingChannelActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_clear) {
            mDBHelper.deleteAllCartInfo();
            MyApplication.getInstance().goodsCount = 0;
            showCount();
            ToastUtil.showMsg(this, "购物车已清空");
        } else if (v.getId() == R.id.btn_settle) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("结算商品");
            builder.setMessage("客观抱歉，结算功能暂未上线，请下次再来");
            builder.setPositiveButton("好的", null);
            builder.create().show();
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        CartInfo info = mCartList.get(position);
        // 从购物车数据库中删除该商品
        AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingCartActivity.this);
        builder.setMessage("是否从购物车删除" + info.goods.name + "?");
        builder.setPositiveButton("是", (dialog, which) -> {
            // 从购物车列表中移除该商品
            mCartList.remove(position);
            cartAdapter.notifyDataSetChanged();
            // 重新计算购物车中的商品总金额
            refrestTotalAmount();
        });
        builder.setNegativeButton("否", null);
        builder.create().show();
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(ShoppingCartActivity.this, ShoppingDetailActivity.class);
        intent.putExtra("goods_id", mCartList.get(position).goodsId);
        startActivity(intent);
    }
}