package com.example.latte_ec.main.sort.content;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 分类内容列表-json数据转换
 */
public class ContentDataConverter {

    final List<ContentBean> convert(String json) {
        final List<ContentBean> contentBeans = new ArrayList<>();
        final JSONArray jsonArray = JSON.parseObject(json1).getJSONArray("data");
        final int size = jsonArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject jsonObject = jsonArray.getJSONObject(i);
            final int id = jsonObject.getInteger("id");
            final String title = jsonObject.getString("section");
            final ContentBean contentBean = new ContentBean(true, title);
            contentBean.setId(id);
            contentBean.setIsMore(true);
            contentBeans.add(contentBean);
            final JSONArray goodsArray = jsonObject.getJSONArray("goods");
            final int goodSize = goodsArray.size();
            for (int j = 0; j < goodSize; j++) {
                JSONObject goodObject = goodsArray.getJSONObject(j);
                int goodId = goodObject.getInteger("goods_id");
                String goodImg = goodObject.getString("goods_img");
                String goodName = goodObject.getString("goods_name");
                final ContentItemEntity contentItemEntity = new ContentItemEntity();
                contentItemEntity.setGoodsId(goodId);
                contentItemEntity.setGoodsThumb(goodImg);
                contentItemEntity.setGoodsName(goodName);
                contentBeans.add(new ContentBean(contentItemEntity));
            }
        }
        return contentBeans;
    }

    private static final String json1 = "{\n" +
            "    \"data\": [\n" +
            "        {\n" +
            "            \"id\": 1,\n" +
            "            \"section\": \"运动品牌\",\n" +
            "            \"goods\": [\n" +
            "                {\n" +
            "                    \"goods_id\": 1,\n" +
            "                    \"goods_img\": \"http://img.zcool.cn/community/011d5057fdb672a84a0e282b37e404.jpg@1280w_1l_2o_100sh.png\",\n" +
            "                    \"goods_name\": \"阿迪达斯\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"goods_id\": 2,\n" +
            "                    \"goods_img\": \"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541943381911&di=f3664822a4eebc1fd9073b0f3e6833ac&imgtype=0&src=http://bbsimg.cardniu.com/data/attachment/forum/201806/2ccedab3bb8695b1bd6796.jpeg\",\n" +
            "                    \"goods_name\": \"耐克\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"goods_id\": 3,\n" +
            "                    \"goods_img\": \"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541943428722&di=603e108afceeeb10386427bf15696318&imgtype=0&src=http://a.hiphotos.baidu.com/zhidao/pic/item/4a36acaf2edda3ccdc185bb003e93901203f92a6.jpg\",\n" +
            "                    \"goods_name\": \"新百伦\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"goods_id\": 4,\n" +
            "                    \"goods_img\": \"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541943463363&di=a31b3d3218ad2ee6f9392c5d99ac3158&imgtype=0&src=http://o4.xiaohongshu.com/discovery/w640/7bff9fa5f69d17435f065145afc50c9c_640_640_92.jpg\",\n" +
            "                    \"goods_name\": \"aj\"\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 2,\n" +
            "            \"section\": \"手机品牌\",\n" +
            "            \"goods\": [\n" +
            "                {\n" +
            "                    \"goods_id\": 5,\n" +
            "                    \"goods_img\": \"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541957161412&di=a8a34d289459f7ed41e816abb6c58172&imgtype=0&src=http%3A%2F%2Fp1.qhimgs4.com%2Ft010b2b6e984c7ba124.jpg\",\n" +
            "                    \"goods_name\": \"iphone\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"goods_id\": 6,\n" +
            "                    \"goods_img\": \"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541957044909&di=914810ba2a166975c5535c2f794af56c&imgtype=0&src=http%3A%2F%2Fcms.qn.img-space.com%2Fproduct%2F158%2F238%2FceTH31SJI6ShA.jpg\",\n" +
            "                    \"goods_name\": \"华为\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"goods_id\": 7,\n" +
            "                    \"goods_img\": \"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541957005917&di=89c91cd3d194d6e7dc02856b69a32803&imgtype=0&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201805%2F31%2F181909by8vfl4gxglq8z9h.jpg\",\n" +
            "                    \"goods_name\": \"小米\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"goods_id\": 8,\n" +
            "                    \"goods_img\": \"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541957134290&di=30938115c491aa63f0fb99180e93fb92&imgtype=0&src=http%3A%2F%2Ffile.8080.net%2FPost%2F20171109%2F5a03c321b38f1447697171.jpg\",\n" +
            "                    \"goods_name\": \"三星\"\n" +
            "                }\n" +
            "            ]\n" +
            "        }\n" +
            "    ]\n" +
            "}";
}
