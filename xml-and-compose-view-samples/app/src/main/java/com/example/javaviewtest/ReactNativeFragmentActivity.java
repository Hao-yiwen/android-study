package com.example.javaviewtest;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.facebook.react.ReactFragment;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;

public class ReactNativeFragmentActivity extends AppCompatActivity implements DefaultHardwareBackBtnHandler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_react_native_fragment);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button button = findViewById(R.id.button_rn);
        button.setOnClickListener(v -> {
            Fragment reactNativeFragment = new ReactFragment.Builder()
                    .setComponentName("HelloWorld")
                    .setLaunchOptions(getLaunchOptions("test"))
                    .setFabricEnabled(false)
                    .build();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.reactNativeFragment, reactNativeFragment)
                    .commit();
        });
    }

    private Bundle getLaunchOptions(String message) {
        Bundle initialProperties = new Bundle();
        initialProperties.putString("message", message);
        return initialProperties;
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }
}