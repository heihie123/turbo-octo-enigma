package com.example.latte_core.detegates.web;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * 初始化web的接口
 */
public interface IWebviewInitializer {

    WebView initWebView(WebView webView);
    // 针对浏览器
    WebViewClient initWebViewClient();
    // 针对页面
    WebChromeClient initWebChromeClient();
}
