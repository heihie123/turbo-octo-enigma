package com.example.latte_core.detegates.web;

import com.alibaba.fastjson.JSON;

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

    public String event(String params) {
        final String action = JSON.parseObject(params).getString("action");
        return null;
    }
}
