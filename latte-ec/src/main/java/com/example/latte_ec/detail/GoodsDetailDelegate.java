package com.example.latte_ec.detail;

import android.os.Bundle;
import android.view.View;

import com.example.latte_core.detegates.LatteDelegate;
import com.example.latte_ec.R;

import androidx.annotation.Nullable;

/**
 * 商品详情Delegate
 */
public class GoodsDetailDelegate extends LatteDelegate {

    public static GoodsDetailDelegate create(int goodsId) {
        return new GoodsDetailDelegate();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {

    }
}
