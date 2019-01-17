package com.example.latte_core.ui.banner;

import android.content.Context;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;
import com.example.latte_core.util.image.GlideUtils;

import androidx.appcompat.widget.AppCompatImageView;

public class BannerImageHolder extends Holder<String> {

    private Context mContext;
    private AppCompatImageView mAppCompatImageView;

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
        GlideUtils.loadNormalImg(mContext, data, mAppCompatImageView);
    }
}
