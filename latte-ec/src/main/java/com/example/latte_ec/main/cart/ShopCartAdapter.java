package com.example.latte_ec.main.cart;

import com.example.latte_core.ui.recycler.MultipleFields;
import com.example.latte_core.ui.recycler.MultipleItemEntity;
import com.example.latte_core.ui.recycler.MultipleRecyclerAdapter;
import com.example.latte_core.ui.recycler.MultipleViewHolder;
import com.example.latte_ec.R;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.List;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * 购物车Adapter
 */
public class ShopCartAdapter extends MultipleRecyclerAdapter {

    private boolean mIsSeleceAll = false;
    private ICartItemListener mICartItemListener = null;
    private double mTotalPrice = 0.00;

    ShopCartAdapter(List<MultipleItemEntity> data) {
        super(data);
        for (MultipleItemEntity entity : data) {
            final double price = entity.getField(ShopCartItemFields.PRICE);
            final int count = entity.getField(ShopCartItemFields.COUNT);
            final double total = price * count;
            addItemType(ShopCartItemType.SHOP_CART_ITEM, R.layout.item_shop_cart
            );
        }
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case ShopCartItemType.SHOP_CART_ITEM:
                final int id = entity.getField(MultipleFields.ID);
                final String thumb = entity.getField(MultipleFields.IMAGE_URL);
                final String title = entity.getField(ShopCartItemFields.TITLE);
                final String desc = entity.getField(ShopCartItemFields.DESC);
                final int count = entity.getField(ShopCartItemFields.COUNT);
                final double price = entity.getField(ShopCartItemFields.PRICE);

                final IconTextView isSelectedIcon = holder.getView(R.id.icon_item_shop_cart);
                final AppCompatImageView thumbImg = holder.getView(R.id.img_item_shop_cart);
                final AppCompatTextView titleTxt = holder.getView(R.id.txt_item_shop_cart_title);
                final AppCompatTextView descTxt = holder.getView(R.id.txt_item_shop_cart_desc);
                final AppCompatTextView priceTxt = holder.getView(R.id.txt_item_shop_cart_price);
                final IconTextView minusIcon = holder.getView(R.id.icon_item_minus);
                final IconTextView iconPlusIcon = holder.getView(R.id.icon_item_plus);
                final AppCompatTextView countTxt = holder.getView(R.id.txt_item_shop_cart_count);
                break;
        }
    }

    public void setIsSeleceAll(boolean isSeleceAll) {
        this.mIsSeleceAll = isSeleceAll;
    }

    public void setICartItemListener(ICartItemListener iCartItemListener) {
        this.mICartItemListener = iCartItemListener;
    }

    public double getTotalPrice() {
        return mTotalPrice;
    }
}
