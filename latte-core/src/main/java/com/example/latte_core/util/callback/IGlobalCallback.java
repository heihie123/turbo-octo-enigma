package com.example.latte_core.util.callback;

import io.reactivex.annotations.Nullable;

/**
 * 回调接口
 */
public interface IGlobalCallback<T> {
    void executeCallback(@Nullable T args);
}
