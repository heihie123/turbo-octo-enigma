package com.example.latte_ec.main.personal.order;

import android.annotation.SuppressLint;

import com.example.latte_core.ui.recycler.MultipleFields;
import com.example.latte_core.ui.recycler.MultipleItemEntity;
import com.example.latte_core.ui.recycler.MultipleRecyclerAdapter;
import com.example.latte_core.ui.recycler.MultipleViewHolder;
import com.example.latte_core.util.image.GlideUtils;
import com.example.latte_ec.R;

import java.util.List;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * 订单列表适配器
 */
public class OrderListAdapter extends MultipleRecyclerAdapter {

    protected OrderListAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(OrderListItemType.ITEM_ORDER_LIST, R.layout.item_order_list);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity item) {
        super.convert(holder, item);
        switch (holder.getItemViewType()) {
            case OrderListItemType.ITEM_ORDER_LIST:
                final AppCompatImageView orderImg = holder.getView(R.id.img_order);
                final AppCompatTextView titleTxt = holder.getView(R.id.txt_order_title);
                final AppCompatTextView priceTxt = holder.getView(R.id.txt_order_price);
                final AppCompatTextView timeTxt = holder.getView(R.id.txt_order_time);

                final String titleVal = item.getField(MultipleFields.TITLE);
                final String timeVal = item.getField(OrderItemFields.TIME);
                final double priceVal = item.getField(OrderItemFields.PRICE);
                final String imgVal = item.getField(MultipleFields.IMAGE_URL);
                titleTxt.setText(titleVal);
                titleTxt.setText("价格" + priceVal);
                titleTxt.setText("时间" + timeVal);
                GlideUtils.loadNormalImg(mContext, imgVal, orderImg);
                break;
        }
    }
}
