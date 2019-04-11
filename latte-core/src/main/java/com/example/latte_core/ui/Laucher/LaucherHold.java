package com.example.latte_core.ui.Laucher;

import androidx.appcompat.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * 欢迎页轮播Holder
 */
public class LaucherHold extends Holder<Integer> {

    private AppCompatImageView mAppCompatImageView;

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
