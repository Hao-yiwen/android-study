package com.example.chapter06;

import static android.provider.CalendarContract.CalendarCache.URI;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chapter06.database.ShoppingDBHelper;
import com.example.chapter06.enity.GoodsInfo;
import com.example.chapter06.util.ToastUtil;

import org.w3c.dom.Text;

import java.util.List;

public class ShoppingChannelActivity extends AppCompatActivity {
    // 声明一个商品数据库的帮助对象
    private ShoppingDBHelper mDBHelper;
    private TextView tv_count;
    private GridLayout gl_channel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_channel);
        TextView tv_title = findViewById(R.id.tv_title);
        tv_title.setText("手机商城");

        tv_count = findViewById(R.id.tv_count);
        gl_channel = findViewById(R.id.gl_channel);
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
        // 商品条目是一个线性布局，设置布局的宽度为屏幕的一半
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(screenWidth / 2, LinearLayout.LayoutParams.WRAP_CONTENT);
        // 查询数据库中所有的商品记录
        List<GoodsInfo> list = mDBHelper.queryAllGoodsInfo();
        for (GoodsInfo info : list) {
            // 获取布局文件item_goods.xml的根试图
            View view = LayoutInflater.from(this).inflate(R.layout.item_goods, null);
            ImageView iv_thumb = view.findViewById(R.id.iv_thumb);
            TextView tv_name = view.findViewById(R.id.tv_name);
            TextView tv_price = view.findViewById(R.id.tv_price);
            Button btn_add = view.findViewById(R.id.btn_add);

            iv_thumb.setImageURI(Uri.parse(info.pic_path));
            tv_name.setText(info.name);
            tv_price.setText(String.valueOf((int) info.price));

            btn_add.setOnClickListener(v -> {
                addToCart(info.id, info.name);
            });
            gl_channel.addView(view, params);
        }
    }

    // 把指定编号的商品添加到购物车
    private void addToCart(int id, String name) {
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
}