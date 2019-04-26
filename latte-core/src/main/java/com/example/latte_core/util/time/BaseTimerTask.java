package com.example.latte_core.util.time;

import java.util.TimerTask;

public class BaseTimerTask extends TimerTask {

    private ITimerListener mITimerListener;

    public BaseTimerTask(ITimerListener iTimerListener) {
        this.mITimerListener = iTimerListener;
    }

    @Override
    public void run() {
        if (mITimerListener != null) {
            mITimerListener.onTimer();
        }
    }
}
