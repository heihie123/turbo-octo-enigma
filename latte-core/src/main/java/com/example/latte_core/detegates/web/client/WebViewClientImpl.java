package com.example.latte_core.detegates.web.client;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.latte_core.detegates.web.IPageLoadListener;
import com.example.latte_core.detegates.web.WebDelegate;
import com.example.latte_core.detegates.web.route.Router;

/**
 * WebViewClient实现类
 */
public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate DELEGATE;
    private IPageLoadListener mIPageLoadListener = null;

    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return super.shouldOverrideUrlLoading(view, request);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return Router.getInstance().handleWebview(DELEGATE, url);
    }
}
