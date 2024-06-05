package io.github.haoyiwen.react_native_container;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.facebook.react.BuildConfig;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import com.facebook.soloader.SoLoader;

import io.github.haoyiwen.react_native_container.utils.DevURL;
import io.github.haoyiwen.test.core.activity.BaseActivity;
//import com.swmansion.rnscreens.RNScreensPackage;


public class ReactNativeActivity extends BaseActivity implements DefaultHardwareBackBtnHandler, PermissionAwareActivity {
    private final int OVERLAY_PERMISSION_REQ_CODE = 1;
    private ReactRootView mReactRootView;

    private ReactInstanceManager mReactInstanceManager;
    private static final String COMPONENT_NAME_KEY = "componentName";
    private static final String BUNDLE_PATH_KEY = "bundlePath";

    private static final String DEV_URL = "devUrl";

    private static final String RN_URL = "rnUrl";

    public String getRNUrl() {
        return RNUrl;
    }

    public void setRNUrl(String RNUrl) {
        this.RNUrl = RNUrl;
    }

    private String RNUrl = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(null);
        this.showLoading();
        checkOverlayPermission();
        setupReactNativeView();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_rnbase;
    }

    @Override
    protected String setTitle() {
        return null;
    }

    private void checkOverlayPermission() {
        if (BuildConfig.DEBUG && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
        }
    }

    private void setupReactNativeView() {
        String componentName = getIntent().getStringExtra(COMPONENT_NAME_KEY);
        String bundlePath = getIntent().getStringExtra(BUNDLE_PATH_KEY);
        String devUrl = getIntent().getStringExtra(DEV_URL);
        String rnUrl = getIntent().getStringExtra(RN_URL);
        SoLoader.init(this, false);
        mReactRootView = new ReactRootView(this);
        // 不使用新架构
        mReactRootView.setIsFabric(false);
        if (rnUrl != null) {
            setRNUrl(rnUrl);
        }

        mReactInstanceManager = ((MyReactNativeApplication) getApplication()).createReactInstanceManager(bundlePath, devUrl, componentName);
        if (mReactInstanceManager == null) {
            hideLoading();
            showErrorPage("url有误~");
            return;
        }

        try {
            mReactRootView.startReactApplication(mReactInstanceManager, componentName, null);
            mReactRootView.addOnLayoutChangeListener((v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) -> {
                if (bottom != oldBottom) {
                    hideLoading();
                }
            });
        } catch (Exception e) {
            Log.e("ReactNativeActivity", "setupReactNativeView: ", e);
            Toast.makeText(this, "ReactNativeActivity setupReactNativeView error", Toast.LENGTH_SHORT).show();
            MyReactNativeApplication.getInstance().destroyReactInstanceManager(componentName);
            hideLoading();
            showErrorPage("页面未加载~");
        }

        FrameLayout mainContainer = findViewById(R.id.main_container);
        mainContainer.addView(mReactRootView);

        if (BuildConfig.DEBUG) {
            addDebugIcon(mainContainer);
        }
    }

    private void addDebugIcon(FrameLayout mainContainer) {
        ImageView debugIcon = new ImageView(this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                100,
                100
        );
        layoutParams.gravity = Gravity.END | Gravity.CENTER_VERTICAL;
        layoutParams.setMargins(16, 16, 16, 16);
        debugIcon.setPadding(10, 10, 10, 10);
        debugIcon.setLayoutParams(layoutParams);
        debugIcon.setBackgroundResource(R.drawable.rounded_corner); // Set your rounded corner background
        debugIcon.setImageResource(R.drawable.react); // Set your debug icon image
        debugIcon.setScaleType(ImageView.ScaleType.CENTER_CROP);

        debugIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle debug icon click
                Toast.makeText(ReactNativeActivity.this, "Debug icon clicked", Toast.LENGTH_SHORT).show();
                showDebugPopup(v);
            }
        });

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            debugIcon.setOutlineProvider(new ViewOutlineProvider() {
                @Override
                public void getOutline(View view, Outline outline) {
                    outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 20f);
                }
            });
            debugIcon.setClipToOutline(true);
        }

        mainContainer.addView(debugIcon);
    }

    private void showDebugPopup(View anchorView) {
        Context self = this;
        View popupView = LayoutInflater.from(this).inflate(R.layout.debug_popup, null);
        PopupWindow popupWindow = new PopupWindow(popupView, FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);

        Button btnPageReturn = popupView.findViewById(R.id.btn_page_return);
        Button btnPageUrl = popupView.findViewById(R.id.btn_page_url);
        Button btnRnDebug = popupView.findViewById(R.id.btn_rn_debug);

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
                String url = getRNUrl();
                text.setText("url: " + (url != null ? url : "null"));

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

        btnRnDebug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle open RN debug menu
                if (mReactInstanceManager != null) {
                    mReactInstanceManager.showDevOptionsDialog();
                }
                popupWindow.dismiss();
            }
        });

        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(anchorView, -anchorView.getWidth(), -anchorView.getHeight());
    }

    public static Intent createIntent(Context context, String componentName, String bundlePath, String rnurl) {
        Intent intent = new Intent(context, ReactNativeActivity.class);
        intent.putExtra(COMPONENT_NAME_KEY, componentName);
        intent.putExtra(BUNDLE_PATH_KEY, bundlePath);
        intent.putExtra(RN_URL, rnurl);
        return intent;
    }

    public static Intent createIntent(Context context, String componentName, String bundlePath, String url, String rnurl) {
        Intent intent = new Intent(context, ReactNativeActivity.class);
        intent.putExtra(COMPONENT_NAME_KEY, componentName);
        intent.putExtra(BUNDLE_PATH_KEY, bundlePath);
        intent.putExtra(DEV_URL, url);
        intent.putExtra(RN_URL, rnurl);
        return intent;
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostPause(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostResume(this, this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostDestroy(this);
            mReactInstanceManager = null;  // 清理 ReactInstanceManager
        }
        if (mReactRootView != null) {
            mReactRootView.unmountReactApplication();
            mReactRootView = null;  // 清理 ReactRootView
        }
        MyReactNativeApplication.getInstance().destroyReactInstanceManager(getIntent().getStringExtra(COMPONENT_NAME_KEY));
    }


    @Override
    public void onBackPressed() {
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU && mReactInstanceManager != null) {
            mReactInstanceManager.showDevOptionsDialog();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    private PermissionListener listener;

    @Override
    public void requestPermissions(String[] permissions, int requestCode, PermissionListener listener) {
        this.listener = listener;
        requestPermissions(permissions, requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (listener != null) {
            listener.onRequestPermissionsResult(requestCode, permissions, grantResults);
            listener = null;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
