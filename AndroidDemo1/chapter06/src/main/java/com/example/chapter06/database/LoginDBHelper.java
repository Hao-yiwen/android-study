package com.example.chapter06.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.chapter06.enity.LoginInfo;
import com.example.chapter06.enity.User;

import java.util.ArrayList;
import java.util.List;

public class LoginDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "login.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "login_info";
    private static LoginDBHelper mHelper = null;
    private SQLiteDatabase mRDB = null;
    private SQLiteDatabase mWDB = null;

    private LoginDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static LoginDBHelper getInstance(Context context) {
        if (mHelper == null) {
            mHelper = new LoginDBHelper(context);
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
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " phone VARCHAR NOT NULL," +
                " password VARCHAR NOT NULL," +
                " remember INTEGER NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        String sql = "ALTER TABLE " + TABLE_NAME + " ADD COLUMN phone VARCHAR;";
//        db.execSQL(sql);
//        sql = "ALTER TABLE " + TABLE_NAME + " ADD COLUMN password VARCHAR;";
//        db.execSQL(sql);
    }

    public void save() {
        // 如果存在则删除 再添加
    }

    public long insert(LoginInfo info) {
        ContentValues values = new ContentValues();
        values.put("phone", info.phone);
        values.put("password", info.password);
        values.put("remember", info.remember);
        return mWDB.insert(TABLE_NAME, null, values);
    }

    public long deleteByName(String name) {
        return mWDB.delete(TABLE_NAME, "name=?", new String[]{name});
    }

    public long updateByName(User user) {
        ContentValues values = new ContentValues();
        values.put("age", user.age);
        values.put("height", user.height);
        values.put("weight", user.weight);
        values.put("married", user.married);
        values.put("update_time", System.currentTimeMillis());
        return mWDB.update(TABLE_NAME, values, "name=?", new String[]{user.name});
    }

    public List<User> queryAll() {
        List<User> list = new ArrayList<>();
        Cursor cursor = mRDB.query(TABLE_NAME, null, null, null, null, null, "update_time DESC");
        while (cursor.moveToNext()) {
            User user = new User();
            user.id = cursor.getInt(0);
            user.name = cursor.getString(1);
            user.age = cursor.getInt(2);
            user.height = cursor.getLong(3);
            user.weight = cursor.getFloat(4);
            user.married = cursor.getInt(5) == 1;
            list.add(user);
        }
        return list;
    }

    public List<User> queryByName(String name) {
        List<User> list = new ArrayList<>();
        Cursor cursor = mRDB.query(TABLE_NAME, null, "name=?", new String[]{name}, null, null, "update_time DESC");
        while (cursor.moveToNext()) {
            User user = new User();
            user.id = cursor.getInt(0);
            user.name = cursor.getString(1);
            user.age = cursor.getInt(2);
            user.height = cursor.getLong(3);
            user.weight = cursor.getFloat(4);
            user.married = cursor.getInt(5) == 1;
            list.add(user);
        }
        return list;
    }
}
