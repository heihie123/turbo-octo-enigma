package com.example.latte_ec.main.index;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.latte_core.ui.recycler.BaseDataConverter;
import com.example.latte_core.ui.recycler.ItemType;
import com.example.latte_core.ui.recycler.MultipleFields;
import com.example.latte_core.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * item json数据转换
 */
public class IndexDataConverter extends BaseDataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        String json;
        if ("request_first".equals(getJsonData())) {
            json = json1;
        } else {
            json = json2;
        }
        final JSONArray dataArray = JSON.parseObject(json).getJSONArray("data");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject jsonObject = dataArray.getJSONObject(i).getJSONObject("item");
            final String imageUrl = jsonObject.getString("imageUrl");
            final String text = jsonObject.getString("text");
            final Integer spanSize = jsonObject.getInteger("spanSize");
            final Integer id = jsonObject.getInteger("goodsId");
            final JSONArray jsonArray = jsonObject.getJSONArray("banners");
            final ArrayList<String> bannerImages = new ArrayList<>();
            int type = 0;

            if (!TextUtils.isEmpty(imageUrl)) {
                if (!TextUtils.isEmpty(text)) {
                    type = ItemType.TEXT_IMAGE;
                } else {
                    type = ItemType.IMAGE;
                }
            } else if (!TextUtils.isEmpty(text)) {
                type = ItemType.TEXT;
            } else if (jsonArray != null && jsonArray.size() > 0) {
                type = ItemType.BANNER;
                for(int j = 0; j < size; j++){
                    bannerImages.add(jsonArray.getJSONObject(i).getString("imageUrl"));
                }
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

    private String json1 = "{\n" +
            "    \"data\": [\n" +
            "        {\n" +
            "            \"item\": {\n" +
            "                \"banners\": [\n" +
            "                    {\n" +
            "                        \"imageUrl\": \"http://bpic.ooopic.com/15/64/42/15644277-b06a3fbd7745a392fab1ac9a21dcbe50.jpg\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"imageUrl\": \"http://bpic.ooopic.com/16/54/92/16549228-3396654f265c6b942170b216f7c2a753.jpg\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"imageUrl\": \"http://bpic.ooopic.com/16/37/91/16379119-59336ecc6904333c3d1d880ed6e461dd-4.jpg\"\n" +
            "                    }\n" +
            "                ],\n" +
            "                \"imageUrl\": \"\",\n" +
            "                \"text\": \"\",\n" +
            "                \"spanSize\": 4,\n" +
            "                \"goodsId\": \"1\"\n" +
            "            }\n" +
            "        },\n" +
            "        {\n" +
            "            \"item\": {\n" +
            "                \"banners\": [\n" +
            "                    \n" +
            "                ],\n" +
            "                \"imageUrl\": \"http://bpic.ooopic.com/16/81/22/16812205-afe75bedf7e5c3eee4fa0e2c6eaf11f7-1.jpg\",\n" +
            "                \"text\": \"特购特购\",\n" +
            "                \"spanSize\": 4,\n" +
            "                \"goodsId\": \"2\"\n" +
            "            }\n" +
            "        },\n" +
            "        {\n" +
            "            \"item\": {\n" +
            "                \"banners\": [\n" +
            "                    \n" +
            "                ],\n" +
            "                \"imageUrl\": \"\",\n" +
            "                \"text\": \"降价秒杀\",\n" +
            "                \"spanSize\": 4,\n" +
            "                \"goodsId\": \"3\"\n" +
            "            }\n" +
            "        },\n" +
            "        {\n" +
            "            \"item\": {\n" +
            "                \"banners\": [\n" +
            "                    \n" +
            "                ],\n" +
            "                \"imageUrl\": \"http://bpic.ooopic.com/16/74/43/16744375-3ad20b5875c6786ddeebf01a39dbfc78.jpg\",\n" +
            "                \"text\": \"\",\n" +
            "                \"spanSize\": 4,\n" +
            "                \"goodsId\": \"4\"\n" +
            "            }\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    private String json2 = "{\n" +
            "    \"data\": [\n" +
            "        {\n" +
            "            \"item\": {\n" +
            "                \"banners\": [\n" +
            "                    \n" +
            "                ],\n" +
            "                \"imageUrl\": \"http://bpic.wotucdn.com/15/80/92/15809238-b364f6f73498d3a562937247f8a9db60.jpg\",\n" +
            "                \"text\": \"特购特购\",\n" +
            "                \"spanSize\": 4,\n" +
            "                \"goodsId\": \"2\"\n" +
            "            }\n" +
            "        },\n" +
            "        {\n" +
            "            \"item\": {\n" +
            "                \"banners\": [\n" +
            "                    \n" +
            "                ],\n" +
            "                \"imageUrl\": \"\",\n" +
            "                \"text\": \"降价秒杀\",\n" +
            "                \"spanSize\": 4,\n" +
            "                \"goodsId\": \"3\"\n" +
            "            }\n" +
            "        },\n" +
            "        {\n" +
            "            \"item\": {\n" +
            "                \"banners\": [\n" +
            "                    \n" +
            "                ],\n" +
            "                \"imageUrl\": \"http://bpic.ooopic.com/15/86/63/15866358-068952855554356e62d92685ac4d1e4f-1.jpg\",\n" +
            "                \"text\": \"\",\n" +
            "                \"spanSize\": 4,\n" +
            "                \"goodsId\": \"4\"\n" +
            "            }\n" +
            "        }\n" +
            "    ]\n" +
            "}";
}
