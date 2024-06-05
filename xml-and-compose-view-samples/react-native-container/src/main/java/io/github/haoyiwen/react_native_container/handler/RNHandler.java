package io.github.haoyiwen.react_native_container.handler;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import io.github.haoyiwen.react_native_container.ReactNativeActivity;
import io.github.haoyiwen.test.core.router.URLHandler;

public class RNHandler implements URLHandler {
    @Override
    public boolean canHandle(String url) {
        return url.contains("isRN=true");
    }

    /**
     * 处理RN的URL
     *
     * @param context
     * @param url
     * @decription 本地url 例如 http://localhost:8081/index.bundle?platform=android&isRN=true&moduleName=xxx
     * @decription 静态url 例如 /rn_xxx/yyyy.jsbundle?isRN=true
     * 解释: xxx是component的名字
     * index.jsbundle是静态资源名字
     * isRN=true表示是RN的URL
     */
    @Override
    public void handle(Context context, String url) {
        // 本地url
        if (url.startsWith("http")) {
            String moduleName = Uri.parse(url).getQueryParameter("moduleName");
            Intent intent = ReactNativeActivity.createIntent(context, moduleName, null, url, url);
            context.startActivity(intent);
        } else {
            // 静态url
            // /rn_xrn_0741/xrn_0741.android.bundle?platform=android&isRN=true&moduleName=splitRn_0736
            String moduleName = url.substring(url.indexOf("/rn_") + 4, url.lastIndexOf("/"));
            String bundleName = url.substring(url.lastIndexOf("/") + 1, url.indexOf("?"));
            Intent intent = ReactNativeActivity.createIntent(context, moduleName, bundleName, url);
            context.startActivity(intent);
        }
    }
}
