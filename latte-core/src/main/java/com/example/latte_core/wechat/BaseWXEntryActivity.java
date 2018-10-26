package com.example.latte_core.wechat;

import com.example.latte_core.net.rx.RxRestClient;
import com.example.latte_core.ui.loader.LatteLoader;
import com.example.latte_core.ui.loader.LoaderStyle;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 微信回调父类
 */
public abstract class BaseWXEntryActivity extends BaseWXActivity {

    protected abstract void onSignInSuccess(String userInfo);

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        final String code = ((SendAuth.Resp) baseResp).code;
        final StringBuilder anthUrl = new StringBuilder();
        anthUrl.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=")
                .append(LatteWeChat.APP_ID)
                .append("&secret=")
                .append(LatteWeChat.APP_SECRET)
                .append("&code=")
                .append(code)
                .append("&grant_type=authorization_code");
        // 请求微信服务器
        getAuth(anthUrl.toString());
    }

    private void getAuth(String anthUrl) {
        Observable<String> observable = RxRestClient.builder()
                .url(anthUrl)
                .loader(this, LoaderStyle.BallPulseIndicator)
                .build()
                .get();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        onSignInSuccess(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LatteLoader.stopLoading();
                    }

                    @Override
                    public void onComplete() {
                        LatteLoader.stopLoading();
                    }
                });
    }
}
