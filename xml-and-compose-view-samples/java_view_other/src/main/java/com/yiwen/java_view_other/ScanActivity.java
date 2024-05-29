package com.yiwen.java_view_other;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import io.github.haoyiwen.test.core.router.URLRouter;

public class ScanActivity extends AppCompatActivity {

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                if (result.getContents() == null) {
                    Toast.makeText(ScanActivity.this, "Cancelled", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ScanActivity.this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                    URLRouter.openURL(ScanActivity.this, result.getContents());
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_scan);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.btn_scan).setOnClickListener(v -> {
            ScanOptions options = new ScanOptions();
            options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES);
            options.setPrompt("Scan a barcode");
            options.setCameraId(0);  // Use a specific camera of the device
            options.setBeepEnabled(true);
            options.setBarcodeImageEnabled(true);
            options.setOrientationLocked(true);
            barcodeLauncher.launch(options);
        });

//        new IntentIntegrator(this).initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        if (result != null) {
//            if (result.getContents() == null) {
//                Log.d("ScanActivity", "Cancelled");
//            } else {
//                Log.d("ScanActivity", "Scanned: " + result.getContents());
//            }
//        } else {
//            Log.d("ScanActivity", "onActivityResult: " + requestCode + ", " + resultCode + ", " + data);
//            super.onActivityResult(requestCode, resultCode, data);
//        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}