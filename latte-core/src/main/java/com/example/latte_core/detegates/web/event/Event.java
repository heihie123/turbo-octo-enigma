package com.example.latte_core.detegates.web.event;

import android.content.Context;
import android.webkit.WebView;

import com.example.latte_core.detegates.web.WebDelegate;

/**
 * js事件的bean
 */
public abstract class Event implements IEvent {

    private Context mContext = null;
    private String mAction = null;
    private WebDelegate mWebDelegate = null;
    private String mUrl = null;
    private WebView mWebView = null;

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    public String getAction() {
        return mAction;
    }

    public void setAction(String action) {
        this.mAction = action;
    }

    public WebDelegate getWebDelegate() {
        return mWebDelegate;
    }

    public void setWebDelegate(WebDelegate webDelegate) {
        this.mWebDelegate = webDelegate;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public WebView getWebView() {
        return mWebView;
    }

    public void setWebView(WebView webView) {
        this.mWebView = webView;
    }
}
