package com.example.latte_ec.main.sort.list;

import android.os.Bundle;
import android.view.View;

import com.example.latte_core.detegates.LatteDelegate;
import com.example.latte_ec.R;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 分类垂直列表delegate
 */
public class VerticalListDelegate extends LatteDelegate {

    private RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_sort_list;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {
        initRecycleView();
    }

    private void initRecycleView() {
        mRecyclerView = $(R.id.rv_vertical_menu_list);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivityContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(null);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
//        LatteLoader.showLoading(getActivityContext(), LoaderStyle.PacmanIndicator);
//        Latte.getHandler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                LatteLoader.stopLoading();
//            }
//        }, 1000);
    }
}
