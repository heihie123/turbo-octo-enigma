package com.example.latte_core.ui.recycler;

import com.google.auto.value.AutoValue;

/**
 * rgb颜色实体类
 */
@AutoValue
public abstract class RGBValue {

    public abstract int red();
    public abstract int green();
    public abstract int blue();

    public static RGBValue create(int red, int green, int blue) {
        return new AutoValue_RGBValue(red, green, blue);
    }
}
