package io.github.haoyiwen.hybird.handler;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import io.github.haoyiwen.hybird.WebViewActivity;
import io.github.haoyiwen.test.core.router.URLHandler;

public class WebViewHandler implements URLHandler {
    @Override
    public boolean canHandle(Uri uri) {
        return uri.getScheme() != null && (uri.getScheme().equals("http") || uri.getScheme().equals("https"));
    }

    @Override
    public void handle(Context context, Uri uri) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("url", uri.toString());
        context.startActivity(intent);
    }
}
