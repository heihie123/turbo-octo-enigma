package com.example.latte_core.detegates.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.example.latte_core.detegates.web.event.Event;
import com.example.latte_core.detegates.web.event.EventManager;

/**
 * 处理js交互
 */
public class LatteWebInterface {

    private final WebDelegate DELEGATE;

    private LatteWebInterface(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    static LatteWebInterface create(WebDelegate delegate) {
        return new LatteWebInterface(delegate);
    }

    @JavascriptInterface
    public String event(String params) {
        final String action = JSON.parseObject(params).getString("action");
        final Event event = EventManager.getInstance().createEvent(action);
        if(event != null){
            event.setAction(action);
            event.setWebDelegate(DELEGATE);
            event.setContext(DELEGATE.getActivityContext());
            event.setUrl(DELEGATE.getUrl());
            return event.execute(params);
        }
        return null;
    }
}
