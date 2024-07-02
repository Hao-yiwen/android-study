package io.github.haoyiwen.test.core.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import io.github.haoyiwen.test.core.R;
import io.github.haoyiwen.test.core.listener.PermissionListener;
import io.github.haoyiwen.test.core.storage.Storage;
import io.github.haoyiwen.test.core.BuildConfig;
import io.github.haoyiwen.test.core.util.UIUtils;


public abstract class BaseActivity extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;
    LinearLayout pageLayout;
    TextView page_title;

    Storage storage;

    ConstraintLayout mainContainer;

    String appUrl = "";

    long mPreTime = 0;

    protected  Bundle savedInstanceState;

    public PermissionListener mPermissionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        storage = Storage.getInstance(this);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_base);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // 这是 rgba
        int themeResId = this.getResources().getIdentifier("md_theme_primary", "color", this.getPackageName());
        int statusBarColor = ContextCompat.getColor(this, themeResId);
        Log.d("BaseActivity", "Status bar color: " + Integer.toHexString(statusBarColor));
        this.getWindow().setStatusBarColor(statusBarColor);

        Toolbar toolbar = findViewById(R.id.toolbar);
        String title = setTitle();
        if (title != null && !title.isEmpty()) {
            toolbar.setTitle(title);
            setSupportActionBar(toolbar);
            if (!isTaskRoot()) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        } else {
            toolbar.setVisibility(View.GONE);
        }

        LayoutInflater.from(this).inflate(getLayoutResId(), findViewById(R.id.fragmentContainer), true);

        lottieAnimationView = findViewById(R.id.lottieAnimationView);
        page_title = findViewById(R.id.page_title);
        pageLayout = findViewById(R.id.pageLayout);

        // 添加app几倍debug按钮
        addDebugIcon();

        // 获取appurl
        this.appUrl = getAppUrl(this);

        // 开启侧滑&&点击两次退出按钮才能退出app
        handlePressBack();

        // 存储saveInstanceState
        this.savedInstanceState = savedInstanceState;
    }

    private void handlePressBack() {
        // 双击退出app
        long currentTime = System.currentTimeMillis();
        Context self = this;
        this.getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                try {
                    String bigHomeActivityClassName = "com.example.javaviewtest.BigHomeActivity";
                    Class<?> bigHomeActivityClass = Class.forName(bigHomeActivityClassName);
                    if (!enableSlideClose()) {
                        return;
                    } else if (bigHomeActivityClass.isInstance(self)) {
                        if (currentTime - mPreTime > 2000) {
                            Toast.makeText(self, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                            mPreTime = currentTime;
                        } else {
                            finish();
                        }
                    } else {
                        finish();
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    finish();
                }
            }
        });
    }

    private String getAppUrl(Context context) {
        // 获取appurl
        String moduleName = this.getClass().getName();
        // 读取 JSON 文件
        String jsonString = loadJSONFromAssets(context, "yiwen_app_activities.json");
        if (jsonString != null && moduleName != null) {
            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                String keyByValue = JsonUtils.getKeyByValue(jsonObject, moduleName);
                if (keyByValue == null) {
                    Toast.makeText(context, "className not found", Toast.LENGTH_LONG).show();
                    return null;
                } else {
                    return "yiwen://app?moduleName=" + keyByValue;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(context, "activities.json not found", Toast.LENGTH_LONG).show();
        }
        return null;
    }

    private void addDebugIcon() {
        if (BuildConfig.DEBUG) {
            // 初始化 mainContainer
            mainContainer = findViewById(R.id.main);

            // 创建 ImageView
            ImageView imageView = new ImageView(this);
            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(120, 120);
            imageView.setLayoutParams(layoutParams);
            // 使用 Glide 加载圆形图片
            Glide.with(this)
                    .load(R.drawable.debug)
                    .apply(RequestOptions.circleCropTransform())
                    .into(imageView);
            // 设置 ID 以便应用约束
            imageView.setId(View.generateViewId());
            mainContainer.addView(imageView);
            // 应用约束条件
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(mainContainer);
            // 将 ImageView 约束到父布局的右侧和垂直中心
            constraintSet.connect(imageView.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 20);
            constraintSet.connect(imageView.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0);
            constraintSet.connect(imageView.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0);
            constraintSet.setHorizontalBias(imageView.getId(), 0.9f); // 1.0f 表示靠右
            constraintSet.setVerticalBias(imageView.getId(), 0.6f); // 0.5f 表示垂直居中
            // 应用约束
            constraintSet.applyTo(mainContainer);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle debug icon click
                    Toast.makeText(BaseActivity.this, "Debug icon clicked", Toast.LENGTH_SHORT).show();
                    showDebugPopup(v);
                }
            });
        }
    }

    private void showDebugPopup(View anchorView) {
        Context self = this;
        View popupView = LayoutInflater.from(this).inflate(io.github.haoyiwen.test.core.R.layout.base_debug_popup, null);
        PopupWindow popupWindow = new PopupWindow(popupView, FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);

        Button btnPageReturn = popupView.findViewById(R.id.btn_page_return);
        Button btnPageUrl = popupView.findViewById(R.id.btn_page_url);

        btnPageReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle page return
                finish(); // Finish the activity
                popupWindow.dismiss();
            }
        });

        btnPageUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popupView1 = LayoutInflater.from(self).inflate(R.layout.popup_url, null);
                PopupWindow popupWindow1 = new PopupWindow(popupView1, FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
                TextView text = popupView1.findViewById(R.id.url_text_view);
                String url = "url: " + appUrl;
                url = url + "\n" + "currentActivity: " + self.getClass().getPackage().getName() + self.getClass().getSimpleName();
                text.setText(url);

                // Ensure the new popup window is focusable and dismissible
                popupWindow1.setFocusable(true);
                popupWindow1.setOutsideTouchable(true);
                popupWindow1.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                // Close the original popup window
                popupWindow.dismiss();

                // Show the new popup window
                popupWindow1.showAtLocation(v, Gravity.CENTER, 0, 0);
            }
        });

        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(anchorView, -anchorView.getWidth(), -anchorView.getHeight());
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

    private String loadJSONFromAssets(Context context, String fileName) {
        String json = null;
        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open(fileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public boolean enableSlideClose() {
        return true;
    }

    public void requestRuntimePermission(String[] permissions, PermissionListener permissionListener){
        mPermissionListener = permissionListener;
        List<String> permissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }

        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(this, permissionList.toArray(new String[permissionList.size()]), 1);
        } else {
            permissionListener.onGranted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if(grantResults.length > 0){
                    List<String> deniedPermissions = new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++) {
                        int grantResult = grantResults[i];
                        String permission = permissions[i];
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            deniedPermissions.add(permission);
                        }
                    }
                    if (deniedPermissions.isEmpty()) {
                        mPermissionListener.onGranted();
                    } else {
                        mPermissionListener.onDenied(deniedPermissions);
                    }
                }
        }
    }
}