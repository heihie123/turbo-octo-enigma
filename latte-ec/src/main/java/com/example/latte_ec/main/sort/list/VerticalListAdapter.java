package com.example.latte_ec.main.sort.list;

import com.example.latte_core.ui.recycler.ItemType;
import com.example.latte_core.ui.recycler.MultipleItemEntity;
import com.example.latte_core.ui.recycler.MultipleRecyclerAdapter;

import java.util.List;

public class VerticalListAdapter extends MultipleRecyclerAdapter {


    private VerticalListAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(ItemType.VERTICAL_MENU_LIST, com.example.latte_core.R.layout.item_multiple_banner);
    }
}
