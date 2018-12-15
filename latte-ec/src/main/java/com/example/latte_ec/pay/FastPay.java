package com.example.latte_ec.pay;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.latte_core.app.ConfigKeys;
import com.example.latte_core.app.Latte;
import com.example.latte_core.detegates.LatteDelegate;
import com.example.latte_core.net.rx.RxRestClient;
import com.example.latte_core.wechat.LatteWeChat;
import com.example.latte_ec.R;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import androidx.appcompat.app.AlertDialog;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 支付弹窗
 */
public class FastPay implements View.OnClickListener {

    private IAlPayResultListener mIAlPayResultListener = null;
    private Activity mActivity = null;
    private AlertDialog mDialog = null;

    private int mOrderId = -1;

    public FastPay(LatteDelegate delegate) {
        this.mActivity = delegate.getProxyActivity();
        this.mDialog = new AlertDialog.Builder(delegate.getContext()).create();
    }

    public static FastPay create(LatteDelegate delegate) {
        return new FastPay(delegate);
    }

    public void beginPayDialog() {
        mDialog.show();
        final Window window = mDialog.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_pay_panel);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            window.setAttributes(params);

            window.findViewById(R.id.icon_ali_pay).setOnClickListener(this);
            window.findViewById(R.id.icon_weixin_pay).setOnClickListener(this);
            window.findViewById(R.id.btn_pay_cancel).setOnClickListener(this);
        }
    }

    public FastPay setPayResultListener(IAlPayResultListener listener) {
        this.mIAlPayResultListener = listener;
        return this;
    }

    public FastPay setOrderId(int orderId) {
        this.mOrderId = orderId;
        return this;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.icon_ali_pay) {
            onAlPayClick(mOrderId);
            mDialog.cancel();
        } else if (id == R.id.icon_weixin_pay) {
            onWeixinPayClick(mOrderId);
            mDialog.cancel();
        } else if (id == R.id.btn_pay_cancel) {
            mDialog.cancel();
        }
    }

    private void onAlPayClick(int orderID) {
        final String alPayUrl = "服务器地址" + orderID;
        RxRestClient.builder()
                .url(alPayUrl)
                .build()
                .post()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        final String paySign = JSON.parseObject(s).getString("result");
                        PayAsyncTask payAsyncTask = new PayAsyncTask(mActivity, mIAlPayResultListener);
                        payAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, paySign);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void onWeixinPayClick(int orderID) {
        final String weChatPrePayUrl = "服务器地址" + orderID;
        final IWXAPI iwxapi = LatteWeChat.getInstance().getWxapi();
        final String appId = Latte.getConfiguration(ConfigKeys.WE_CHAT_APP_ID);
        iwxapi.registerApp(appId);
        RxRestClient.builder()
                .url(weChatPrePayUrl)
                .build()
                .post()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        final JSONObject result = JSON.parseObject(s).getJSONObject("result");
                        final String prepayId = result.getString("prepayid");
                        final String partnerId = result.getString("partnerid");
                        final String packageValue = result.getString("package");
                        final String timestamp = result.getString("timestamp");
                        final String nonceStr = result.getString("noncestr");
                        final String paySign = result.getString("sign");

                        final PayReq payReq = new PayReq();
                        payReq.appId = appId;
                        payReq.prepayId = prepayId;
                        payReq.partnerId = partnerId;
                        payReq.packageValue = packageValue;
                        payReq.timeStamp = timestamp;
                        payReq.nonceStr = nonceStr;
                        payReq.sign = paySign;
                        iwxapi.sendReq(payReq);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
