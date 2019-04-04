package com.example.latte_core.detegates.bottom;

/**
 * BottomItem的bean
 */
public final class BottomTabBean {
    // 变量修饰为final并且在构造函数中传值，防止多线程并发出现问题
    private final CharSequence ICON;
    private final CharSequence TITLE;

    public BottomTabBean(CharSequence icon, CharSequence title) {
        this.ICON = icon;
        this.TITLE = title;
    }

    public CharSequence getICON() {
        return ICON;
    }

    public CharSequence getTITLE() {
        return TITLE;
    }
}
