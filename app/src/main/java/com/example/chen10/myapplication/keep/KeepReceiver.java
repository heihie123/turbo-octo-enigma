package com.example.chen10.myapplication.keep;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

/**
 * 开屏灭屏广播
 */
public class KeepReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.e("keep", "receive:" + action);
        if (TextUtils.equals(action, Intent.ACTION_SCREEN_OFF)) {
            KeepManager.getInstance().startKeep(context);
        } else if (TextUtils.equals(action, Intent.ACTION_SCREEN_ON)) {
            KeepManager.getInstance().finishKeep();
        }
    }
}
