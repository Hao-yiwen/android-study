package io.github.haoyiwen.test.core.router;

import android.content.Context;

import io.github.haoyiwen.test.core.bus.EventBusManager;
import io.github.haoyiwen.test.core.bus.events.URLEvent;

public class URLRouter {

    /**
     * 打开URL
     * @param context
     * @param url
     * @decription 使用总线将url发送到对应的模块处理
     */
    public static void openURL(Context context, String url) {
        // 如果url是以http或者https开头，那么就在webview中打开
        // 如果url是以/rn_开头，则使用rn模块处理
        // 如果url是以/flutter_开头，则在flutter模块处理
        // 如果url是以/app开头，则跳转至对应的activity
        EventBusManager.getInstance().post(new URLEvent(url));
    }
}
