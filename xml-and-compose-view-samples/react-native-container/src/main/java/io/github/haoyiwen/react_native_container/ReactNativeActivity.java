package io.github.haoyiwen.react_native_container;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Outline;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.react.BuildConfig;
import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.defaults.DefaultReactActivityDelegate;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;
import com.reactnativecommunity.webview.RNCWebViewPackage;
import com.swmansion.rnscreens.RNScreensPackage;
import com.th3rdwave.safeareacontext.SafeAreaContextPackage;
//import com.swmansion.rnscreens.RNScreensPackage;

import java.util.Arrays;
import java.util.List;

import io.github.haoyiwen.test.core.activity.BaseActivity;


public class ReactNativeActivity extends BaseActivity implements DefaultHardwareBackBtnHandler {
    private final int OVERLAY_PERMISSION_REQ_CODE = 1;
    private ReactRootView mReactRootView;

    private ReactInstanceManager mReactInstanceManager;
    private static final String COMPONENT_NAME_KEY = "componentName";
    private static final String BUNDLE_PATH_KEY = "bundlePath";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rnbase);
        checkOverlayPermission();
        setupReactNativeView();
    }

    private void checkOverlayPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
        }
    }

    private void setupReactNativeView() {
        String componentName = getIntent().getStringExtra(COMPONENT_NAME_KEY);
        String bundlePath = getIntent().getStringExtra(BUNDLE_PATH_KEY);
        SoLoader.init(this, false);
        mReactRootView = new ReactRootView(this);

        mReactInstanceManager = ((MyReactNativeApplication) getApplication()).createReactInstanceManager(bundlePath);
        mReactRootView.startReactApplication(mReactInstanceManager, componentName, null);
        FrameLayout mainContainer = findViewById(R.id.main_container);
        mainContainer.addView(mReactRootView);

        if (BuildConfig.DEBUG) {
            addDebugIcon(mainContainer);
        }
    }

    private void addDebugIcon(FrameLayout mainContainer) {
        ImageView debugIcon = new ImageView(this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                100, // Width
                100  // Height
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
                // Handle show page URL
                Toast.makeText(ReactNativeActivity.this, "Current URL: " + "your_page_url_here", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
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

    public static Intent createIntent(Context context, String componentName, String bundlePath) {
        Intent intent = new Intent(context, ReactNativeActivity.class);
        intent.putExtra(COMPONENT_NAME_KEY, componentName);
        intent.putExtra(BUNDLE_PATH_KEY, bundlePath);
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
}
