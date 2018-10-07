package com.example.chen10.myapplication;

import com.example.latte_core.activitys.ProxyActivity;
import com.example.latte_core.detegates.LatteDelegate;

import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class MainActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new MainFragment();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return null;
    }

    @Override
    public void post(Runnable runnable) {

    }
}
