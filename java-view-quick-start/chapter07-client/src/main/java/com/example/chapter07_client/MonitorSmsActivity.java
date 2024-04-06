package com.example.chapter07_client;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.jetbrains.annotations.Nullable;

public class MonitorSmsActivity extends AppCompatActivity {

    private SmsGetObserver smsGetObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor_sms);
        // 给指定uri注册内容观察期 一旦发生数据变化 就会触发观察期的onChange方法
        Uri uri = Uri.parse("content://sms");
        smsGetObserver = new SmsGetObserver(this);
        getContentResolver().registerContentObserver(uri, true, smsGetObserver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getContentResolver().unregisterContentObserver(smsGetObserver);
    }

    private static class SmsGetObserver extends ContentObserver {
        private final Context mContent;

        public SmsGetObserver(Context ctx) {
            super(new Handler(Looper.getMainLooper()));
            this.mContent = ctx;
        }

        @Override
        @SuppressLint("Range")
        public void onChange(boolean selfChange, @Nullable Uri uri) {
            super.onChange(selfChange);
            if (uri == null) {
                return;
            }
            if (uri.toString().contains("content://sms/raw") || uri.toString().equals("content://sms")) {
                return;
            }

            Cursor cursor = mContent.getContentResolver().query(uri, new String[]{"address", "date", "body"}, null, null, "date desc");
            if (cursor.moveToNext()) {
                String sender = cursor.getString(cursor.getColumnIndex("address"));
                String date = cursor.getString(cursor.getColumnIndex("date"));
                String body = cursor.getString(cursor.getColumnIndex("body"));
                Log.d("ning", "sender: " + sender + " date: " + date + " body: " + body);
            }
            cursor.close();
        }
    }
}