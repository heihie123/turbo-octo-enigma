package com.example.chen10.myapplication;

import android.app.Application;

import com.example.latte_core.app.Latte;
import com.example.latte_core.net.Interceptor.DebugInterceptor;
import com.example.latte_ec.database.DatabaseManager;
import com.example.latte_ec.icon.FontEcModule;
import com.facebook.stetho.Stetho;
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
                .withWeChatAppId("wxfb12312312312312")
                .withWeChatAppSecret("1231239b9b4657123123f52957123123")
                .configure();
        initStetho();
        initDatabase();
    }

    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build()
        );
    }

    private void initDatabase() {
        DatabaseManager.getInstance().init(this);
    }
}
