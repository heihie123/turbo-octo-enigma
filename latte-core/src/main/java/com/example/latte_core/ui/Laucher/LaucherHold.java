package com.example.latte_core.ui.Laucher;

import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;

public class LaucherHold extends Holder<Integer> {

    private AppCompatImageView mAppCompatImageView = null;

    public LaucherHold(View itemView) {
        super(itemView);
    }

    @Override
    protected void initView(View itemView) {
        mAppCompatImageView = (AppCompatImageView) itemView;
    }

    @Override
    public void updateUI(Integer data) {
        mAppCompatImageView.setBackgroundResource(data);
    }
}
