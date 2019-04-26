package com.example.latte_ec.launcher;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.latte_core.app.AccountManager;
import com.example.latte_core.app.IUserChecker;
import com.example.latte_core.detegates.LatteDelegate;
import com.example.latte_core.ui.Laucher.ILaucherListener;
import com.example.latte_core.ui.Laucher.OnLauncherFinishTag;
import com.example.latte_core.ui.Laucher.ScrollLaucherTag;
import com.example.latte_core.util.storage.LattePreference;
import com.example.latte_core.util.time.BaseTimerTask;
import com.example.latte_core.util.time.ITimerListener;
import com.example.latte_ec.R;

import java.text.MessageFormat;
import java.util.Timer;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * 启动页Fragment
 */
public class LauncherDelegate extends LatteDelegate implements ITimerListener, View.OnClickListener {

    private AppCompatTextView mLancherTimerTxt = null;
    private Timer mTimer = null;
    private int mCount = 5;
    private ILaucherListener mILaucherListener = null;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ILaucherListener) {
            mILaucherListener = (ILaucherListener) context;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_lancher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {
        initTimer();
        mLancherTimerTxt = $(R.id.txt_lancher_timer);
        mLancherTimerTxt.setOnClickListener(this);
    }

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask timerTask = new BaseTimerTask(this);
        mTimer.schedule(timerTask, 0, 1000);
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTimer != null) {
                    mLancherTimerTxt.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        onClickTimerView();
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.txt_lancher_timer) {
            onClickTimerView();
        }
    }

    private void onClickTimerView() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    private void checkIsShowScroll() {
        if (!LattePreference.getAppFlag(ScrollLaucherTag.HAS_FIRST_LAUCHER_APP.name())) {
            getSupportDelegate().startWithPop(new LauncherScrollDelegate());
        } else {
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mILaucherListener != null) {
                        mILaucherListener.onLaucherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mILaucherListener != null) {
                        mILaucherListener.onLaucherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }
    }
}
