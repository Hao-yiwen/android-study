package com.yiwen.java_view_other;

import android.graphics.Outline;
import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import io.github.haoyiwen.test.core.activity.BaseActivity;

public class ElevationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textView3 = findViewById(R.id.textView3);
//        TextView textView4 = findViewById(R.id.textView4);

//        textView3.setOutlineProvider(new ViewOutlineProvider() {
//            @Override
//            public void getOutline(View view, Outline outline) {
//                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 0);
//            }
//        });
//
//        textView4.setOutlineProvider(new ViewOutlineProvider() {
//            @Override
//            public void getOutline(View view, Outline outline) {
//                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 0);
//            }
//        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_elevation;
    }

    @Override
    protected String setTitle() {
        return "ElevationActivity";
    }
}