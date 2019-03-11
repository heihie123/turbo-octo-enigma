package com.example.latte_ec.main.personal.address;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.latte_core.ui.recycler.BaseDataConverter;
import com.example.latte_core.ui.recycler.MultipleFields;
import com.example.latte_core.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

public class AddressDataConverter extends BaseDataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {
//        final JSONArray array = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = 5;
        for (int i = 0; i < size; i++) {
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(AddressItemType.ITEM_ADDRESS)
                    .setField(MultipleFields.ID, i)
                    .setField(MultipleFields.NAME, "小爱" + i)
                    .setField(MultipleFields.TAG, false)
                    .setField(AddressItemFields.ADDRESS, "北京市海淀区xxx")
                    .setField(AddressItemFields.PHONE, "13200000000")
                    .build();
            ENTITYS.add(entity);
        }
        return ENTITYS;
    }
}
