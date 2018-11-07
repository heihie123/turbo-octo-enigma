package com.example.latte_core.ui.refresh;

import android.content.Context;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.latte_core.app.Latte;
import com.example.latte_core.ui.loader.LatteLoader;
import com.example.latte_core.ui.loader.LoaderStyle;
import com.example.latte_core.ui.recycler.BaseDataConverter;
import com.example.latte_core.ui.recycler.MultipleRecyclerAdapter;

/**
 * 下拉刷新
 */
public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    // 尽量让成员变量在构造方法中赋值，如果不能，设为null
    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final RecyclerView INDEX_LIST;
    private final IndexPageBean BEAN;
    private MultipleRecyclerAdapter mAdapter = null;
    private BaseDataConverter CONVERTER;
    private Context mContext;

    private RefreshHandler(Context context, final SwipeRefreshLayout REFRESH_LAYOUT, RecyclerView indexList,
                           BaseDataConverter converter, IndexPageBean bean) {
        this.REFRESH_LAYOUT = REFRESH_LAYOUT;
        this.INDEX_LIST = indexList;
        this.CONVERTER = converter;
        this.BEAN = bean;
        this.mContext = context;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    public static RefreshHandler create(Context context, SwipeRefreshLayout mSwipeRefreshLayout, RecyclerView mIndexList,
                                        BaseDataConverter converter) {
        return new RefreshHandler(context, mSwipeRefreshLayout, mIndexList, converter, new IndexPageBean());
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
        }, 1000);
    }

    @Override
    public void onLoadMoreRequested() {
        requestPage();
    }

    public void requestFirst() {
        // 模拟网络请求
        LatteLoader.showLoading(mContext, LoaderStyle.BallScaleRippleMultipleIndicator);
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String dataJson = "request_first";
                CONVERTER.setJsonData(dataJson);
                mAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJsonData(dataJson));
                mAdapter.setOnLoadMoreListener(RefreshHandler.this, INDEX_LIST);
                INDEX_LIST.setAdapter(mAdapter);
                LatteLoader.stopLoading();
            }
        }, 1000);
    }

    private void requestPage() {
        // 模拟网络请求
        LatteLoader.showLoading(mContext, LoaderStyle.PacmanIndicator);
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String dataJson = "request_other";
                CONVERTER.setJsonData(dataJson);
                mAdapter.refresh(CONVERTER.convert());
                LatteLoader.stopLoading();
            }
        }, 1000);
    }
}
