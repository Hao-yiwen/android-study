package com.example.chapter08.util;

import android.content.Context;

public class Utils {

    // 从dp转化为px
    public static int dip2px(Context context, float dpValue){
        // 获取像素密度
        float density = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue * density + 0.5f);
    }
}
