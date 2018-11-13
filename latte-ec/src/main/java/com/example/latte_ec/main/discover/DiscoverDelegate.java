package com.example.latte_ec.main.discover;

import android.os.Bundle;
import android.view.View;

import com.example.latte_core.detegates.bottom.BottomItemDelegate;
import com.example.latte_ec.R;

import androidx.annotation.Nullable;

/**
 * 发现delegate
 */
public class DiscoverDelegate extends BottomItemDelegate {

    @Override
    public Object setLayout() {
        return R.layout.deleagte_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {

    }
}
