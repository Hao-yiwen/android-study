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

public class RNHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rnhome);
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
    }
}