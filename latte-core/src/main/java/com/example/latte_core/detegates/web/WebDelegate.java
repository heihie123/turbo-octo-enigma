package com.example.latte_core.detegates.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.latte_core.app.ConfigKeys;
import com.example.latte_core.app.Latte;
import com.example.latte_core.detegates.LatteDelegate;
import com.example.latte_core.detegates.web.route.RouteKeys;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Objects;

import androidx.annotation.Nullable;

/**
 * web delegate基类
 */
public abstract class WebDelegate extends LatteDelegate implements IWebviewInitializer {

    private WebView mWebView = null;
    private final ReferenceQueue<WebView> WEB_VIEW_QUENE = new ReferenceQueue<>();
    private String mUrl = null;
    private boolean mIsWebViewAvailable = false;
    private LatteDelegate mTopDelegate = null;

    public WebDelegate() {

    }

    protected abstract IWebviewInitializer setInitializer();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle bundle = getArguments();
        mUrl = Objects.requireNonNull(bundle).getString(RouteKeys.URL.name());
        initWebview();
    }

    @SuppressLint("JavascriptInterface")
    private void initWebview() {
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
        } else {
            // webview不要写在xml中初始化，容易内存泄漏
            final IWebviewInitializer initializer = setInitializer();
            if (initializer != null) {
                final WeakReference<WebView> webViewWeakReference =
                        new WeakReference<>(new WebView(getActivityContext()), WEB_VIEW_QUENE);
                mWebView = webViewWeakReference.get();
                mWebView = initializer.initWebView(mWebView);
                mWebView.setWebViewClient(initializer.initWebViewClient());
                mWebView.setWebChromeClient(initializer.initWebChromeClient());
                final String name = Latte.getConfiguration(ConfigKeys.JAVASCRIPT_INTERFACE);
                mWebView.addJavascriptInterface(LatteWebInterface.create(this), name);
                mIsWebViewAvailable = true;
            } else {
                throw new NullPointerException("Initializer IS NULL!");
            }
        }
    }

    public void setTopDelegate(LatteDelegate delegate) {
        mTopDelegate = delegate;
    }

    public LatteDelegate getTopDelegate() {
        if (mTopDelegate == null) {
            mTopDelegate = this;
        }
        return mTopDelegate;
    }

    public WebView getWebView() {
        if (mWebView == null) {
            throw new NullPointerException("WebView IS NULL!");
        }
        return mIsWebViewAvailable ? mWebView : null;
    }

    public String getUrl() {
        if (mWebView == null) {
            throw new NullPointerException("Url IS NULL!");
        }
        return mUrl;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mWebView != null) {
            mIsWebViewAvailable = false;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }
}
