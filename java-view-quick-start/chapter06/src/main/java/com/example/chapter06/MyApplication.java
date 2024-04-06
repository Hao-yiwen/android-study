package com.example.chapter06;

import android.app.Application;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.example.chapter06.dao.BookDao;
import com.example.chapter06.database.BookDatabase;
import com.example.chapter06.database.ShoppingDBHelper;
import com.example.chapter06.enity.BookInfo;
import com.example.chapter06.enity.GoodsInfo;
import com.example.chapter06.util.FileUtil;
import com.example.chapter06.util.SharedUtil;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class MyApplication extends Application {

    private static MyApplication myApp;

    public HashMap<String, String> infoMap = new HashMap<>();

    // 生命一个书籍数据库对象
    public BookDatabase bookDatabase;

    // 购物车中的商品总数量
    public int goodsCount;

    public static MyApplication getInstance() {
        return myApp;
    }

    //App启动时调用
    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
        Log.d("ning", "onCreate");

        // 构建书籍数据库实例
        bookDatabase = Room.databaseBuilder(this, BookDatabase.class, "book")
                .addMigrations()
                .allowMainThreadQueries()
                .build();

        // 初始化商品信息
        initGoodsInfo();
    }

    private void initGoodsInfo() {
        // 获取共享参数判断是否是首次打开
        boolean isFirst = SharedUtil.getInstance(this).readBoolean("first", true);
        // 获取当前app的私有下载路径
        String directory = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + File.separatorChar;
        if (isFirst) {
            // 模拟网络图片的下载
            List<GoodsInfo> list = GoodsInfo.getDefaultList();
            for (GoodsInfo info: list){
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), info.pic);
                String path = directory + info.id + ".jpg";
                FileUtil.saveImage(path, bitmap);
                // 回收位图
                bitmap.recycle();
                info.pic_path = path;
            }
            // 打开数据库 将商品信息存入到数据库中
            ShoppingDBHelper dbHelper = ShoppingDBHelper.getInstance(this);
            dbHelper.openWriteLink();
            dbHelper.insertGoodsInfos(list);
            dbHelper.closeLink();
            // 把是否首次打开写入共享参数
            SharedUtil.getInstance(this).writeBoolean("first", false);
        }
    }

    //App终止时调用
    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d("ning", "onTerminate");
    }

    //配置改变时候调用
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("ning", "onConfigurationChanged");
    }

    public BookDatabase getBookDatabase() {
        return bookDatabase;
    }
}
