package com.example.chapter06.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.chapter06.enity.CartInfo;
import com.example.chapter06.enity.GoodsInfo;
import com.example.chapter06.enity.LoginInfo;
import com.example.chapter06.enity.User;

import java.util.ArrayList;
import java.util.List;

public class ShoppingDBHelper extends SQLiteOpenHelper {
    // 数据库名称
    private static final String DB_NAME = "shopping.db";
    private static final int DB_VERSION = 1;
    // 商品信息表
    private static final String TABLE_GOODS_INFO = "goods_info";
    // 购物车信息表
    private static final String TABLE_CART_INFO = "cart_info";
    private static ShoppingDBHelper mHelper = null;
    private SQLiteDatabase mRDB = null;
    private SQLiteDatabase mWDB = null;

    private ShoppingDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static ShoppingDBHelper getInstance(Context context) {
        if (mHelper == null) {
            mHelper = new ShoppingDBHelper(context);
        }
        return mHelper;
    }

    public SQLiteDatabase openReadLink() {
        if (mRDB == null || !mRDB.isOpen()) {
            mRDB = mHelper.getReadableDatabase();
        }
        return mRDB;
    }

    public SQLiteDatabase openWriteLink() {
        if (mWDB == null || !mWDB.isOpen()) {
            mWDB = mHelper.getWritableDatabase();
        }
        return mWDB;
    }

    public void closeLink() {
        if (mRDB != null && mRDB.isOpen()) {
            mRDB.close();
            mRDB = null;
        }
        if (mWDB != null && mWDB.isOpen()) {
            mWDB.close();
            mWDB = null;
        }
    }

    // 创建数据
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建商品信息表
        String sql1 = "CREATE TABLE IF NOT EXISTS " + TABLE_GOODS_INFO + " (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " name VARCHAR NOT NULL," +
                " description VARCHAR NOT NULL," +
                " price FLOAT NOT NULL," +
                " pic_path VARCHAR NOT NULL);";
        db.execSQL(sql1);
        // 创建购物车信息表
        String sql2 = "CREATE TABLE IF NOT EXISTS " + TABLE_CART_INFO + " (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " goods_id VARCHAR NOT NULL," +
                " count INTEGER NOT NULL);";
        db.execSQL(sql2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // 添加多条商品信息
    public void insertGoodsInfos(List<GoodsInfo> list) {
        try {
            mWDB.beginTransaction();
            for (GoodsInfo info : list) {
                ContentValues values = new ContentValues();
                values.put("name", info.name);
                values.put("description", info.description);
                values.put("price", info.price);
                values.put("pic_path", info.pic_path);
                mWDB.insert(TABLE_GOODS_INFO, null, values);
            }
            mWDB.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mWDB.endTransaction();
        }
    }

    public List<GoodsInfo> queryAllGoodsInfo() {
        String sql = "select * from " + TABLE_GOODS_INFO;
        List<GoodsInfo> list = new ArrayList<>();
        Cursor cursor = mRDB.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            GoodsInfo info = new GoodsInfo();
            info.id = cursor.getInt(0);
            info.name = cursor.getString(1);
            info.description = cursor.getString(2);
            info.price = cursor.getFloat(3);
            info.pic_path = cursor.getString(4);
            list.add(info);
        }
        cursor.close();
        return list;
    }

    // 把商品添加到购物车
    public void insertGoodsInfos(int goodsId) {
        CartInfo cartInfo = queryCartInfoByGoodsId(goodsId);
        ContentValues values = new ContentValues();
        values.put("goods_id", goodsId);
        if (cartInfo == null) {
            // 如果购物车不存在该商品，添加一条信息
            values.put("count", 1);
            mWDB.insert(TABLE_CART_INFO, null, values);
        } else {
            // 如果购物车存在该商品，更新商品数量
            values.put("_id", cartInfo.id);
            values.put("count", cartInfo.count + 1);
            mWDB.update(TABLE_CART_INFO, values, "_id=?", new String[]{String.valueOf(cartInfo.id)});
        }
    }

    private CartInfo queryCartInfoByGoodsId(int goodsId) {
        Cursor cursor = mRDB.query(TABLE_CART_INFO, null, "goods_id=?", new String[]{String.valueOf(goodsId)}, null, null, null);
        CartInfo info = null;
        if (cursor.moveToNext()) {
            info = new CartInfo();
            info.id = cursor.getInt(0);
            info.goodsId = cursor.getInt(1);
            info.count = cursor.getInt(2);
        }
        return info;
    }

    public int queryCartInfoCount() {
        int count = 0;
        String sql = "select sum(count) from " + TABLE_CART_INFO;
        Cursor cursor = mRDB.rawQuery(sql, null);
        if (cursor.moveToNext()) {
            count = cursor.getInt(0);
        }
        return count;
    }

    //查询购物车中的所有信息表
    public List<CartInfo> queryAllCartInfo() {
        List<CartInfo> list = new ArrayList<>();
        Cursor cursor = mRDB.query(TABLE_CART_INFO, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            CartInfo info = new CartInfo();
            info.id = cursor.getInt(0);
            info.goodsId = cursor.getInt(1);
            info.count = cursor.getInt(2);
            list.add(info);
        }
        return list;
    }

    // 根据商品id查询商品信息
    public GoodsInfo queryGoodsInfoById(int goodsId) {
        GoodsInfo info = null;
        Cursor cursor = mRDB.query(TABLE_GOODS_INFO, null, "_id=?", new String[]{String.valueOf(goodsId)}, null, null, null);
        if (cursor.moveToNext()) {
            info = new GoodsInfo();
            info.id = cursor.getInt(0);
            info.name = cursor.getString(1);
            info.description = cursor.getString(2);
            info.price = cursor.getFloat(3);
            info.pic_path = cursor.getString(4);
        }
        return info;
    }

    public void deleteCartInfoByGoodsId(int id) {
        mWDB.delete(TABLE_CART_INFO, "goods_id=?", new String[]{String.valueOf(id)});
    }

    public void deleteAllCartInfo() {
        mWDB.delete(TABLE_CART_INFO, "1=1", null);
    }
}
