package com.example.chen10.myapplication.service.account;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * 账户服务
 */
public class AuthenticationService extends Service {

    private AccountAuthenticator mAccountAuthenticator;

    @Override
    public void onCreate() {
        super.onCreate();
        mAccountAuthenticator = new AccountAuthenticator(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("keep", "账户");
        return mAccountAuthenticator.getIBinder();
    }
}
