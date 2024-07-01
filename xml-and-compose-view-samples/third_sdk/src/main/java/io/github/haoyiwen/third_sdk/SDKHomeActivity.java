package io.github.haoyiwen.third_sdk;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import io.github.haoyiwen.test.core.activity.BaseActivity;
import io.github.haoyiwen.third_sdk.mapview.MapsActivity;

public class SDKHomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConstraintLayout layout = findViewById(R.id.sdk_root);

        Button button = layout.findViewById(R.id.sdk_button);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(this, io.github.haoyiwen.third_sdk.NavigateWXActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        MaterialButton mapBtn = new MaterialButton(this);
        mapBtn.setText("打开地图");
        mapBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MapsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
        layout.addView(mapBtn);

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.topMargin = (int) getResources().getDimension(R.dimen.margin_40);
        layoutParams.topToBottom = button.getId();
        layoutParams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID;
        mapBtn.setLayoutParams(layoutParams);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_sdkhome;
    }

    @Override
    protected String setTitle() {
        return "SDKHomeActivity";
    }

    @Override
    public boolean enableSlideClose() {
        return false;
    }
}