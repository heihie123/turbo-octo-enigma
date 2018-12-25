package com.example.latte_ec.main.cart;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.example.latte_core.detegates.bottom.BottomItemDelegate;
import com.example.latte_core.net.rx.RxRestClient;
import com.example.latte_core.ui.recycler.MultipleItemEntity;
import com.example.latte_core.util.ToastUtils;
import com.example.latte_ec.R;
import com.example.latte_ec.main.EcBottomDelegate;
import com.example.latte_ec.pay.FastPay;
import com.example.latte_ec.pay.IAlPayResultListener;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.ViewStubCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 购物车delegate
 */
public class ShopCartDelegate extends BottomItemDelegate implements ICartItemListener, View.OnClickListener, IAlPayResultListener {

    private RecyclerView mCartList = null;
    private IconTextView mSelectAllIcon = null;
    private ViewStubCompat mNoDataItem = null;
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
        mSelectAllIcon.setTag(0);
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
            @SuppressLint("RestrictedApi") final View stubView = mNoDataItem.inflate();
            final AppCompatTextView gobuyTxt = stubView.findViewById(R.id.txt_gobuy);
            gobuyTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUtils.showShotToast("您该购物了！");
                    final int indexTab = 0;
                    final EcBottomDelegate ecBottomDelegate = getParentDelegate();
                    final BottomItemDelegate indexDelegate = ecBottomDelegate.getItemDelegates().get(indexTab);
                    ecBottomDelegate.getSupportDelegate().showHideFragment(indexDelegate, ShopCartDelegate.this);
                    ecBottomDelegate.changeColor(indexTab);
                }
            });
            mCartList.setVisibility(View.GONE);
        } else {
            mCartList.setVisibility(View.VISIBLE);
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
        final String url = "";
        final WeakHashMap<String, Object> orderParams = new WeakHashMap<>();
        orderParams.put("userid", 1);
        orderParams.put("amount", mTotalPrice);
        orderParams.put("comment", "支付");
        orderParams.put("type", 1);
        orderParams.put("ordertype", 1);

        RxRestClient.builder()
                .url(url)
                .params(orderParams)
                .build()
                .post()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        ToastUtils.showShotToast("onSubscribe");
                    }

                    @Override
                    public void onNext(String s) {
                        ToastUtils.showShotToast("onNext" + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showShotToast("onError" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        FastPay fastPay = FastPay.create(ShopCartDelegate.this)
                                .setOrderId(1)
                                .setPayResultListener(ShopCartDelegate.this);
                        fastPay.beginPayDialog();
                    }
                });
    }

    private void onClickClear() {
        mShopCartAdapter.getData().clear();
        mShopCartAdapter.notifyDataSetChanged();
        checkItemCount();
    }

    private void onClickRemoveSelected() {
        final List<MultipleItemEntity> data = mShopCartAdapter.getData();
        final List<MultipleItemEntity> deleteEntities = new ArrayList<>();
        for (MultipleItemEntity entity : data) {
            final boolean isSelected = entity.getField(ShopCartItemFields.IS_SELECTED);
            if (isSelected) {
                deleteEntities.add(entity);
            }
        }
        for (MultipleItemEntity entity : deleteEntities) {
            int removePosition;
            final int entityPosition = entity.getField(ShopCartItemFields.POSITION);
            if (entityPosition > mCurrentCount - 1) {
                removePosition = entityPosition - (mTotalCount - mCurrentCount);
            } else {
                removePosition = entityPosition;
            }
            if (removePosition <= mShopCartAdapter.getItemCount()) {
                mShopCartAdapter.remove(removePosition);
                mCurrentCount = mShopCartAdapter.getItemCount();
                mShopCartAdapter.notifyItemRangeChanged(removePosition, mShopCartAdapter.getItemCount());
            }
        }
        checkItemCount();
    }

    private void onClickSelectAll() {
        final int tag = (int) mSelectAllIcon.getTag();
        if (tag == 0) {
            mSelectAllIcon.setTextColor(ContextCompat.getColor(mContext, R.color.APPBG_DARK));
            mSelectAllIcon.setTag(1);
            mShopCartAdapter.setIsSelectAll(true);
        } else {
            mSelectAllIcon.setTextColor(ContextCompat.getColor(mContext, R.color.TEXT_CART_DARK_GRAY));
            mSelectAllIcon.setTag(0);
            mShopCartAdapter.setIsSelectAll(false);
        }
        // 更新recycyleview显示状态
        mShopCartAdapter.notifyItemRangeChanged(0, mShopCartAdapter.getItemCount());
    }

    @Override
    public void onPaySuccess() {

    }

    @Override
    public void onPaying() {

    }

    @Override
    public void onPayFail() {

    }

    @Override
    public void onPayCancel() {

    }

    @Override
    public void onPayConnectError() {

    }
}
