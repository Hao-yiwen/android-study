package com.yiwen.java_view_other;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ColorAndColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_color_and_colors);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        getWindow().setBackgroundDrawableResource(R.drawable.android_bot);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        LinearLayout ll = findViewById(R.id.main);
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ll.setBackgroundColor(getResources().getColor(R.color.orange));
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            ll.setBackgroundColor(getResources().getColor(R.color.pink));
        }
    }
}