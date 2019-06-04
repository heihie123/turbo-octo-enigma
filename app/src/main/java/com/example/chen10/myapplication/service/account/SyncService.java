package com.example.chen10.myapplication.service.account;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * 账户同步服务
 */
public class SyncService extends Service {

    private SyncAdapter mSyncAdapter;

    @Override
    public void onCreate() {
        super.onCreate();
        mSyncAdapter = new SyncAdapter(this, true);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("keep", "账户同步开始");
        return mSyncAdapter.getSyncAdapterBinder();
    }
}
