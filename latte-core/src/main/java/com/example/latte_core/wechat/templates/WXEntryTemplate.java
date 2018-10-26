package com.example.latte_core.wechat.templates;

import com.example.latte_core.wechat.BaseWXEntryActivity;
import com.example.latte_core.wechat.LatteWeChat;

/**
 * 微信回调Activity
 */
public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        LatteWeChat.getInstance().getSignInCallback().onSignInSuccess(userInfo);
    }
}
