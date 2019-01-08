package com.example.latte_ec.main.personal.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.latte_core.ui.recycler.BaseDataConverter;
import com.example.latte_core.ui.recycler.MultipleFields;
import com.example.latte_core.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

public class OrderListDataConverter extends BaseDataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray array = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = 5;
        for (int i = 0; i < size; i++) {
            final JSONObject data = array.getJSONObject(i);
            final String thumb = data.getString("thumb");
            final String title = data.getString("title");
            final int id = data.getInteger("id");
            final double price = data.getDouble("price");
            final String time = data.getString("time");
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(OrderListItemType.ITEM_ORDER_LIST)
                    .setField(MultipleFields.ID, i)
                    .setField(MultipleFields.IMAGE_URL, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1546952324924&di=a79d3a43832aa9a0dad5d8b62a9b8528&imgtype=0&src=http%3A%2F%2Fimg1.mydrivers.com%2Fimg%2F20160908%2Fc34985e26e5e45d89cd9a2a421c373fe.jpg")
                    .setField(MultipleFields.TEXT, "iphone n0" + i)
                    .setField(OrderItemFields.PRICE, 10)
                    .setField(OrderItemFields.TIME, "2019-1-1 17:00")
                    .build();
            ENTITYS.add(entity);
        }

        return ENTITYS;
    }
}
