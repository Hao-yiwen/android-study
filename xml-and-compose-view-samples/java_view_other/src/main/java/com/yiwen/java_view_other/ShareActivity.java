package com.yiwen.java_view_other;

import android.content.Intent;
import android.graphics.ImageFormat;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        View myView = findViewById(R.id.shareActivityLayout);
        if (myView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(myView, (v, insets) -> {
                // 逻辑处理
                return insets;
            });
        } else {
            Log.e("TAG", "视图初始化失败，myView未找到");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if (intent == null) {
            Log.d("ShareActivity", "onResume: intent is null");
            return;
        }
        String action = intent.getAction();
        String type = intent.getType();

        LinearLayout ll = (LinearLayout) findViewById(R.id.shareActivityLayout);
        if (ll == null) {
            Log.d("ShareActivity", "onResume: ll is null");
        }

        ImageView image = new ImageView(this);

        TextView textView = new TextView(this);

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
                if (sharedText != null) {
                    textView.setText(sharedText);
                }
            } else if (type.startsWith("image/")) {
                // Handle single image being sent
                Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
                image.setImageURI(imageUri);
            }
        } else {
            // Handle other intents, such as being started from the home screen
            image.setImageResource(R.drawable.android_bot);
            textView.setText("无内容分享!");
        }

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        image.setLayoutParams(layoutParams);
        textView.setLayoutParams(layoutParams);

        ll.addView(image);
        ll.addView(textView);
    }
}