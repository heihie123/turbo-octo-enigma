package com.example.chen10.myapplication.keep;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.lang.ref.WeakReference;

/**
 * 1像素activity 保活管理类
 */
public class KeepManager {

    private static KeepManager instance;
    private WeakReference<Activity> mActivityWeakReference;
    private KeepReceiver mKeepReceiver;

    private KeepManager() {

    }

    public static KeepManager getInstance() {
        if (instance == null) {
            instance = new Holder().INSTANCE;
        }
        return instance;
    }

    private static class Holder {
        private KeepManager INSTANCE = new KeepManager();
    }

    public void registerKeepReceiver(Context context) {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        mKeepReceiver = new KeepReceiver();
        context.registerReceiver(mKeepReceiver, filter);
    }

    public void unRegisterKeepReceiver(Context context) {
        if (mKeepReceiver != null) {
            context.unregisterReceiver(mKeepReceiver);
        }
    }

    public void setKeepActivity(Activity activity) {
        mActivityWeakReference = new WeakReference<>(activity);
    }

    public void startKeep(Context context) {
        Intent intent = new Intent(context, KeepActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void finishKeep() {
        if (mActivityWeakReference != null) {
            Activity activity = mActivityWeakReference.get();
            if (activity != null) {
                activity.finish();
            }
            mActivityWeakReference = null;
        }
    }
}
