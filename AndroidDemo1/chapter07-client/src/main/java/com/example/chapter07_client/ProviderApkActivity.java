package com.example.chapter07_client;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import com.example.chapter07_client.util.PermissionUtil;
import com.example.chapter07_client.util.ToastUtil;

import java.io.File;

public class ProviderApkActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String[] PERMISSIONS = new String[]{
            "android.permission.REQUEST_INSTALL_PACKAGES",
    };

    private static final int REQUEST_INSTALL_PACKAGES = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_apk);
        findViewById(R.id.btn_install).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Log.d("ning", "Build.VERSION.SDK_INT: " + Build.VERSION.SDK_INT);
            checkAndInstall();
        } else {
            if (PermissionUtil.checkPermission(this, PERMISSIONS, REQUEST_INSTALL_PACKAGES)) {
                installApk();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_INSTALL_PACKAGES && PermissionUtil.checkGrant(grantResults)) {
            installApk();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    private void checkAndInstall() {
        try {
            if (!Environment.isExternalStorageManager()) {
                Log.d("ning", "Environment.isExternalStorageManager(): " + Environment.isExternalStorageManager());
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.fromParts("package", getPackageName(), null));
                startActivity(intent);
            } else {
                installApk();
            }
        } catch (Exception e) {

        }
    }

    private void installApk() {
        String apkPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/app-debug.apk";
        Log.d("ning", "apkPath: " + apkPath);
        // 获取应用包管理器
        PackageManager pm = getPackageManager();
        PackageInfo pi = pm.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
        if (pi == null) {
            ToastUtil.showMsg(this, "未找到安装包");
            return;
        }
        Uri uri = Uri.parse(apkPath);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // 7.0及以上版本
            uri = FileProvider.getUriForFile(this, getString(R.string.file_provider), new File(apkPath));
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        startActivity(intent);
    }
}