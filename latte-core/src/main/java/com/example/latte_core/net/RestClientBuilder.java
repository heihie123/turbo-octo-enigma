package com.example.latte_core.net;

import android.content.Context;

import com.example.latte_core.net.callback.IError;
import com.example.latte_core.net.callback.IFailure;
import com.example.latte_core.net.callback.IRequest;
import com.example.latte_core.net.callback.ISuccess;
import com.example.latte_core.ui.loader.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RestClientBuilder {
    private String mUrl = null;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private IRequest mRequest = null;
    private ISuccess mSucess = null;
    private IFailure mFailure = null;
    private IError mError = null;
    private RequestBody mBody = null;
    private LoaderStyle mLoaderStyle = null;
    private Context mContext = null;
    private File mFile = null;
    private String mDownload_dir = null;
    private String mExtension = null;
    private String mName = null;

    RestClientBuilder() {

    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder file(String filePatch) {
        this.mFile = new File(filePatch);
        return this;
    }

    public final RestClientBuilder dir(String dir) {
        this.mDownload_dir = dir;
        return this;
    }

    public final RestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }

    public final RestClientBuilder name(String name) {
        this.mName = name;
        return this;
    }

    public final RestClientBuilder request(IRequest request) {
        this.mRequest = request;
        return this;
    }

    public final RestClientBuilder sucess(ISuccess sucess) {
        this.mSucess = sucess;
        return this;
    }

    public final RestClientBuilder failure(IFailure failure) {
        this.mFailure = failure;
        return this;
    }

    public final RestClientBuilder error(IError error) {
        this.mError = error;
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder loader(Context context, LoaderStyle loaderStyle) {
        this.mContext = context;
        this.mLoaderStyle = loaderStyle;
        return this;
    }

    public final RestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RestClient build() {
        return new RestClient(mUrl, PARAMS, mRequest, mSucess, mFailure, mError, mBody, mLoaderStyle, mContext, mFile, mDownload_dir, mExtension, mName);
    }
}
