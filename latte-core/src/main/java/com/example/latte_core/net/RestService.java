package com.example.latte_core.net;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 连接http方法接口，对外提供
 */
public interface RestService {

    @GET
    Call<String> get(@Url String url, @QueryMap Map<String, Object> params);

    @FormUrlEncoded
    @POST
    Call<String> post(@Url String url, @FieldMap Map<String, Object> params);

    @FormUrlEncoded
    @PUT
    Call<String> put(@Url String url, @FieldMap Map<String, Object> params);

    @DELETE
    Call<String> delete(@Url String url, @QueryMap Map<String, Object> params);

    // @Streaming 边下载边写入，防止文件过大内存溢出
    @Streaming
    @GET
    Call<ResponseBody> download(@Url String url, @QueryMap Map<String, Object> params);

    // @Multipart 将文件以二进制的形式上传
    @Multipart
    @POST
    Call<String> upload(@Url String url, @Part MultipartBody.Part file);
}
