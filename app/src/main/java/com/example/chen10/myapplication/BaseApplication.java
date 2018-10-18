package com.example.chen10.myapplication;

import android.app.Application;

import com.example.latte_core.app.Latte;
import com.example.latte_core.net.Interceptor.DebugInterceptor;
import com.example.latte_ec.icon.FontEcModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http://127.0.1/")
                .withInterceptor(new DebugInterceptor("14717", R.raw.text))
                .configure();
    }
}
