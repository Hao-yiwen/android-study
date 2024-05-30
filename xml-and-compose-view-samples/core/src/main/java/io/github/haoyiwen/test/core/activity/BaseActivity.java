package io.github.haoyiwen.test.core.activity;


import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;

import io.github.haoyiwen.test.core.R;
import io.github.haoyiwen.test.core.storage.Storage;


public abstract class BaseActivity extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;
    LinearLayout pageLayout;
    TextView page_title;

    Storage storage;

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

        storage = Storage.getInstance(this);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_base);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        String title = setTitle();
        if(title!=null && !title.isEmpty()){
            toolbar.setTitle(title);
            setSupportActionBar(toolbar);
            if (!isTaskRoot()) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        } else {
            toolbar.setVisibility(View.GONE);
        }

        LayoutInflater.from(this).inflate(getLayoutResId(), findViewById(R.id.container), true);

        lottieAnimationView = findViewById(R.id.lottieAnimationView);
        page_title = findViewById(R.id.page_title);
        pageLayout = findViewById(R.id.pageLayout);
    }

    protected abstract int getLayoutResId();

    protected abstract String setTitle();

    protected void showLoading() {
        if (lottieAnimationView != null) {
            pageLayout.setVisibility(View.VISIBLE);
            lottieAnimationView.playAnimation();
        }
    }

    protected void hideLoading() {
        if (lottieAnimationView != null) {
            // 搞点动画小时时候的动画效果
            pageLayout.animate()
                    .alpha(0f)
                    .setDuration(500)
                    .withEndAction(() -> {
                        pageLayout.setVisibility(View.GONE);
                        lottieAnimationView.cancelAnimation();
                    })
                    .start();
        }
    }

    protected void showErrorPage(String message) {
        if (lottieAnimationView != null) {
            pageLayout.setVisibility(View.VISIBLE);
            lottieAnimationView.setAnimation(R.raw.error_page);
            lottieAnimationView.playAnimation();
            page_title.setText(message);
        }
    }

    protected void hideErrorPage() {
        if (lottieAnimationView != null) {
            pageLayout.setVisibility(View.GONE);
            lottieAnimationView.cancelAnimation();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}