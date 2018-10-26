package com.example.latte_core.wechat;

import android.app.Activity;

import com.example.latte_core.app.ConfigKeys;
import com.example.latte_core.app.Latte;
import com.example.latte_core.wechat.callback.IWeChatSignInCallback;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * 微信api配置类
 */
public class LatteWeChat {

    public static final String APP_ID = Latte.getConfiguration(ConfigKeys.WE_CHAT_APP_ID);
    public static final String APP_SECRET = Latte.getConfiguration(ConfigKeys.WE_CHAT_APP_SECRET);
    private final IWXAPI mWxapi;
    private IWeChatSignInCallback mIWeChatSignInCallback = null;

    private LatteWeChat() {
        final Activity activity = Latte.getConfiguration(ConfigKeys.ACTIVITY);
        mWxapi = WXAPIFactory.createWXAPI(activity, APP_ID, true);
        mWxapi.registerApp(APP_ID);
    }

    private static final class Holder {
        private static final LatteWeChat INSTANCE = new LatteWeChat();
    }

    public static LatteWeChat getInstance() {
        return Holder.INSTANCE;
    }

    public final IWXAPI getWxapi() {
        return mWxapi;
    }

    public LatteWeChat onSignSuccess(IWeChatSignInCallback callback) {
        this.mIWeChatSignInCallback = callback;
        return this;
    }

    public IWeChatSignInCallback getSignInCallback(){
        return mIWeChatSignInCallback;
    }

    public final void signIn() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        mWxapi.sendReq(req);
    }
}
