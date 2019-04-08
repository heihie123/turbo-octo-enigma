package com.example.latte_core.net.Interceptor;

import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;

/**
 * 拦截器基类
 */
public abstract class BaseInterceptor implements Interceptor {

    /**
     * 根据关键字返回某个请求参数
     */
    protected String getUrlParameter(Chain chain, String key) {
        final Request request = chain.request();
        return request.url().queryParameter(key);
    }

    /**
     * 返回所有请求参数
     */
    protected LinkedHashMap<String, String> getUrlParameters(Chain chain) {
        final HttpUrl url = chain.request().url();
        int size = url.querySize();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        for (int i = 0; i < size; i++) {
            params.put(url.queryParameterName(i), url.queryParameterValue(i));
        }
        return params;
    }

    /**
     * 返回请求体中某个参数
     */
    protected String getBodyPramameter(Chain chain, String key) {
        return getBodyPramameters(chain).get(key);
    }

    /**
     * 返回请求体中所有参数
     */
    protected LinkedHashMap<String, String> getBodyPramameters(Chain chain) {
        final FormBody formBody = (FormBody) chain.request().body();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        int size = 0;
        if (formBody != null) {
            size = formBody.size();
        }
        for (int i = 0; i < size; i++) {
            params.put(formBody.name(i), formBody.value(i));
        }
        return params;
    }
}
