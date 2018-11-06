package com.example.latte_core.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.latte_core.app.Latte;
import com.example.latte_core.ui.recycler.BaseDataConverter;
import com.example.latte_core.ui.recycler.MultipleRecyclerAdapter;

/**
 * 下拉刷新
 */
public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final RecyclerView INDEX_LIST;
    private final IndexPageBean BEAN;
    private MultipleRecyclerAdapter mAdapter = null;
    private BaseDataConverter CONVERTER;

    private RefreshHandler(final SwipeRefreshLayout REFRESH_LAYOUT, RecyclerView indexList,
                           BaseDataConverter converter, IndexPageBean bean) {
        this.REFRESH_LAYOUT = REFRESH_LAYOUT;
        this.INDEX_LIST = indexList;
        this.CONVERTER = converter;
        this.BEAN = bean;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    public static RefreshHandler create(SwipeRefreshLayout mSwipeRefreshLayout, RecyclerView mIndexList,
                                        BaseDataConverter converter) {
        return new RefreshHandler(mSwipeRefreshLayout, mIndexList, converter, new IndexPageBean());
    }

    @Override
    public void onRefresh() {
        REFRESH_LAYOUT.setRefreshing(true);
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                REFRESH_LAYOUT.setRefreshing(false);
                requestPage();
            }
        }, 2000);
    }

    @Override
    public void onLoadMoreRequested() {
        requestPage();
    }

    public void requestFirst() {
        // 模拟网络请求
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String dataJson = "request_first";
                CONVERTER.setJsonData(dataJson);
                mAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJsonData(dataJson));
                mAdapter.setOnLoadMoreListener(RefreshHandler.this, INDEX_LIST);
                INDEX_LIST.setAdapter(mAdapter);
            }
        }, 1000);
    }

    private void requestPage() {
        // 模拟网络请求
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String dataJson = "request_other";
                CONVERTER.setJsonData(dataJson);
                mAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJsonData(dataJson));
                mAdapter.setOnLoadMoreListener(RefreshHandler.this, INDEX_LIST);
                INDEX_LIST.setAdapter(mAdapter);
            }
        }, 1000);
    }
}
