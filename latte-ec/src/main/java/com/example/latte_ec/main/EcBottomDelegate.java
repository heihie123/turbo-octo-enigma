package com.example.latte_ec.main;

import android.graphics.Color;

import com.example.latte_core.detegates.bottom.BaseBottomDelegate;
import com.example.latte_core.detegates.bottom.BottomItemDelegate;
import com.example.latte_core.detegates.bottom.BottomTabBean;
import com.example.latte_core.detegates.bottom.ItemBuilder;
import com.example.latte_ec.R;
import com.example.latte_ec.main.cart.ShopCartDelegate;
import com.example.latte_ec.main.discover.DiscoverDelegate;
import com.example.latte_ec.main.index.IndexDelagate;
import com.example.latte_ec.main.personal.PersonalDelegate;
import com.example.latte_ec.main.sort.SortDelegate;

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
        items.put(new BottomTabBean("{fa-home}", getResources().getString(R.string.index_main)), new IndexDelagate());
        items.put(new BottomTabBean("{fa-sort}", getResources().getString(R.string.index_sort)), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", getResources().getString(R.string.index_discover)), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", getResources().getString(R.string.index_cart)), new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-user}", getResources().getString(R.string.index_personal)), new PersonalDelegate());
        return builder.addItems(items).build();
    }
}
