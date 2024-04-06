package com.example.chapter07_client.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.content.ContextCompat;

public class PermissionUtil {
    // 检查多个权限，返回true表示已完全开启权限
    public static boolean checkPermission(Activity act, String[] permissions, int requestCode) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            int check = PackageManager.PERMISSION_GRANTED;
            for (String permission : permissions) {
                check = ContextCompat.checkSelfPermission(act, permission);
                if(check != PackageManager.PERMISSION_GRANTED) {
                    break;
                }
            }
            // 未开启该权限，请求系统弹窗，好让用户选择是否立即开启权限
            if(check != PackageManager.PERMISSION_GRANTED){
                act.requestPermissions(permissions, requestCode);
                return false;
            }
        }
        return true;
    }

    public static boolean checkGrant(int[] grantResults) {
        if(grantResults.length > 0){
            for (int grantResult : grantResults) {
                if(grantResult != PackageManager.PERMISSION_GRANTED){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
