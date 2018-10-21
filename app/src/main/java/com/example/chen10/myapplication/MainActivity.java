package com.example.chen10.myapplication;

import com.example.latte_core.activitys.ProxyActivity;
import com.example.latte_core.detegates.LatteDelegate;
import com.example.latte_ec.launcher.LauncherDelegate;

import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class MainActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return null;
    }

    @Override
    public void post(Runnable runnable) {

    }
}
