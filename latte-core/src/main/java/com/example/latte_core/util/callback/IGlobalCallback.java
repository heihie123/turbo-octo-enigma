package com.example.latte_core.util.callback;

import io.reactivex.annotations.Nullable;

public interface IGlobalCallback<T> {

    void executeCallback(@Nullable T args);
}
