package com.yiwen.java_view_other;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.yiwen.java_view_other.R;

public class LoadingActivity extends AppCompatActivity {

    private LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(com.yiwen.java_view_other.R.layout.activity_loading);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(com.yiwen.java_view_other.R.id.loading_container), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, TextLoadingFragment.newInstance("1", "2")).commit();
        }

        lottieAnimationView = findViewById(R.id.lottieAnimationView);

        lottieAnimationView.playAnimation();

        new android.os.Handler().postDelayed(() -> {
            lottieAnimationView.cancelAnimation();
        }, 3000);
    }
}