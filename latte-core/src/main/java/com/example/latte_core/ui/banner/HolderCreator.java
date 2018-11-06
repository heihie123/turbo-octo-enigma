package com.example.latte_core.ui.banner;

import android.content.Context;
import android.view.View;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.example.latte_core.R;

public class HolderCreator implements CBViewHolderCreator {

    private Context mContext;

    public HolderCreator(Context context) {
        mContext = context;
    }

    @Override
    public Holder createHolder(View itemView) {
        return new BannerImageHolder(mContext, itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.image_laucher_scroll;
    }
}
