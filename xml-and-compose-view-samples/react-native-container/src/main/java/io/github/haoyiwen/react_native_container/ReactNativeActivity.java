package io.github.haoyiwen.react_native_container;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;

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


public class ReactNativeActivity extends AppCompatActivity implements DefaultHardwareBackBtnHandler {
    private final int OVERLAY_PERMISSION_REQ_CODE = 1;
    private ReactRootView mReactRootView;

    private ReactInstanceManager mReactInstanceManager;
    private static final String COMPONENT_NAME_KEY = "componentName";
    private static final String BUNDLE_PATH_KEY = "bundlePath";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
            }
        }
        String componentName = getIntent().getStringExtra(COMPONENT_NAME_KEY);
        String bundlePath = getIntent().getStringExtra(BUNDLE_PATH_KEY);
        SoLoader.init(this, false);
        mReactRootView = new ReactRootView(this);

        mReactInstanceManager = ((MyReactNativeApplication) getApplication()).createReactInstanceManager(bundlePath);

        mReactRootView.startReactApplication(mReactInstanceManager, componentName, null);
        setContentView(mReactRootView);
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
