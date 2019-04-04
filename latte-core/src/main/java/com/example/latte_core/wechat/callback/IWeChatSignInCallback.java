package com.example.latte_core.wechat.callback;

/**
 * 微信登录成功回调接口
 */
public interface IWeChatSignInCallback {
    void onSignInSuccess(String userInfo);
}
