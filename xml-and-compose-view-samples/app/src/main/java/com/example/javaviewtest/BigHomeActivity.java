package com.example.javaviewtest;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;
import com.yiwen.compose_views.ComposeActivity;
import com.yiwen.java_view_other.ScanActivity;
import com.yiwen.recyclerviewtest.HomeActivity;

import org.greenrobot.eventbus.EventBus;

import io.flutter.embedding.android.FlutterActivity;
import io.github.haoyiwen.test.core.router.URLRouter;

public class BigHomeActivity extends AppCompatActivity {
    private final int OVERLAY_PERMISSION_REQ_CODE = 1;

    static {
        System.loadLibrary("native-lib");
    }

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                if (result.getContents() == null) {
                    Toast.makeText(BigHomeActivity.this, "Cancelled", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(BigHomeActivity.this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                    URLRouter.openURL(BigHomeActivity.this, result.getContents());
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activitu_constraint_layout_view1);
        TextView tv = findViewById(R.id.tv_title);
        tv.setText(stringFromJNI());

        // 打印activity context
        Log.d("BigHomeActivity", "onCreate: " + this);
        // 打印application context
        Log.d("BigHomeActivity", "onCreate: " + getApplicationContext());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if (currentNightMode == Configuration.UI_MODE_NIGHT_NO) {
            // Night mode is not active, we're using the light theme
            Log.d("BigHomeActivity", "onCreate: light theme");
            setTheme(R.style.AppTheme);
        } else if (currentNightMode == Configuration.UI_MODE_NIGHT_YES) {
            // Night mode is active, we're using dark theme
            Log.d("BigHomeActivity", "onCreate: dark theme");
            setTheme(R.style.AppTheme_Dark);
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("大首页");
        setSupportActionBar(toolbar);

        Button btn_scan = findViewById(R.id.btn_scan);
        btn_scan.setOnClickListener(v -> {
            ScanOptions options = new ScanOptions();
            options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES);
            options.setPrompt("Scan a barcode");
            options.setCameraId(0);  // Use a specific camera of the device
            options.setBeepEnabled(true);
            options.setBarcodeImageEnabled(true);
            options.setOrientationLocked(true);
            barcodeLauncher.launch(options);
        });

        Button button = findViewById(R.id.btn_jump_home);

        button.setOnClickListener(v -> {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        Button btn_compose = findViewById(R.id.btn_jump_compose);
        btn_compose.setOnClickListener(v -> {
            Intent intent = new Intent(this, ComposeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        Button btn_jump_thrid_aar = findViewById(R.id.btn_jump_thrid_aar);
        btn_jump_thrid_aar.setOnClickListener(v -> {
            Intent intent = new Intent(this, com.example.chapter03.Chapter3BigHome.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        Button btn_jump_rn = findViewById(R.id.btn_jump_rn);
        btn_jump_rn.setOnClickListener(v -> {
            Intent intent = new Intent(this, io.github.haoyiwen.react_native_container.RNHomeActivity.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("initParam1", "value1");
            intent.putExtra("initParam2", 123);
            startActivity(intent);
        });

        Button btn_qr_code = findViewById(R.id.btn_jump_javaview_other);
        btn_qr_code.setOnClickListener(v -> {
            Intent intent = new Intent(this, com.yiwen.java_view_other.BigHomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        Button btn_jump_rn_fragment = findViewById(R.id.btn_jump_rn_fragment);
        btn_jump_rn_fragment.setOnClickListener(v -> {
            Intent intent = new Intent(this, com.example.javaviewtest.ReactNativeFragmentActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        Button btn_jump_flutter = findViewById(R.id.btn_jump_flutter);
        btn_jump_flutter.setOnClickListener(v -> {
            startActivity(
                    FlutterActivity.createDefaultIntent(this)
            );
        });

        Button btn_jump_baseloading = findViewById(R.id.btn_jump_base_loading);
        btn_jump_baseloading.setOnClickListener(v -> {
            Intent intent = new Intent(this, BaseLoadingTestActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        Button btn_third_sdk = findViewById(R.id.btn_jump_third_sdk);
        btn_third_sdk.setOnClickListener(v -> {
            Intent intent = new Intent(this, io.github.haoyiwen.third_sdk.SDKHomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        Button btn_jump_h5 = findViewById(R.id.jump_h5);
        btn_jump_h5.setOnClickListener(v -> {
            URLRouter.openURL(this, "http://www.baidu.com");
        });

        Button openurl_rn = findViewById(R.id.openurl_rn);
        openurl_rn.setOnClickListener(v -> {
            URLRouter.openURL(this, "http://127.0.0.1:8081/index.bundle?platform=android&isRN=true&moduleName=splitRn_0736");
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public native String stringFromJNI();
}