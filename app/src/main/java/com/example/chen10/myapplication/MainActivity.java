package com.example.chen10.myapplication;

import com.example.latte_core.activitys.ProxyActivity;
import com.example.latte_core.detegates.LatteDelegate;

public class MainActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new MainFragment();
    }
}
