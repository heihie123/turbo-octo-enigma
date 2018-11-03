package com.example.latte_core.ui.banner;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;

public class BannerImageHolder extends Holder<String> {

    private Context mContext;
    private AppCompatImageView mAppCompatImageView = null;

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
                .into(mAppCompatImageView);
    }
}
