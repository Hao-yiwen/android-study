package com.example.chapter08.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.chapter08.enity.BillInfo;
import com.example.chapter08.enity.CartInfo;
import com.example.chapter08.enity.GoodsInfo;

import java.util.ArrayList;
import java.util.List;

public class BillDBHelper extends SQLiteOpenHelper {
    // 数据库名称
    private static final String DB_NAME = "bill.db";
    private static final int DB_VERSION = 1;
    // 账单信息表
    private static final String TABLE_BILLS_INFO = "bill_info";
    private static BillDBHelper mHelper = null;
    private SQLiteDatabase mRDB = null;
    private SQLiteDatabase mWDB = null;

    private BillDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static BillDBHelper getInstance(Context context) {
        if (mHelper == null) {
            mHelper = new BillDBHelper(context);
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
        String sql1 = "CREATE TABLE IF NOT EXISTS " + TABLE_BILLS_INFO + " (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " date VARCHAR NOT NULL," +
                " type INTEGER NOT NULL," +
                " amount DOUBLE NOT NULL," +
                " remark VARCHAR NOT NULL);";
        db.execSQL(sql1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    public long save(BillInfo bill) {
        ContentValues cv = new ContentValues();
        cv.put("date", bill.date);
        cv.put("type", bill.type);
        cv.put("amount", bill.amount);
        cv.put("remark", bill.remark);
        return mWDB.insert(TABLE_BILLS_INFO, null, cv);
    }

    @SuppressLint("Range")
    public List<BillInfo> queryByMonth(String yearMonth) {
        List<BillInfo> list = new ArrayList<>();
        String sql = "select * from " + TABLE_BILLS_INFO + " where date like '" + yearMonth + "%'";
        Log.d("ning", sql);
        Cursor cursor = mRDB.rawQuery(sql, null);
        Log.d("ning", "cursor.getCount()=" + cursor.getCount());
        while (cursor.moveToNext()) {
            BillInfo billInfo = new BillInfo();
            billInfo.id = cursor.getInt(cursor.getColumnIndex("_id"));
            billInfo.date = cursor.getString(cursor.getColumnIndex("date"));
            billInfo.type = cursor.getInt(cursor.getColumnIndex("type"));
            billInfo.amount = cursor.getDouble(cursor.getColumnIndex("amount"));
            billInfo.remark = cursor.getString(cursor.getColumnIndex("remark"));
            list.add(billInfo);
        }
        return list;
    }
}
