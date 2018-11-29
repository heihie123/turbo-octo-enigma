package com.example.latte_ec.main.cart;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;

import com.example.latte_core.detegates.bottom.BottomItemDelegate;
import com.example.latte_core.util.ToastUtils;
import com.example.latte_ec.R;
import com.example.latte_ec.main.EcBottomDelegate;
import com.joanzapata.iconify.widget.IconTextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.ViewStubCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 购物车delegate
 */
public class ShopCartDelegate extends BottomItemDelegate implements ICartItemListener, View.OnClickListener {

    private RecyclerView mCartList = null;
    private IconTextView mSelectAllIcon = null;
    private ViewStub mNoDataItem = null;
    private AppCompatTextView mTotalPriceTxt = null;

    private int mCurrentCount = 0;
    private int mTotalCount = 0;
    private double mTotalPrice = 0.00;
    private ShopCartAdapter mShopCartAdapter = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {
        initView();

    }

    private void initView() {
        mCartList = $(R.id.list_shop_cart);
        mSelectAllIcon = $(R.id.icon_select_all);
        mNoDataItem = $(R.id.stub_nodata);
        mTotalPriceTxt = $(R.id.txt_price);
        AppCompatTextView leftText = $(R.id.txt_toolbar_left);
        AppCompatTextView rightText = $(R.id.txt_toolbar_right);

        leftText.setText(R.string.index_cart_empty);
        rightText.setText(R.string.index_cart_delete);
        ((AppCompatTextView) $(R.id.txt_toolbar_title)).setText(R.string.index_cart);

        leftText.setOnClickListener(this);
        rightText.setOnClickListener(this);
        $(R.id.icon_select_all).setOnClickListener(this);
        $(R.id.txt_pay).setOnClickListener(this);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mShopCartAdapter = new ShopCartAdapter(new ShopCartDataConverter().setJsonData("").convert());
        mShopCartAdapter.setICartItemListener(this);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mCartList.setLayoutManager(linearLayoutManager);
        mCartList.setAdapter(mShopCartAdapter);
        mTotalPrice = mShopCartAdapter.getTotalPrice();
        mTotalPriceTxt.setText(String.valueOf(mTotalPrice));
        checkItemCount();
    }

    private void checkItemCount() {
        final int count = mShopCartAdapter.getItemCount();
        if (count == 0) {
            final View stubView = mNoDataItem.inflate();
            final AppCompatTextView gobuyTxt = stubView.findViewById(R.id.txt_gobuy);
            gobuyTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUtils.showShotToast("您该购物了！");
                    final int indexTab = 0;
                    final EcBottomDelegate ecBottomDelegate = getParentDelegate();
//                    final BottomItemDelegate indexDelegate = ecBottomDelegate.
                }
            });
        }
    }

    @Override
    public void onItemClick(double itemTotalPrice) {
        final double price = mShopCartAdapter.getTotalPrice();
        mTotalPriceTxt.setText(String.valueOf(price));
    }

    @Override
    public void onClick(View view) {
        int vId = view.getId();
        if (vId == R.id.txt_toolbar_left) {
            onClickClear();
        }
        if (vId == R.id.txt_toolbar_right) {
            onClickRemoveSelected();
        }
        if (vId == R.id.icon_select_all) {
            onClickSelectAll();
        }
        if (vId == R.id.txt_pay) {
            onClickCreateOrder();
        }
    }

    private void onClickCreateOrder() {

    }

    private void onClickClear() {

    }

    private void onClickRemoveSelected() {

    }

    private void onClickSelectAll() {

    }
}
