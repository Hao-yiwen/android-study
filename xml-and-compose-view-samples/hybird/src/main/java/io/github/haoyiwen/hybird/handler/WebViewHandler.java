package io.github.haoyiwen.hybird.handler;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import io.github.haoyiwen.hybird.WebViewActivity;
import io.github.haoyiwen.test.core.router.URLHandler;

public class WebViewHandler implements URLHandler {
    @Override
    public boolean canHandle(String url) {
        return (url.startsWith("http") || url.startsWith("https")) && !url.contains("isRN=true");
    }

    @Override
    public void handle(Context context, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }
}
