package com.example.latte_core.app;

import android.app.Activity;
import android.os.Handler;

import com.blankj.utilcode.util.Utils;
import com.example.latte_core.detegates.web.event.Event;
import com.example.latte_core.detegates.web.event.EventManager;
import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.NonNull;
import okhttp3.Interceptor;

/**
 * 配置
 */
public class Configurator {

    // WeakHashMap:key不使用的时候回收的非常快，节省内存
    private static final HashMap<Object, Object> LATTE_CONFIGS = new HashMap<>();
    private static final Handler HANDLER = new Handler();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();   // icon
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();   // 拦截器

    private Configurator() {
        // .name枚举以字符串方式输出，未初始化完成
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY, false);
        LATTE_CONFIGS.put(ConfigKeys.HANDLER, HANDLER);
    }

    // 线程安全的单例模式（静态内部类）
    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    //------------------------------ 初始化分割线 ---------------------------------------

    public final void configure() {
        // 通用的东西在初始化的时候就应该初始化好
        initIcons();
        Logger.addLogAdapter(new AndroidLogAdapter());
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY, true);
        Utils.init(Latte.getApplication());
    }

    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    final HashMap<Object, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigKeys.API_HOST, host);
        return this;
    }

    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public final Configurator withWeChatAppId(String appId) {
        LATTE_CONFIGS.put(ConfigKeys.WE_CHAT_APP_ID, appId);
        return this;
    }

    public final Configurator withWeChatAppSecret(String APPsecret) {
        LATTE_CONFIGS.put(ConfigKeys.WE_CHAT_APP_SECRET, APPsecret);
        return this;
    }

    public final Configurator withActivity(Activity activity) {
        LATTE_CONFIGS.put(ConfigKeys.ACTIVITY, activity);
        return this;
    }

    public Configurator withJavascriptInterface(@NonNull String name) {
        LATTE_CONFIGS.put(ConfigKeys.JAVASCRIPT_INTERFACE, name);
        return this;
    }

    public Configurator withWebHost(String host) {
        LATTE_CONFIGS.put(ConfigKeys.WEB_HOST, host);
        return this;
    }

    public Configurator withWebEvent(@NonNull String name, @NonNull Event event) {
        final EventManager manager = EventManager.getInstance();
        manager.addEvent(name, event);
        return this;
    }

    private void checkConfiguration() {
        // 类变量/方法变量中，如果变量不再变化，尽量使用final修饰，防止意外被修改（jvm对final优化，或多或少性能提升）
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigKeys.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready, call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = LATTE_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) LATTE_CONFIGS.get(key);
    }
}
