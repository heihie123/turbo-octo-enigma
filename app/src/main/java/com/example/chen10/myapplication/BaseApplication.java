package com.example.chen10.myapplication;

import android.app.Application;

import com.example.chen10.myapplication.event.ShareEvent;
import com.example.latte_core.app.Latte;
import com.example.latte_core.net.Interceptor.AddCookieInterceptor;
import com.example.latte_core.net.Interceptor.DebugInterceptor;
import com.example.latte_core.util.callback.CallbackManager;
import com.example.latte_core.util.callback.CallbackType;
import com.example.latte_core.util.callback.IGlobalCallback;
import com.example.latte_ec.database.DatabaseManager;
import com.example.latte_ec.icon.FontEcModule;
import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import cn.jpush.android.api.JPushInterface;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http://127.0.1/")
                .withInterceptor(new DebugInterceptor("14717", R.raw.text))
                .withInterceptor(new AddCookieInterceptor())
                .withWeChatAppId("wxfb12312312312312")
                .withWeChatAppSecret("1231239b9b4657123123f52957123123")
                .withJavascriptInterface("latte")
                .withWebEvent("share", new ShareEvent())
                .withWebHost("https://www.baidu.com/")
                .configure();
        initStetho();   // 初始化安卓web调试工具
        initDatabase(); // 初始化greendao
        initJPush();    // 初始化极光推送
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

    private void initJPush() {
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        CallbackManager.getInstance()       // 设置推送开关回调
                .addCallbacks(CallbackType.TAG_OPEN_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(Object args) {
                        if (JPushInterface.isPushStopped(Latte.getApplication())) {
                            JPushInterface.setDebugMode(true);
                            JPushInterface.init(Latte.getApplication());
                        }
                    }
                })
                .addCallbacks(CallbackType.TAG_STOP_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(Object args) {
                        if (!JPushInterface.isPushStopped(Latte.getApplication())) {
                            JPushInterface.stopPush(Latte.getApplication());
                        }
                    }
                });
    }
}
