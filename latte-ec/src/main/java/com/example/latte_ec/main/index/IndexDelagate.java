package com.example.latte_ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.latte_core.detegates.bottom.BottomItemDelegate;
import com.example.latte_ec.R;
import com.joanzapata.iconify.widget.IconTextView;

/**
 * 首页底部按钮delegate
 */
public class IndexDelagate extends BottomItemDelegate {
    private SwipeRefreshLayout mSwipeRefreshLayout = null;
    private RecyclerView mIndexList = null;
    private Toolbar mToolbar = null;
    private IconTextView mScanIcon = null;
    private AppCompatEditText mSearchEdit = null;
    private IconTextView mMsgIcon = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {
        initView();
        initRefresh();
    }

    private void initView() {
        mSwipeRefreshLayout = $(R.id.srl_index);
        mIndexList = $(R.id.rv_index);
        mToolbar = $(R.id.tb_index);
        mScanIcon = $(R.id.icon_index_scan);
        mSearchEdit = $(R.id.et_search_view);
        mMsgIcon = $(R.id.icon_index_message);
    }

    private void initRefresh() {
        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_red_light
        );
        // scale是否由小变大
        mSwipeRefreshLayout.setProgressViewOffset(true,);
    }
}
