package com.example.latte_ec.pay;

/**
 * 支付宝支付回调接口
 */
public interface IAlPayResultListener {

    void onPaySuccess();

    void onPaying();

    void onPayFail();

    void onPayCancel();

    void onPayConnectError();
}
