package com.example.latte_ec.main.personal.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.latte_core.detegates.LatteDelegate;
import com.example.latte_core.util.ToastUtils;
import com.example.latte_ec.R;
import com.example.latte_ec.main.personal.list.ListAdapter;
import com.example.latte_ec.main.personal.list.ListBean;
import com.example.latte_ec.main.personal.list.ListItemType;
import com.example.latte_ec.main.personal.settings.NameDelegate;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 个人信息delegate
 */
public class UserProfileDelegate extends LatteDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_user_profile;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {
        ((AppCompatTextView) $(R.id.txt_toolbar_title)).setText("用户");

        RecyclerView userProfileList = $(R.id.list_user_profile);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        userProfileList.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(getData());
        userProfileList.setAdapter(adapter);
        userProfileList.addOnItemTouchListener(new UserProfileClickListener(this));
    }

    private List<ListBean> getData() {
        final ListBean image = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_AVATAR)
                .setId(1)
                .setImageUrl("http://i9.qhimg.com/t017d891ca365ef60b5.jpg")
                .build();

        final ListBean name = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setText("姓名")
                .setDelegate(new NameDelegate())
                .setValue("未设置姓名")
                .build();

        final ListBean gender = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(3)
                .setText("性别")
                .setValue("未设置性别")
                .build();

        final ListBean birth = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(4)
                .setText("生日")
                .setValue("未设置生日")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(image);
        data.add(name);
        data.add(gender);
        data.add(birth);
        return data;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1000:
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        ToastUtils.showShotToast(bundle.getString("nameStr", ""));
                    }
                    break;
            }
        }
    }
}
