package com.yiwen.java_view_other;

import static io.github.haoyiwen.test.core.util.UIUtils.showToast;

import android.Manifest;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import io.github.haoyiwen.test.core.activity.BaseActivity;
import io.github.haoyiwen.test.core.listener.PermissionListener;

public class PermissonTestActivity extends BaseActivity {

    private static final String[] PERMISSIONS = {
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestRuntimePermission(PERMISSIONS, new PermissionListener() {
            @Override
            public void onGranted() {
                Toast.makeText(PermissonTestActivity.this, "所有权限都被授权", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDenied(List<String> deniedPermission) {
                for (String permission : deniedPermission) {
                    Toast.makeText(PermissonTestActivity.this, "被拒绝的权限：" + permission, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_permisson_test;
    }

    @Override
    protected String setTitle() {
        return "权限测试页面";
    }
}