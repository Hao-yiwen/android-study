package io.github.haoyiwen.react_native_container;

import android.app.Application;

import androidx.annotation.Nullable;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.defaults.DefaultReactNativeHost;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;
import com.reactnativecommunity.webview.RNCWebViewPackage;
import com.swmansion.rnscreens.RNScreensPackage;
import com.th3rdwave.safeareacontext.SafeAreaContextPackage;

import java.util.Arrays;
import java.util.List;

public class MyReactNativeApplication extends Application implements ReactApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, false);
    }

    private final ReactNativeHost mReactNativeHost = new DefaultReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
        }

        protected List<ReactPackage> getPackages() {
            List<ReactPackage> packages = Arrays.<ReactPackage>asList(
                    new MainReactPackage(),
                    new RNCWebViewPackage(),
                    new MyReactPackage(),
                    new RNScreensPackage(),
                    new SafeAreaContextPackage()
            );
            // Packages that cannot be autolinked yet can be added manually here
            return packages;
        }

        @Override
        protected String getJSMainModuleName() {
            return "index";
        }

        @Override
        protected boolean isNewArchEnabled() {
            return false;
        }

        @Nullable
        @Override
        protected Boolean isHermesEnabled() {
            return true;
        }

        @Nullable
        @Override
        protected String getBundleAssetName() {
            return "index.android.bundle";
        }

        @Override
        protected ReactInstanceManager createReactInstanceManager() {
            return super.createReactInstanceManager();
        }
    };



    // todo多实例化
    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }

}
