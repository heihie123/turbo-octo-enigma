package com.example.latte_core.util.callback;

import java.util.WeakHashMap;

/**
 * 回调的管理类
 */
public class CallbackManager {

    private static final WeakHashMap<Object, IGlobalCallback> CALLBACKS = new WeakHashMap<>();

    public static CallbackManager getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final CallbackManager INSTANCE = new CallbackManager();
    }

    public IGlobalCallback getCallbacks(Object tag) {
        return CALLBACKS.get(tag);
    }

    public CallbackManager addCallbacks(Object tag, IGlobalCallback callback) {
        CALLBACKS.put(tag, callback);
        return this;
    }
}
