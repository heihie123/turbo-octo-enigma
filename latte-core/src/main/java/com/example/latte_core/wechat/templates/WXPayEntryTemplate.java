package com.example.latte_core.wechat.templates;

import com.example.latte_core.util.ToastUtils;
import com.example.latte_core.wechat.BaseWXPayEntryActivity;
import com.tencent.mm.opensdk.modelbase.BaseReq;

/**
 * 微信支付回调Activity
 */
public class WXPayEntryTemplate extends BaseWXPayEntryActivity {

    @Override
    protected void onPaySuccess() {
        ToastUtils.showShotToast("支付成功");
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onPayFail() {
        ToastUtils.showShotToast("支付失败");
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onPayCancel() {
        ToastUtils.showShotToast("支付取消");
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }
}
