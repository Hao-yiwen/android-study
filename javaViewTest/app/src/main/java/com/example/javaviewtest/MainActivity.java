package com.example.javaviewtest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView textView = findViewById(R.id.textView);
        textView.post(new Runnable() {
            @Override
            public void run() {
                Log.d("MainActivity", "textView.getMeasuredHeight() = " + textView.getMeasuredHeight() + " textview.getMeasuredWidth() = " + textView.getMeasuredWidth());
                Log.d("MainActivity", "textView.getHeight() = " + textView.getHeight() + " textview.getWidth() = " + textView.getWidth());
            }
        });
    }
}