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
 * 首页-json数据转换
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
                int bannerSize = jsonArray.size();
                for (int j = 0; j < bannerSize; j++) {
                    bannerImages.add(jsonArray.getJSONObject(j).getString("imageUrl"));
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

    private static final String json1 = "{\n" +
            "    \"data\": [\n" +
            "        {\n" +
            "            \"item\": {\n" +
            "                \"banners\": [\n" +
            "                    {\n" +
            "                        \"imageUrl\": \"http://img4.imgtn.bdimg.com/it/u=353149760,1085730566&fm=26&gp=0.jpg\",\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"imageUrl\": \"http://img0.imgtn.bdimg.com/it/u=1255494658,2944247972&fm=11&gp=0.jpg\",\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"imageUrl\": \"http://img5.imgtn.bdimg.com/it/u=3163600373,1878398212&fm=26&gp=0.jpg\",\n" +
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
            "                \"imageUrl\": \"http://img.zcool.cn/community/011ccd5ba08e18a801213dea4b1952.jpg@2o.jpg\",\n" +
            "                \"text\": \"每秒秒冲\",\n" +
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
            "                \"text\": \"我是存文本-首页模块\",\n" +
            "                \"spanSize\": 4,\n" +
            "                \"goodsId\": \"3\"\n" +
            "            }\n" +
            "        },\n" +
            "        {\n" +
            "            \"item\": {\n" +
            "                \"banners\": [\n" +
            "                    \n" +
            "                ],\n" +
            "                \"imageUrl\": \"http://img.zcool.cn/community/0179925ba08e18a8012099c8ddd938.jpg@1280w_1l_2o_100sh.jpg\",\n" +
            "                \"text\": \"\",\n" +
            "                \"spanSize\": 4,\n" +
            "                \"goodsId\": \"4\"\n" +
            "            }\n" +
            "        },\n" +
            "        {\n" +
            "            \"item\": {\n" +
            "                \"banners\": [\n" +
            "                    \n" +
            "                ],\n" +
            "                \"imageUrl\": \"http://img.zcool.cn/community/01c0645ba08e18a801213dea78903c.jpg@2o.jpg\",\n" +
            "                \"text\": \"添加银行卡\",\n" +
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
            "                \"text\": \"我是存文本-以下内容更精彩\",\n" +
            "                \"spanSize\": 4,\n" +
            "                \"goodsId\": \"3\"\n" +
            "            }\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    private static final String json2 = "{\n" +
            "    \"data\": [\n" +
            "        {\n" +
            "            \"item\": {\n" +
            "                \"banners\": [\n" +
            "                    \n" +
            "                ],\n" +
            "                \"imageUrl\": \"http://img.zcool.cn/community/0191255ab10aa5a80120be1441683b.png@2o.png\",\n" +
            "                \"text\": \"幻想全明星\",\n" +
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
            "                \"text\": \"我是存文本-学院崩坏\",\n" +
            "                \"spanSize\": 4,\n" +
            "                \"goodsId\": \"3\"\n" +
            "            }\n" +
            "        },\n" +
            "        {\n" +
            "            \"item\": {\n" +
            "                \"banners\": [\n" +
            "                    \n" +
            "                ],\n" +
            "                \"imageUrl\": \"http://tv05.tgbusdata.cn/v3/thumb/jpg/YTI2NSw3MDAsMTAwLDQsMywxLC0xLDEs/u/shouji.tgbus.com/uploads/1302/21/14-130221100Z0N3.jpg\",\n" +
            "                \"text\": \"\",\n" +
            "                \"spanSize\": 4,\n" +
            "                \"goodsId\": \"4\"\n" +
            "            }\n" +
            "        },\n" +
            "        {\n" +
            "            \"item\": {\n" +
            "                \"banners\": [\n" +
            "                    \n" +
            "                ],\n" +
            "                \"imageUrl\": \"http://cdn2.ettoday.net/images/1039/d1039421.jpg\",\n" +
            "                \"text\": \"fate/night\",\n" +
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
            "                \"text\": \"我是存文本-马里奥\",\n" +
            "                \"spanSize\": 4,\n" +
            "                \"goodsId\": \"3\"\n" +
            "            }\n" +
            "        },\n" +
            "        {\n" +
            "            \"item\": {\n" +
            "                \"banners\": [\n" +
            "                    \n" +
            "                ],\n" +
            "                \"imageUrl\": \"http://qny.smzdm.com/201804/23/5add99b0783ef6313.jpg_e600.jpg\",\n" +
            "                \"text\": \"\",\n" +
            "                \"spanSize\": 4,\n" +
            "                \"goodsId\": \"4\"\n" +
            "            }\n" +
            "        }\n" +
            "    ]\n" +
            "}";
}
