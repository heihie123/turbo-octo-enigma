package com.example.latte_ec.main.cart;

import android.os.Bundle;
import android.view.View;

import com.example.latte_core.detegates.bottom.BottomItemDelegate;
import com.example.latte_ec.R;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * 购物车delegate
 */
public class ShopCartDelegate extends BottomItemDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {
        initView();

    }

    private void initView() {
        ((AppCompatTextView) $(R.id.txt_toolbar_left)).setText(R.string.index_cart_empty);
        ((AppCompatTextView) $(R.id.txt_toolbar_title)).setText(R.string.index_cart);
        ((AppCompatTextView) $(R.id.txt_toolbar_right)).setText(R.string.index_cart_delete);
    }
}
