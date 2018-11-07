package com.example.latte_core.ui.banner;

import android.content.Context;
import androidx.appcompat.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class BannerImageHolder extends Holder<String> {

    private Context mContext;
    private AppCompatImageView mAppCompatImageView;
    private static final RequestOptions BANNER_OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()
            .centerCrop();

    public BannerImageHolder(Context context, View itemView) {
        super(itemView);
        this.mContext = context;
    }

    @Override
    protected void initView(View itemView) {
        mAppCompatImageView = (AppCompatImageView) itemView;
    }

    @Override
    public void updateUI(String data) {
        Glide.with(mContext)
                .load(data)
                .apply(BANNER_OPTIONS)
                .into(mAppCompatImageView);
    }
}
