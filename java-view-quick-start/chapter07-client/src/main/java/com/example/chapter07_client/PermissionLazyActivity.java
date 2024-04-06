package com.example.chapter07_client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import com.example.chapter07_client.util.PermissionUtil;
import com.example.chapter07_client.util.ToastUtil;

import kotlin.jvm.internal.PropertyReference0Impl;

public class PermissionLazyActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String[] PERMISSIONS_CONTACTS = new String[]{
            "android.permission.READ_CONTACTS",
            "android.permission.WRITE_CONTACTS"
    };

    private static final String[] PERMISSIONS_SMS = new String[]{
            "android.permission.READ_SMS",
            "android.permission.SEND_SMS",
    };

    private static final int REQUEST_CODE_CONTACTS = 1;
    private static final int REQUEST_CODE_SMS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_lazy);

        findViewById(R.id.btn_contact).setOnClickListener(this);
        findViewById(R.id.btn_sms).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_contact) {
            // 读取联系人
            PermissionUtil.checkPermission(this, PERMISSIONS_CONTACTS, REQUEST_CODE_CONTACTS);
        } else if (v.getId() == R.id.btn_sms) {
            // 读取短信
            PermissionUtil.checkPermission(this, PERMISSIONS_SMS, REQUEST_CODE_SMS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
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