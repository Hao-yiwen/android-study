package io.github.haoyiwen.react_native_container;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.hermes.intl.DateTimeFormat;
import com.facebook.hermes.reactexecutor.HermesExecutorFactory;
import com.facebook.react.BuildConfig;
import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactInstanceManagerBuilder;
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

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Arrays;


public class ReactNativeActivity extends ReactActivity {
    private ReactInstanceManager mReactInstanceManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    protected String getMainComponentName() {
        return "MyReactNativeApp";
    }

    @Override
    protected ReactActivityDelegate createReactActivityDelegate() {
        return new DefaultReactActivityDelegate(this, getMainComponentName(), false) {
            @Nullable
            @Override
            protected Bundle getLaunchOptions() {
                Bundle initialProperties = new Bundle();
                initialProperties.putString("initParam", "value");
                return initialProperties;
            }
        };
    }

    public ReactInstanceManager getmReactInstanceManager() {
        return mReactInstanceManager;
    }

    @Override
    public AssetManager getAssets() {
        return super.getAssets();
    }
}
