package com.example.latte_ec.main.personal.address;

import android.os.Bundle;
import android.view.View;

import com.example.latte_core.detegates.LatteDelegate;
import com.example.latte_core.ui.recycler.MultipleItemEntity;
import com.example.latte_ec.R;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 地址delegate
 */
public class AddressDelegate extends LatteDelegate {

    private RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_address;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {
        mRecyclerView = $(R.id.rv_address);
        final LinearLayoutManager manager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(manager);
        final List<MultipleItemEntity> data = new AddressDataConverter().setJsonData("").convert();
        final AddressAdapter addressAdapter = new AddressAdapter(data);
        mRecyclerView.setAdapter(addressAdapter);
    }
}
