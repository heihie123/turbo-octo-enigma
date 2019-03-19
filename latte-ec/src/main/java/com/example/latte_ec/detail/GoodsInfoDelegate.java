package com.example.latte_ec.detail;

import android.os.Bundle;
import android.view.View;

import com.example.latte_core.detegates.LatteDelegate;
import com.example.latte_ec.R;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * 商品详情Delegate
 */
public class GoodsInfoDelegate extends LatteDelegate {

    private static final String ARG_GOODS_DATA = "ARG_GOODS_DATA";
    private String goodsDataStr;

    public static GoodsInfoDelegate create(String goodsData) {
        final Bundle args = new Bundle();
        args.putString(ARG_GOODS_DATA, goodsData);
        final GoodsInfoDelegate goodsInfoDelegate = new GoodsInfoDelegate();
        goodsInfoDelegate.setArguments(args);
        return goodsInfoDelegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            goodsDataStr = args.getString(ARG_GOODS_DATA);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_info;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {
        ((AppCompatTextView) $(R.id.txt_goods_info_title)).setText("我是标题");
        ((AppCompatTextView) $(R.id.txt_goods_info_desc)).setText("我是秒速");
        ((AppCompatTextView) $(R.id.txt_goods_info_price)).setText(String.valueOf("￥1280"));
    }
}
