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
        String json;
        if ("request_first".equals(getJsonData())) {
            json = json1;
        } else {
            json = json2;
        }
        final JSONArray dataArray = JSON.parseObject(json).getJSONArray("data");
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

    private String json1 = "{\n" +
            "    \"data\": [\n" +
            "        {\n" +
            "            \"item1\": {\n" +
            "                \"banners\": [\n" +
            "                    {\n" +
            "                        \"imageUrl\": \"http://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E5%AE%BD%E5%9B%BE%E8%83%8C%E6%99%AF&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&cs=1208188544,1513879059&os=392717502,1138275663&simid=9497353,890253649&pn=17&rn=1&di=3701205750&ln=1676&fr=&fmq=1541236406972_R&fm=rs1&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&oriquery=%E5%AE%BD%E5%9B%BE&objurl=http%3A%2F%2Fbpic.ooopic.com%2F15%2F64%2F42%2F15644277-b06a3fbd7745a392fab1ac9a21dcbe50.jpg&rpstart=0&rpnum=0&adpicid=0\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"imageUrl\": \"http://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E5%AE%BD%E5%9B%BE%E8%83%8C%E6%99%AF&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&cs=3428309959,1480357108&os=2110224293,1400436748&simid=0,0&pn=2&rn=1&di=85716488880&ln=1676&fr=&fmq=1541236406972_R&fm=rs1&ic=undefined&s=undefined&se=&sme=&tab=0&width=1024&height=480&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=78&oriquery=%E5%AE%BD%E5%9B%BE&objurl=http%3A%2F%2Fbpic.ooopic.com%2F16%2F54%2F92%2F16549228-3396654f265c6b942170b216f7c2a753.jpg&rpstart=0&rpnum=0&adpicid=0\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"imageUrl\": \"http://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E5%AE%BD%E5%9B%BE%E8%83%8C%E6%99%AF&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&cs=3636747541,1994743070&os=2896142659,34293409&simid=3290763023,276872586&pn=8&rn=1&di=107910405680&ln=1676&fr=&fmq=1541236406972_R&fm=rs1&ic=undefined&s=undefined&se=&sme=&tab=0&width=1024&height=480&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=78&oriquery=%E5%AE%BD%E5%9B%BE&objurl=http%3A%2F%2Fbpic.ooopic.com%2F16%2F37%2F91%2F16379119-59336ecc6904333c3d1d880ed6e461dd-4.jpg&rpstart=0&rpnum=0&adpicid=0\"\n" +
            "                    }\n" +
            "                ],\n" +
            "                \"imageUrl\": \"\",\n" +
            "                \"text\": \"\",\n" +
            "                \"spanSize\": 3,\n" +
            "                \"goodsId\": \"1\"\n" +
            "            }\n" +
            "        },\n" +
            "        {\n" +
            "            \"item2\": {\n" +
            "                \"banners\": [\n" +
            "                    \n" +
            "                ],\n" +
            "                \"imageUrl\": \"http://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E5%AE%BD%E5%9B%BE%E8%83%8C%E6%99%AF&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&cs=313001586,2990268225&os=2047997987,4006238210&simid=0,0&pn=18&rn=1&di=106846319690&ln=1676&fr=&fmq=1541236406972_R&fm=rs1&ic=undefined&s=undefined&se=&sme=&tab=0&width=1024&height=480&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=78&oriquery=%E5%AE%BD%E5%9B%BE&objurl=http%3A%2F%2Fbpic.ooopic.com%2F16%2F81%2F22%2F16812205-afe75bedf7e5c3eee4fa0e2c6eaf11f7-1.jpg&rpstart=0&rpnum=0&adpicid=0\",\n" +
            "                \"text\": \"特购特购\",\n" +
            "                \"spanSize\": 0,\n" +
            "                \"goodsId\": \"2\"\n" +
            "            }\n" +
            "        },\n" +
            "        {\n" +
            "            \"item3\": {\n" +
            "                \"banners\": [\n" +
            "                    \n" +
            "                ],\n" +
            "                \"imageUrl\": \"\",\n" +
            "                \"text\": \"降价秒杀\",\n" +
            "                \"spanSize\": 0,\n" +
            "                \"goodsId\": \"3\"\n" +
            "            }\n" +
            "        },\n" +
            "        {\n" +
            "            \"item4\": {\n" +
            "                \"banners\": [\n" +
            "                    \n" +
            "                ],\n" +
            "                \"imageUrl\": \"http://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E5%AE%BD%E5%9B%BE%E8%83%8C%E6%99%AF&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&cs=313001586,2990268225&os=2047997987,4006238210&simid=0,0&pn=18&rn=1&di=106846319690&ln=1676&fr=&fmq=1541236406972_R&fm=rs1&ic=undefined&s=undefined&se=&sme=&tab=0&width=1024&height=480&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=78&oriquery=%E5%AE%BD%E5%9B%BE&objurl=http%3A%2F%2Fbpic.ooopic.com%2F16%2F81%2F22%2F16812205-afe75bedf7e5c3eee4fa0e2c6eaf11f7-1.jpg&rpstart=0&rpnum=0&adpicid=0\",\n" +
            "                \"text\": \"\",\n" +
            "                \"spanSize\": 0,\n" +
            "                \"goodsId\": \"4\"\n" +
            "            }\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    private String json2 = "{\n" +
            "    \"data\": [\n" +
            "        {\n" +
            "            \"item2\": {\n" +
            "                \"banners\": [\n" +
            "                    \n" +
            "                ],\n" +
            "                \"imageUrl\": \"http://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E5%AE%BD%E5%9B%BE%E8%83%8C%E6%99%AF&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&cs=313001586,2990268225&os=2047997987,4006238210&simid=0,0&pn=18&rn=1&di=106846319690&ln=1676&fr=&fmq=1541236406972_R&fm=rs1&ic=undefined&s=undefined&se=&sme=&tab=0&width=1024&height=480&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=78&oriquery=%E5%AE%BD%E5%9B%BE&objurl=http%3A%2F%2Fbpic.ooopic.com%2F16%2F81%2F22%2F16812205-afe75bedf7e5c3eee4fa0e2c6eaf11f7-1.jpg&rpstart=0&rpnum=0&adpicid=0\",\n" +
            "                \"text\": \"特购特购\",\n" +
            "                \"spanSize\": 0,\n" +
            "                \"goodsId\": \"2\"\n" +
            "            }\n" +
            "        },\n" +
            "        {\n" +
            "            \"item3\": {\n" +
            "                \"banners\": [\n" +
            "                    \n" +
            "                ],\n" +
            "                \"imageUrl\": \"\",\n" +
            "                \"text\": \"降价秒杀\",\n" +
            "                \"spanSize\": 0,\n" +
            "                \"goodsId\": \"3\"\n" +
            "            }\n" +
            "        },\n" +
            "        {\n" +
            "            \"item4\": {\n" +
            "                \"banners\": [\n" +
            "                    \n" +
            "                ],\n" +
            "                \"imageUrl\": \"http://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E5%AE%BD%E5%9B%BE%E8%83%8C%E6%99%AF&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&cs=313001586,2990268225&os=2047997987,4006238210&simid=0,0&pn=18&rn=1&di=106846319690&ln=1676&fr=&fmq=1541236406972_R&fm=rs1&ic=undefined&s=undefined&se=&sme=&tab=0&width=1024&height=480&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=78&oriquery=%E5%AE%BD%E5%9B%BE&objurl=http%3A%2F%2Fbpic.ooopic.com%2F16%2F81%2F22%2F16812205-afe75bedf7e5c3eee4fa0e2c6eaf11f7-1.jpg&rpstart=0&rpnum=0&adpicid=0\",\n" +
            "                \"text\": \"\",\n" +
            "                \"spanSize\": 0,\n" +
            "                \"goodsId\": \"4\"\n" +
            "            }\n" +
            "        }\n" +
            "    ]\n" +
            "}";
}
