package com.example.latte_ec.main.index.search;

import com.alibaba.fastjson.JSONArray;
import com.example.latte_core.ui.recycler.BaseDataConverter;
import com.example.latte_core.ui.recycler.MultipleFields;
import com.example.latte_core.ui.recycler.MultipleItemEntity;
import com.example.latte_core.util.storage.LattePreference;

import java.util.ArrayList;

public class SearchDataConverter extends BaseDataConverter {

    public static final String TAG_SEARCH_HISTORY = "search_history";

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final String jsonStr = LattePreference.getCustomAppProfile(TAG_SEARCH_HISTORY);
        if (!jsonStr.equals("")) {
            final JSONArray jsonArray = JSONArray.parseArray(jsonStr);
            final int size = jsonArray.size();
            for (int i = 0; i < size; i++) {
                final String historyItemText = jsonArray.getString(i);
                final MultipleItemEntity entity = MultipleItemEntity.builder()
                        .setItemType(SearchItemType.ITEM_SEARCH)
                        .setField(MultipleFields.TEXT, historyItemText)
                        .build();
                ENTITYS.add(entity);
            }
        }
        return ENTITYS;
    }
}
