package com.example.latte_ec.main.personal.order;

import android.os.Bundle;
import android.view.View;

import com.example.latte_core.detegates.LatteDelegate;
import com.example.latte_core.ui.recycler.MultipleItemEntity;
import com.example.latte_ec.R;
import com.example.latte_ec.main.personal.PersonalDelegate;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 订单列表delegate
 */
public class OrderListDelegate extends LatteDelegate {

    private String mType = null;
    private RecyclerView mOrderList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mType = args.getString(PersonalDelegate.ORDER_TYPE);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_list;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {
        initview();
    }

    private void initview() {
        mOrderList = $(R.id.rlist_order_lsit);
        ((AppCompatTextView) $(R.id.txt_toolbar_title)).setText("订单列表");
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mOrderList.setLayoutManager(linearLayoutManager);
        mOrderList.addOnItemTouchListener(new OrderListClickListener(this));
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final List<MultipleItemEntity> data = new OrderListDataConverter().setJsonData("").convert();
        final OrderListAdapter orderListAdapter = new OrderListAdapter(data);
        mOrderList.setAdapter(orderListAdapter);
    }
}
