package com.example.latte_ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.latte_core.detegates.LatteDelegate;
import com.example.latte_core.util.timer.BaseTimerTask;
import com.example.latte_core.util.timer.ITimerListener;
import com.example.latte_ec.R;

import java.text.MessageFormat;
import java.util.Timer;

public class LauncherDelegate extends LatteDelegate implements ITimerListener, View.OnClickListener {

    private AppCompatTextView mLancherTimerTxt = null;
    private Timer mTimer = null;
    private int mCount = 5;

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
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
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

    }
}
