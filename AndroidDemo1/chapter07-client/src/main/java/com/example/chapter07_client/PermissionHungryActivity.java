package com.example.chapter07_client;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chapter07_client.util.PermissionUtil;
import com.example.chapter07_client.util.ToastUtil;

public class PermissionHungryActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String[] PERMISSIONS = new String[]{
            "android.permission.READ_CONTACTS",
            "android.permission.WRITE_CONTACTS",
            "android.permission.READ_SMS",
            "android.permission.SEND_SMS",
    };

    private static final int REQUEST_CODE_ALL = 1;
    private static final int REQUEST_CODE_CONTACTS = 2;
    private static final int REQUEST_CODE_SMS = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_lazy);

        findViewById(R.id.btn_contact).setOnClickListener(this);
        findViewById(R.id.btn_sms).setOnClickListener(this);

        PermissionUtil.checkPermission(this, PERMISSIONS, REQUEST_CODE_ALL);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_contact) {
            // 读取联系人
            PermissionUtil.checkPermission(this, new String[]{PERMISSIONS[0], PERMISSIONS[1]}, REQUEST_CODE_CONTACTS);
        } else if (v.getId() == R.id.btn_sms) {
            // 读取短信
            PermissionUtil.checkPermission(this, new String[]{PERMISSIONS[2], PERMISSIONS[3]}, REQUEST_CODE_SMS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_ALL:
                // 读取联系人的权限回调
                if (PermissionUtil.checkGrant(grantResults)) {
                    Log.d("ning", "已经开启读取联系人权限");
                } else {
                    for (int i = 0; i < grantResults.length; i++) {
                        if(grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            if (permissions[i].equals("android.permission.READ_CONTACTS") ||
                                    permissions[i].equals("android.permission.WRITE_CONTACTS")) {
                                ToastUtil.showMsg(this, "用户拒绝了读取联系人权限");
                                jumpToSettings();
                                return;
                            } else if (permissions[i].equals("android.permission.READ_SMS") ||
                                    permissions[i].equals("android.permission.SEND_SMS")) {
                                ToastUtil.showMsg(this, "用户拒绝了收发短信的权限");
                                jumpToSettings();
                                return;
                            }
                        }
                    }
                }
                break;
            case REQUEST_CODE_CONTACTS:
                // 读取联系人的权限回调
                if (PermissionUtil.checkGrant(grantResults)) {
                    Log.d("ning", "已经开启读取联系人权限");
                } else {
                    ToastUtil.showMsg(this, "用户拒绝了读取联系人权限");
                    jumpToSettings();
                }
                break;
            case REQUEST_CODE_SMS:
                if (PermissionUtil.checkGrant(grantResults)) {
                    Log.d("ning", "收发短信权限已经开启");
                } else {
                    ToastUtil.showMsg(this, "收发短信的权限被拒绝");
                    jumpToSettings();
                }
                break;
        }
    }

    // 跳转到应用设置界面
    private void jumpToSettings() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", getPackageName(), null));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}