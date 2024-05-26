package io.github.haoyiwen.react_native_container;

import android.app.Application;
import android.content.Context;

import androidx.annotation.Nullable;

import com.facebook.react.BuildConfig;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.defaults.DefaultReactNativeHost;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;
import com.reactnativecommunity.webview.RNCWebViewPackage;
import com.swmansion.rnscreens.RNScreensPackage;
import com.th3rdwave.safeareacontext.SafeAreaContextPackage;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class MyReactNativeApplication extends Application {
    private ReactInstanceManager mReactInstanceManager;
    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, false);
        // 添加flipper插件
        this.initializeFlipper(this);
    }

    public List<ReactPackage> getReactPackages() {
        return Arrays.<ReactPackage>asList(
                new MainReactPackage(),
                new RNCWebViewPackage(),
                new MyReactPackage(),
                new RNScreensPackage(),
                new SafeAreaContextPackage()
        );
    }

    // 在你的 MyReactNativeApplication 类中添加
    public ReactInstanceManager createReactInstanceManager(String bundlePath) {
        // 检查是否需要创建新的实例或重用现有的
        if (mReactInstanceManager != null) {
            mReactInstanceManager.destroy();
            mReactInstanceManager = null;
        }
        if (mReactInstanceManager == null) {
            mReactInstanceManager = ReactInstanceManager.builder()
                    .setApplication(this)
                    .setBundleAssetName(bundlePath)
                    .setJSMainModulePath("index")
                    .addPackages(Arrays.asList(
                            new MainReactPackage(),
                            new RNCWebViewPackage(),
                            new RNScreensPackage(),
                            new SafeAreaContextPackage()))
                    .setUseDeveloperSupport(BuildConfig.DEBUG)
                    .setInitialLifecycleState(LifecycleState.BEFORE_CREATE)
                    .build();
        }
        return mReactInstanceManager;
    }


    // 通过反射添加flipper插件
    public static void initializeFlipper(Context context) {
        if (BuildConfig.DEBUG) {
            try {
                Class<?> aClass = Class.forName("io.github.haoyiwen.react_native_container.ReactNativeFlipper");
                aClass
                        .getMethod("initializeFlipper", Context.class, ReactInstanceManager.class)
                        .invoke(null, context);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

}
