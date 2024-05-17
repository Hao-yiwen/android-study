package com.example.javaviewtest;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;

public abstract class BaseActivity extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;
    LinearLayout loadingLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_base);

        LayoutInflater.from(this).inflate(getLayoutResId(), findViewById(R.id.container), true);

        lottieAnimationView = findViewById(R.id.lottieAnimationView);
        loadingLayout = findViewById(R.id.loadingLayout);
    }

    protected abstract int getLayoutResId();

    protected void showLoading() {
        if (lottieAnimationView != null) {
            loadingLayout.setVisibility(View.VISIBLE);
            lottieAnimationView.playAnimation();
        }
    }

    protected void hideLoading() {
        if (lottieAnimationView != null) {
            // 搞点动画小时时候的动画效果
            loadingLayout.animate()
                    .alpha(0f)
                    .setDuration(500)
                    .withEndAction(() -> {
                        loadingLayout.setVisibility(View.GONE);
                        lottieAnimationView.cancelAnimation();
                    })
                    .start();
        }
    }
}