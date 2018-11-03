package com.example.latte_ec.main.index;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.latte_core.ui.recycler.BaseDataConverter;
import com.example.latte_core.ui.recycler.ItemType;
import com.example.latte_core.ui.recycler.MultipleFields;
import com.example.latte_core.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * item json数据转换
 */
public class IndexDataConverter extends BaseDataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject jsonObject = dataArray.getJSONObject(i);
            final String imageUrl = jsonObject.getString("imageUrl");
            final String text = jsonObject.getString("text");
            final int spanSize = jsonObject.getInteger("spanSize");
            final int id = jsonObject.getInteger("goodsId");
            List<String> bannerImages = jsonObject.getJSONArray("banners").toJavaList(String.class);
            int type = 0;

            if (imageUrl != null) {
                if (text != null) {
                    type = ItemType.TEXT_IMAGE;
                } else {
                    type = ItemType.IMAGE;
                }
            } else if (text != null) {
                type = ItemType.TEXT;
            } else if (bannerImages != null) {
                type = ItemType.BANNER;
            }

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(type)
                    .setField(MultipleFields.ITEM_TYPE, type)
                    .setField(MultipleFields.SPAN_SIZE, spanSize)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.TEXT, text)
                    .setField(MultipleFields.IMAGE_URL, imageUrl)
                    .setField(MultipleFields.BANNERS, bannerImages)
                    .build();
            ENTITYS.add(entity);
        }
        return ENTITYS;
    }
}
