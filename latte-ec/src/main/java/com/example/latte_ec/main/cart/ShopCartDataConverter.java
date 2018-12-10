package com.example.latte_ec.main.cart;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.latte_core.ui.recycler.BaseDataConverter;
import com.example.latte_core.ui.recycler.MultipleFields;
import com.example.latte_core.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

public class ShopCartDataConverter extends BaseDataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        ArrayList<MultipleItemEntity> dataList = new ArrayList<>();
        JSONArray jsonArray = JSON.parseObject(json).getJSONArray("data");
        int size = jsonArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject jsonObject = jsonArray.getJSONObject(i);
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, ShopCartItemType.SHOP_CART_ITEM)
                    .setField(MultipleFields.ID, jsonObject.getInteger("id"))
                    .setField(MultipleFields.IMAGE_URL, jsonObject.getString("thumb"))
                    .setField(ShopCartItemFields.TITLE, jsonObject.getString("title"))
                    .setField(ShopCartItemFields.DESC, jsonObject.getString("desc"))
                    .setField(ShopCartItemFields.COUNT, jsonObject.getInteger("count"))
                    .setField(ShopCartItemFields.PRICE, jsonObject.getDouble("price"))
                    .setField(ShopCartItemFields.IS_SELECTED, false)
                    .setField(ShopCartItemFields.POSITION, i)
                    .build();
            dataList.add(entity);
        }
        return dataList;
    }

    private final String json = "{\n" +
            "    \"data\": [\n" +
            "        {\n" +
            "            \"id\": \"0\",\n" +
            "            \"thumb\": \"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1543819386264&di=e0293051a41f7f4cf5d283436bfa762d&imgtype=0&src=http%3A%2F%2Farticle-fd.zol-img.com.cn%2Fg5%2FM00%2F01%2F04%2FChMkJ1qUVn-Ie3AEAAApXYNMuWcAAk_wQI3qsAAACl1854.jpg\",\n" +
            "            \"title\": \"iphonx\",\n" +
            "            \"desc\": \"iphonex的描述\",\n" +
            "            \"count\": \"2\",\n" +
            "            \"price\": \"6999\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": \"1\",\n" +
            "            \"thumb\": \"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1543819478654&di=1786ed654fc2c7b693ccec8b0dc855c4&imgtype=0&src=http%3A%2F%2Fpic.k73.com%2Fup%2Farticle%2F2017%2F0810%2F094503_61990250.jpg\",\n" +
            "            \"title\": \"switch\",\n" +
            "            \"desc\": \"switch皮卡丘版本\",\n" +
            "            \"count\": \"3\",\n" +
            "            \"price\": \"2999\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": \"2\",\n" +
            "            \"thumb\": \"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3363059765,3780798889&fm=11&gp=0.jpg\",\n" +
            "            \"title\": \"马里奥\",\n" +
            "            \"desc\": \"马里奥赛车等等等\",\n" +
            "            \"count\": \"1\",\n" +
            "            \"price\": \"399\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": \"3\",\n" +
            "            \"thumb\": \"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1543819592840&di=a9758882662d8a05ec89e3a5c1c79fe1&imgtype=0&src=http%3A%2F%2Farticle-fd.zol-img.com.cn%2Ft_s500x2000%2Fg5%2FM00%2F00%2F09%2FChMkJ1vzasSIFjVZAAB7prVf5lMAAtQnwAne3AAAHu-100.jpg\",\n" +
            "            \"title\": \"华为mate20\",\n" +
            "            \"desc\": \"华为mate20限时促销的活动\",\n" +
            "            \"count\": \"4\",\n" +
            "            \"price\": \"4999\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";
}
