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
public class CartDelegate extends BottomItemDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {
        ((AppCompatTextView) $(R.id.txt_toolbar_title)).setText(R.string.index_cart);
    }
}
