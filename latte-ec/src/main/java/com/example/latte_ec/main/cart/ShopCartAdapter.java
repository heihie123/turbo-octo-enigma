package com.example.latte_ec.main.cart;

import android.graphics.Color;
import android.view.View;

import com.example.latte_core.ui.recycler.MultipleFields;
import com.example.latte_core.ui.recycler.MultipleItemEntity;
import com.example.latte_core.ui.recycler.MultipleRecyclerAdapter;
import com.example.latte_core.ui.recycler.MultipleViewHolder;
import com.example.latte_core.util.image.GlideUtils;
import com.example.latte_ec.R;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.List;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

/**
 * 购物车Adapter
 */
public class ShopCartAdapter extends MultipleRecyclerAdapter {

    private boolean mIsSelectAll = false;
    private ICartItemListener mICartItemListener = null;
    private double mTotalPrice = 0.00;

    ShopCartAdapter(List<MultipleItemEntity> data) {
        super(data);
        for (MultipleItemEntity entity : data) {
            final double price = entity.getField(ShopCartItemFields.PRICE);
            final int count = entity.getField(ShopCartItemFields.COUNT);
            final double total = price * count;
            mTotalPrice = mTotalPrice + total;
        }
        addItemType(ShopCartItemType.SHOP_CART_ITEM, R.layout.item_shop_cart);
    }

    @Override
    protected void convert(MultipleViewHolder holder, final MultipleItemEntity entity) {
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

                titleTxt.setText(title);
                descTxt.setText(desc);
                priceTxt.setText(String.valueOf(price));
                countTxt.setText(String.valueOf(count));
                GlideUtils.loagNormalImg(mContext, thumb, thumbImg);
                // 在左侧勾勾渲染之前改变全选与否状态
                entity.setField(ShopCartItemFields.IS_SELECTED, mIsSelectAll);
                final boolean isSelected = entity.getField(ShopCartItemFields.IS_SELECTED);
                setSelectUI(isSelectedIcon, isSelected);

                isSelectedIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean currentSelected = entity.getField(ShopCartItemFields.IS_SELECTED);
                        currentSelected = !currentSelected;
                        entity.setField(ShopCartItemFields.IS_SELECTED, currentSelected);
                        setSelectUI(isSelectedIcon, currentSelected);
                    }
                });
                minusIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int currentCount = entity.getField(ShopCartItemFields.COUNT);
                        if (Integer.parseInt(countTxt.getText().toString()) > 1) {
                            currentCount--;
                            countTxt.setText(String.valueOf(countTxt));
                            if (mICartItemListener != null) {
                                mTotalPrice = mTotalPrice - price;
                                final double itemTotal = currentCount * price;
                                mICartItemListener.onItemClick(itemTotal);
                            }
                        }
                    }
                });
                iconPlusIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int currentCount = entity.getField(ShopCartItemFields.COUNT);
                        currentCount++;
                        countTxt.setText(String.valueOf(countTxt));
                        if (mICartItemListener != null) {
                            mTotalPrice = mTotalPrice + price;
                            final double itemTotal = currentCount * price;
                            mICartItemListener.onItemClick(itemTotal);
                        }
                    }
                });
                break;
        }
    }

    private void setSelectUI(IconTextView isSelectedIcon, Boolean isSelected) {
        if (isSelected) {
            isSelectedIcon.setTextColor(ContextCompat.getColor(mContext, R.color.APPBG_DARK));
        } else {
            isSelectedIcon.setTextColor(Color.GRAY);
        }
    }

    public void setIsSelectAll(boolean isSelectAll) {
        this.mIsSelectAll = isSelectAll;
    }

    public void setICartItemListener(ICartItemListener iCartItemListener) {
        this.mICartItemListener = iCartItemListener;
    }

    public double getTotalPrice() {
        return mTotalPrice;
    }
}
