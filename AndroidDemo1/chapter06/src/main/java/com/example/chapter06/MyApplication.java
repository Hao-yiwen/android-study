package com.example.chapter06;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.example.chapter06.dao.BookDao;
import com.example.chapter06.database.BookDatabase;
import com.example.chapter06.enity.BookInfo;

import java.util.HashMap;
import java.util.Stack;

public class MyApplication extends Application {

    private static MyApplication myApp;

    public HashMap<String, String> infoMap = new HashMap<>();

    // 生命一个书籍数据库对象
    private BookDatabase bookDatabase;

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

    public BookDatabase getBookDatabase(){
        return bookDatabase;
    }
}
