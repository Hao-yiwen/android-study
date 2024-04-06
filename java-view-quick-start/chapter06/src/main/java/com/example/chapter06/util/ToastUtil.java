package com.example.chapter06.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    public static void showMsg(Context ctx, String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
    }
}
