package com.example.latte_ec.sign;

/**
 * 登录/注册后接口回调
 */
public interface ISignListener {
    void onSignIn(boolean isSuccess);
    void onSignUp(boolean isSuccess);
}
