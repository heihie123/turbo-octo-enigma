package com.example.latte_ec.main.personal;

import android.os.Bundle;
import android.view.View;

import com.example.latte_core.detegates.bottom.BottomItemDelegate;
import com.example.latte_ec.R;
import com.example.latte_ec.main.personal.address.AddressDelegate;
import com.example.latte_ec.main.personal.list.ListAdapter;
import com.example.latte_ec.main.personal.list.ListBean;
import com.example.latte_ec.main.personal.list.ListItemType;
import com.example.latte_ec.main.personal.order.OrderListDelegate;
import com.example.latte_ec.main.personal.profile.UserProfileDelegate;
import com.example.latte_ec.main.personal.settings.SettingsDelegate;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 个人delegate
 */
public class PersonalDelegate extends BottomItemDelegate implements View.OnClickListener {

    public static final String ORDER_TYPE = "ORDER_TYPE";
    private Bundle mArgs = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArgs = new Bundle();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {
        initView();
    }

    private void initView() {
        $(R.id.txt_all_account).setOnClickListener(this);
        $(R.id.img_user_avatar).setOnClickListener(this);
        final RecyclerView settingList = $(R.id.rv_personal_setting);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        settingList.setLayoutManager(linearLayoutManager);
        final ListAdapter listAdapter = new ListAdapter(getData());
        settingList.setAdapter(listAdapter);
        settingList.addOnItemTouchListener(new PersonalClickListener(this));
    }

    private List<ListBean> getData() {
        final List<ListBean> listBeans = new ArrayList<>();
        final ListBean address = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(1)
                .setDelegate(new AddressDelegate())
                .setText("收货地址")
                .build();
        final ListBean system = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setDelegate(new SettingsDelegate())
                .setText("系统设置")
                .build();
        listBeans.add(address);
        listBeans.add(system);
        return listBeans;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.img_user_avatar) {
            onClickAvatar();
        }
        if (id == R.id.txt_all_account) {
            onClickAllOrder();
        }
    }

    private void onClickAvatar() {
        getParentDelegate().getSupportDelegate().start(new UserProfileDelegate());
    }

    private void onClickAllOrder() {
        mArgs.putString(ORDER_TYPE, "all");
        startOrderListByType();
    }

    private void startOrderListByType() {
        final OrderListDelegate orderListDelegate = new OrderListDelegate();
        orderListDelegate.setArguments(mArgs);
        getParentDelegate().getSupportDelegate().start(orderListDelegate);
    }
}
