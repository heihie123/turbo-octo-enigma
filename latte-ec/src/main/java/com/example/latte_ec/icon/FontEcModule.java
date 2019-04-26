package com.example.latte_ec.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * 自定义图标
 */
public class FontEcModule implements IconFontDescriptor {

    @Override
    public String ttfFileName() {
        return "iconfont.ttf";  // asset下的icon文件名字
    }

    @Override
    public Icon[] characters() {
        return EcIcons.values(); // 将枚举类转变为一个枚举类型的数组
    }
}
