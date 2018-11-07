package com.example.latte_core.ui.recycler;

import android.support.annotation.ColorInt;

import com.choices.divider.DividerItemDecoration;

/**
 * 对外提供的recycleview下划线操作类
 */
public class BaseDescription extends DividerItemDecoration {

    private BaseDescription(@ColorInt int color, int size) {
        setDividerLookup(new DividerLookupImpl(color, size));
    }

    public static BaseDescription create(@ColorInt int color, int size){
        return new BaseDescription(color, size);
    }
}
