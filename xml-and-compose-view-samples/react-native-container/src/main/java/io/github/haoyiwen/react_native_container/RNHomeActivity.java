package io.github.haoyiwen.react_native_container;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import io.github.haoyiwen.test.core.activity.BaseActivity;

public class RNHomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button home = findViewById(R.id.textView);
        home.setOnClickListener(v -> {
            Intent intent = ReactNativeActivity.createIntent(this, "home", "home.android.bundle");
            startActivity(intent);
        });

        Button index = findViewById(R.id.textView2);
        index.setOnClickListener(v -> {
            Intent intent = ReactNativeActivity.createIntent(this, "MyReactNativeApp", "index.android.bundle");
            startActivity(intent);
        });

        Button online = findViewById(R.id.btn1);
        online.setOnClickListener(v -> {
            Intent intent = ReactNativeActivity.createIntent(this, "splitRn_0736", "index.android.bundle", "http://127.0.0.1:8081/index.bundle?platform=android");
            startActivity(intent);
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_rnhome;
    }

    @Override
    protected String setTitle() {
        return "React Native Home";
    }
}