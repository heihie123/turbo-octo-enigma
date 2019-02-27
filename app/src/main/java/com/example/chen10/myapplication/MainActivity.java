package com.example.chen10.myapplication;

import android.os.Bundle;

import com.example.latte_core.activitys.ProxyActivity;
import com.example.latte_core.app.Latte;
import com.example.latte_core.detegates.LatteDelegate;
import com.example.latte_core.ui.Laucher.ILaucherListener;
import com.example.latte_core.ui.Laucher.OnLauncherFinishTag;
import com.example.latte_core.util.ToastUtils;
import com.example.latte_ec.launcher.LauncherDelegate;
import com.example.latte_ec.main.EcBottomDelegate;
import com.example.latte_ec.main.index.IndexDelagate;
import com.example.latte_ec.main.personal.address.AddressDelegate;
import com.example.latte_ec.main.personal.profile.UserProfileDelegate;
import com.example.latte_ec.sign.ISignListener;
import com.example.latte_ec.sign.SignInDelegate;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import cn.jpush.android.api.JPushInterface;
import qiu.niorgai.StatusBarCompat;

public class MainActivity extends ProxyActivity implements ISignListener, ILaucherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Latte.getConfigurator().withActivity(this);
        // 状态栏透明
        StatusBarCompat.translucentStatusBar(this, true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new AddressDelegate();
    }

    @Override
    public void post(Runnable runnable) {

    }

    @Override
    public void onSignIn(boolean isSuccess) {
        if (isSuccess) {
            ToastUtils.showShotToast("登录成功");
            getSupportDelegate().startWithPop(new EcBottomDelegate());
        } else {
            ToastUtils.showShotToast("登录失败！请重新登录");
        }
    }

    @Override
    public void onSignUp(boolean isSuccess) {
        if (isSuccess) {
            ToastUtils.showShotToast("注册成功");
            getSupportDelegate().startWithPop(new SignInDelegate());
        } else {
            ToastUtils.showShotToast("该账号已存在");
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
