package com.example.latte_ec.main;

import android.graphics.Color;

import com.example.latte_core.detegates.bottom.BaseBottomDelegate;
import com.example.latte_core.detegates.bottom.BottomItemDelegate;
import com.example.latte_core.detegates.bottom.BottomTabBean;
import com.example.latte_core.detegates.bottom.ItemBuilder;
import com.example.latte_ec.main.index.IndexDelagate;

import java.util.LinkedHashMap;

/**
 * 首页delegate
 */
public class EcBottomDelegate extends BaseBottomDelegate {

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }

    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelagate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new IndexDelagate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new IndexDelagate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new IndexDelagate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new IndexDelagate());
        return builder.addItems(items).build();
    }
}
