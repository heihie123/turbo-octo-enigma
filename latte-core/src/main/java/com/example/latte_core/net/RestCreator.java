package com.example.latte_core.net;


import com.example.latte_core.app.ConfigKeys;
import com.example.latte_core.app.Latte;
import com.example.latte_core.net.rx.RxRestService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 获取Retrofit对象的方法
 */
public class RestCreator {

    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolser.PARAMS;
    }

    private static final class ParamsHolser {
        private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    // 获取RestService
    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }

    // 获取RxRestService
    public static RxRestService getRxRestService() {
        return RxRestServiceHolder.RX_REST_SERVICE;
    }

    private static final class RxRestServiceHolder {
        private static final RxRestService RX_REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT.create(RxRestService.class);
    }

    // 获取retrofit
    private static final class RetrofitHolder {

        private static final String BASE_URL = Latte.getConfiguration(ConfigKeys.API_HOST);

        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    // 获取okhttp
    private static final class OKHttpHolder {

        private static final int TIME_OUT = 60;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        private static final ArrayList<Interceptor> INTERCEPTORS = Latte.getConfiguration(ConfigKeys.INTERCEPTOR);

        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();

        // 添加拦截器
        private static OkHttpClient.Builder addInterceptor() {
            if (INTERCEPTORS != null && !INTERCEPTORS.isEmpty()) {
                for (Interceptor interceptor : INTERCEPTORS) {
                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }
    }
}
