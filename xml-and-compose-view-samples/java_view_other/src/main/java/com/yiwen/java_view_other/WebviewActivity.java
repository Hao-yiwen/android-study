package com.yiwen.java_view_other;

import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;

import com.yiwen.java_view_other.webviewInterface.WebAppInterface;

import org.greenrobot.eventbus.Subscribe;

import io.github.haoyiwen.test.core.activity.BaseActivity;
import io.github.haoyiwen.test.core.bus.events.URLEvent;

public class WebviewActivity extends BaseActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());

        // Load a local HTML file or a remote URL
        webView.loadUrl("file:///android_asset/index.html");

        webView.addJavascriptInterface(new WebAppInterface(this), "Android");
        this.hideLoading();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_webview;
    }

    @Override
    protected String setTitle() {
        return "WebView";
    }

    @Subscribe
    public void onURLEvent(URLEvent event) {
        Uri uri = Uri.parse(event.url);
        if(uri.getScheme().equals("http") || uri.getScheme().equals("https")) {
            webView.loadUrl(event.url);
        }
    }
}