package com.example.latte_ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * 自定义图标的枚举类
 */
public enum EcIcons implements Icon {

    icon_scan('\ue606'),
    icon_ali_pay('\ue606');

    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
