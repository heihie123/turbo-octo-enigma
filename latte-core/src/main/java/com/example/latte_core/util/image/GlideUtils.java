package com.example.latte_core.util.image;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * 图片加载工具类
 */
public class GlideUtils {

    private static final RequestOptions BANNER_OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()
            .centerCrop();

    private static final RequestOptions NO_CACHE_OPPTIONS = new RequestOptions()
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.NONE);

    public static void loadNormalImg(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(BANNER_OPTIONS)
                .into(imageView);
    }

    public static void loadNormalImgWithOption(Context context, String url, ImageView imageView, RequestOptions options) {
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }

    public static void loadNoCacheImg(Context context, Uri uri, ImageView imageView) {
        Glide.with(context)
                .load(uri)
                .apply(NO_CACHE_OPPTIONS)
                .into(imageView);
    }

    public static void loadNoCacheImg(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(NO_CACHE_OPPTIONS)
                .into(imageView);
    }
}
