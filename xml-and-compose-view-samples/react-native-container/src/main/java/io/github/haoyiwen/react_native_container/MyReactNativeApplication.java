package io.github.haoyiwen.react_native_container;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.facebook.hermes.reactexecutor.HermesExecutorFactory;
import com.facebook.react.BuildConfig;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.shell.MainReactPackage;
import com.reactnativecommunity.webview.RNCWebViewPackage;
import com.swmansion.rnscreens.RNScreensPackage;
import com.th3rdwave.safeareacontext.SafeAreaContextPackage;
import com.facebook.soloader.SoLoader;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

import io.github.haoyiwen.hybird.handler.WebViewHandler;
import io.github.haoyiwen.test.core.bus.EventBusManager;
import io.github.haoyiwen.test.core.bus.events.URLEvent;
import io.github.haoyiwen.test.core.router.URLHandler;

public class MyReactNativeApplication extends Application {
    private static MyReactNativeApplication instance;
    private ConcurrentHashMap<String, ReactInstanceManager> mReactInstanceManagers = new ConcurrentHashMap<>();

    private ArrayList<URLHandler> handlers = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        SoLoader.init(this, false);
        initializeFlipper(this);
        addHandlers();
    }

    public void addHandlers() {
        // URL处理handler添加
        EventBusManager.getInstance().register(this);
        handlers.add(new WebViewHandler());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onURLEvent(URLEvent event) {
        Uri uri = Uri.parse(event.url);
        for (URLHandler handler : handlers) {
            if (handler.canHandle(uri)) {
                handler.handle(event.context, uri);
                return;
            }
        }
        // 处理没有匹配的URL
        // 可以添加一个默认的处理或者显示错误信息
        Log.e("MyReactNativeApplication", "onURLEvent: " + event.url + " not handled");
    }

    public synchronized ReactInstanceManager createReactInstanceManager(String bundlePath) {
        ReactInstanceManager instanceManager = mReactInstanceManagers.get(bundlePath);
        if (instanceManager == null) {
            instanceManager = ReactInstanceManager.builder()
                    .setApplication(this)
                    .setBundleAssetName(bundlePath)
                    .setJSMainModulePath("index")
                    .addPackages(Arrays.asList(
                            new MyReactPackage(),
                            new MainReactPackage(),
                            new RNCWebViewPackage(),
                            new RNScreensPackage(),
                            new SafeAreaContextPackage()))
                    .setUseDeveloperSupport(BuildConfig.DEBUG)
                    .setInitialLifecycleState(LifecycleState.BEFORE_CREATE)
                    .setJavaScriptExecutorFactory(new HermesExecutorFactory())
                    .build();
            instanceManager.createReactContextInBackground();
            mReactInstanceManagers.put(bundlePath, instanceManager);
        }
        return instanceManager;
    }

    public static void initializeFlipper(Context context) {
        if (BuildConfig.DEBUG) {
            try {
                Class<?> aClass = Class.forName("io.github.haoyiwen.react_native_container.ReactNativeFlipper");
                aClass.getMethod("initializeFlipper", Context.class, ReactInstanceManager.class).invoke(null, context);
            } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                     InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public static MyReactNativeApplication getInstance() {
        return instance;
    }
}