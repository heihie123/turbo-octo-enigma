package com.example.chen10.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.example.latte_core.activitys.ProxyActivity;
import com.example.latte_core.app.Latte;
import com.example.latte_core.detegates.LatteDelegate;
import com.example.latte_core.ui.Laucher.ILaucherListener;
import com.example.latte_core.ui.Laucher.OnLauncherFinishTag;
import com.example.latte_ec.launcher.LauncherDelegate;
import com.example.latte_ec.main.EcBottomDelegate;
import com.example.latte_ec.sign.ISignListener;
import com.example.latte_ec.sign.SignInDelegate;

public class MainActivity extends ProxyActivity implements ISignListener, ILaucherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Latte.getConfigurator().withActivity(this);
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void post(Runnable runnable) {

    }

    @Override
    public void onSignIn(boolean isSuccess) {
        if (isSuccess) {
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "登录失败！请重新登录", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSignUp(boolean isSuccess) {
        if (isSuccess) {
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "该账号已存在", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLaucherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                // start的同时把栈中上一个fragment清除
                getSupportDelegate().startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
                getSupportDelegate().startWithPop(new SignInDelegate());
                break;
        }
    }
}
