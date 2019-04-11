package com.example.latte_core.ui.Laucher;

import android.view.View;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.example.latte_core.R;

/**
 * 欢迎页Holder的构造器
 */
public class LaucherHoldCreator implements CBViewHolderCreator {

    @Override
    public Holder createHolder(View itemView) {
        return new LaucherHold(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.image_laucher_scroll;
    }
}
