package com.example.latte_core.detegates.web.client;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.latte_core.app.ConfigKeys;
import com.example.latte_core.app.Latte;
import com.example.latte_core.detegates.web.IPageLoadListener;
import com.example.latte_core.detegates.web.WebDelegate;
import com.example.latte_core.detegates.web.route.Router;
import com.example.latte_core.ui.loader.LatteLoader;
import com.example.latte_core.ui.loader.LoaderStyle;
import com.example.latte_core.util.storage.LattePreference;

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
        return Router.getInstance().handleWebUrl(DELEGATE, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadStart();
        }
        LatteLoader.showLoading(view.getContext(), LoaderStyle.PacmanIndicator);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        syncCoolies();
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadEnd();
        }
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LatteLoader.stopLoading();
            }
        }, 1000);
    }

    /**
     * 缓存cookie
     */
    private void syncCoolies() {
        final CookieManager cookieManager = CookieManager.getInstance();
        final String webHost = Latte.getConfiguration(ConfigKeys.WEB_HOST);
        if (webHost != null) {
            if (cookieManager.hasCookies()) {
                final String cookieStr = cookieManager.getCookie(webHost);
                if (!TextUtils.isEmpty(cookieStr)) {
                    LattePreference.addCustomAppProfile("cookies", cookieStr);
                }
            }
        }
    }

    public void setIPageLoadListener(IPageLoadListener listener) {
        this.mIPageLoadListener = listener;
    }
}
