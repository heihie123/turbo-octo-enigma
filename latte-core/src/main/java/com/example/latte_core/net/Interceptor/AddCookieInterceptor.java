package com.example.latte_core.net.Interceptor;

import android.annotation.SuppressLint;

import com.example.latte_core.util.storage.LattePreference;

import java.io.IOException;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.Request;
import okhttp3.Response;

/**
 * cookie拦截器
 */
public final class AddCookieInterceptor extends BaseInterceptor {

    @SuppressLint("CheckResult")
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
        Observable
                .just(LattePreference.getCustomAppProfile("cookie"))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull String cookie) throws Exception {
                        // 给原生api请求附带上webview拦截的cookiew
                        builder.addHeader("cookie", cookie);
                    }
                });
        return chain.proceed(builder.build());
    }
}