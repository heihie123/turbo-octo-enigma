package com.example.latte_core.detegates.web;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * 初始化web的接口
 */
public class WebviewInitializer {

    @SuppressLint({"ObsoleteSdkInt", "SetJavaScriptEnabled"})
    public WebView createWebView(WebView webView) {
        // web可以调试
        WebView.setWebContentsDebuggingEnabled(true);
        // 开启cookie
        final CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.setAcceptThirdPartyCookies(webView, true);
        }
        CookieManager.setAcceptFileSchemeCookies(true);
        // 不能横向滚动
        webView.setHorizontalScrollBarEnabled(false);
        // 不能纵向滚动
        webView.setVerticalScrollBarEnabled(false);
        // 允许截图
        webView.setDrawingCacheEnabled(true);
        // 拦截长按事件
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });

        final WebSettings settings = webView.getSettings();
        // 支持js
        settings.setJavaScriptEnabled(true);
        // 使劲儿中ua
        final String ua = settings.getUserAgentString();
        settings.setUserAgentString(ua + "Latte");
        // 隐藏缩放控件
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
        // 禁止缩放
        settings.setSupportZoom(false);
        // 是否允许访问文件
        settings.setAllowFileAccess(true);
        // 是否允许在WebView中访问内容
        settings.setAllowContentAccess(true);
        // 是否允许运行在一个URL环境（the context of a file scheme URL）中的JavaScript访问来自其他URL环境的内容
        // 为了保证安全，应该不允许
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        // 缓存相关
        settings.setAppCacheEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        // DOM存储API是否可用，默认false
        settings.setDomStorageEnabled(true);
        // 是否支持数据库
        settings.setDatabaseEnabled(true);
        return webView;
    }
}
