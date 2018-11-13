package com.example.latte_core.detegates.web.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.URLUtil;
import android.webkit.WebView;

import com.example.latte_core.detegates.LatteDelegate;
import com.example.latte_core.detegates.web.WebDelegate;
import com.example.latte_core.detegates.web.WebDelegateImpl;

import androidx.core.content.ContextCompat;

/**
 * 处理url
 */
public class Router {

    private Router() {

    }

    public static Router getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final Router INSTANCE = new Router();
    }

    // false：webview处理，true：自己处理
    public final boolean handleWebUrl(WebDelegate delegate, String url) {
        if (url.contains("tel:")) {
            callphone(delegate.getActivityContext(), url);
            return true;
        }
        final LatteDelegate topDelegate = delegate.getTopDelegate();
        final WebDelegateImpl webDelegate = WebDelegateImpl.create(url);
        topDelegate.getSupportDelegate().start(webDelegate);
        return true;
    }

    private void callphone(Context context, String uri) {
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri data = Uri.parse(uri);
        intent.setData(data);
        ContextCompat.startActivity(context, intent, null);
    }

    public final void loadPage(WebDelegate delegate, String url) {
        loadPage(delegate.getWebView(), url);
    }

    private void loadPage(WebView webView, String url) {
        if (URLUtil.isNetworkUrl(url) || URLUtil.isAssetUrl(url)) {
            loadWebPage(webView, url);
        } else {
            loadLocalPage(webView, url);
        }
    }

    private void loadLocalPage(WebView webView, String url) {
        loadWebPage(webView, "file:///android_asset/" + url);
    }

    private void loadWebPage(WebView webView, String url) {
        if (webView != null) {
            webView.loadUrl(url);
        } else {
            throw new NullPointerException("WebView IS NULL!");
        }
    }
}
