package com.example.latte_core.detegates.web;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.latte_core.detegates.web.chromeclient.WebChromeClientImpl;
import com.example.latte_core.detegates.web.client.WebViewClientImpl;
import com.example.latte_core.detegates.web.route.RouteKeys;
import com.example.latte_core.detegates.web.route.Router;

import androidx.annotation.Nullable;

/**
 * web delegate
 */
public class WebDelegateImpl extends WebDelegate {

    private IPageLoadListener mIPageLoadListener = null;

    public static WebDelegateImpl create(String url) {
        final Bundle bundle = new Bundle();
        bundle.putString(RouteKeys.URL.name(), url);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(bundle);
        return delegate;
    }

    @Override
    public Object setLayout() {
        return getWebView();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {
        if (getUrl() != null) {
            Router.getInstance().loadPage(this, getUrl());
        }
    }

    @Override
    protected IWebviewInitializer setInitializer() {
        return this;
    }

    @Override
    public WebView initWebView(WebView webView) {
        return new WebviewInitializer().createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        WebViewClientImpl webViewClient = new WebViewClientImpl(this);
        webViewClient.setIPageLoadListener(mIPageLoadListener);
        return webViewClient;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }

    public void setIPageLoadListener(IPageLoadListener listener) {
        this.mIPageLoadListener = listener;
    }
}
