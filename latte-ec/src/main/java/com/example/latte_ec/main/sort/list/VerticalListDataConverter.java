package com.example.latte_ec.main.sort.list;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.latte_core.ui.recycler.BaseDataConverter;
import com.example.latte_core.ui.recycler.ItemType;
import com.example.latte_core.ui.recycler.MultipleFields;
import com.example.latte_core.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * 分类列表-json数据转换
 */
public class VerticalListDataConverter extends BaseDataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final ArrayList<MultipleItemEntity> entities = new ArrayList<>();
        JSONArray jsonArray = JSON.parseObject(json).getJSONArray("data");
        int size = jsonArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject jsonObject = jsonArray.getJSONObject(i);
            final int id = jsonObject.getInteger("id");
            final String name = jsonObject.getString("name");
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, ItemType.VERTICAL_MENU_LIST)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.TEXT, name)
                    .setField(MultipleFields.TAG, false)
                    .build();
            entities.add(entity);
        }
        entities.get(0).setField(MultipleFields.TAG, true);
        return entities;
    }

    private static final String json = "{\n" +
            "    \"data\": [\n" +
            "        {\n" +
            "            \"id\": 1,\n" +
            "            \"name\": \"国际名牌\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 2,\n" +
            "            \"name\": \"奢侈品\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 3,\n" +
            "            \"name\": \"全球购\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 4,\n" +
            "            \"name\": \"唯品会\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 5,\n" +
            "            \"name\": \"男装\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 6,\n" +
            "            \"name\": \"女装\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 7,\n" +
            "            \"name\": \"男鞋\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 8,\n" +
            "            \"name\": \"女鞋\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 9,\n" +
            "            \"name\": \"内衣配饰\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 10,\n" +
            "            \"name\": \"箱包手袋\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";
}
