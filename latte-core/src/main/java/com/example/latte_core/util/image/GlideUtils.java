package com.example.latte_core.util.image;

import android.content.Context;
import android.view.View;
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

    public static void loagNormalImg(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(BANNER_OPTIONS)
                .into(imageView);
    }
}
