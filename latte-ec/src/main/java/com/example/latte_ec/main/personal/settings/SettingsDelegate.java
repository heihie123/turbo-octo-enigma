package com.example.latte_ec.main.personal.settings;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import com.example.latte_core.detegates.LatteDelegate;
import com.example.latte_core.util.ToastUtils;
import com.example.latte_core.util.callback.CallbackManager;
import com.example.latte_core.util.callback.CallbackType;
import com.example.latte_ec.R;
import com.example.latte_ec.main.personal.address.AddressDelegate;
import com.example.latte_ec.main.personal.list.ListAdapter;
import com.example.latte_ec.main.personal.list.ListBean;
import com.example.latte_ec.main.personal.list.ListItemType;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SettingsDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_settings;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {
        final ListBean pushBean = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_SWITCH)
                .setId(1)
                .setDelegate(new AddressDelegate())
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        if (isChecked) {
                            CallbackManager.getInstance().getCallbacks(CallbackType.TAG_OPEN_PUSH).executeCallback(null);
                            ToastUtils.showShotToast("打开推送");
                        } else {
                            CallbackManager.getInstance().getCallbacks(CallbackType.TAG_STOP_PUSH).executeCallback(null);
                            ToastUtils.showShotToast("关闭推送");
                        }
                    }
                })
                .setText("消息推送")
                .build();

        final ListBean aboutBean = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setDelegate(new AboutDelegate())
                .setText("关于")
                .build();

        List<ListBean> listBeans = new ArrayList<>();
        listBeans.add(pushBean);
        listBeans.add(aboutBean);

        ((AppCompatTextView) $(R.id.txt_toolbar_title)).setText("设置");
        RecyclerView settingsList = $(R.id.list_settings);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        settingsList.setLayoutManager(linearLayoutManager);
        final ListAdapter listAdapter = new ListAdapter(listBeans);
        settingsList.setAdapter(listAdapter);
        settingsList.addOnItemTouchListener(new SettingsClickListener(this));
    }
}
