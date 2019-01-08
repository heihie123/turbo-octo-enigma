package com.example.latte_ec.main.personal.settings;

import android.os.Bundle;
import android.view.View;

import com.example.latte_core.detegates.LatteDelegate;
import com.example.latte_ec.R;

import androidx.annotation.Nullable;

public class NameDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_name;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {

    }
}
