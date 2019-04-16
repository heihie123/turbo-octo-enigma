package com.example.latte_core.ui.refresh;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.latte_core.app.Latte;
import com.example.latte_core.ui.loader.LatteLoader;
import com.example.latte_core.ui.loader.LoaderStyle;
import com.example.latte_core.ui.recycler.BaseDataConverter;
import com.example.latte_core.ui.recycler.MultipleRecyclerAdapter;
import com.example.latte_core.util.ToastUtils;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

    private RefreshHandler(Context context, final SwipeRefreshLayout refreshLayout, RecyclerView indexList,
                           BaseDataConverter converter, IndexPageBean bean) {
        this.REFRESH_LAYOUT = refreshLayout;
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
        requestPage(true);
    }

    @Override
    public void onLoadMoreRequested() {
        requestPage(false);
    }

    // 首次加载
    public void requestFirst() {
        LatteLoader.showLoading(mContext, LoaderStyle.BallScaleRippleMultipleIndicator);
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initData();
                // 设置适配器
                mAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJsonData("request_first"));
                mAdapter.setOnLoadMoreListener(RefreshHandler.this, INDEX_LIST);
                INDEX_LIST.setAdapter(mAdapter);
                // 处理bean数据
                BEAN.setCurrentCount(mAdapter.getData().size());
                BEAN.addIndex();
                LatteLoader.stopLoading();
            }
        }, 1000);
    }

    // 加载数据
    private void requestPage(final boolean isInit) {
        // 模拟网络请求
        LatteLoader.showLoading(mContext, LoaderStyle.BallScaleRippleMultipleIndicator);
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isInit) {
                    initData();
                    // 设置适配器
                    mAdapter.refresh(CONVERTER.setJsonData("request_first").convert());
                    // 处理bean数据
                    BEAN.setCurrentCount(mAdapter.getData().size());
                    BEAN.addIndex();
                    REFRESH_LAYOUT.setRefreshing(false);
                } else {
                    final int index = BEAN.getPageIndex();
                    final int currentCount = BEAN.getCurrentCount();
                    final int pageSize = BEAN.getPageSize();
                    final int total = BEAN.getTotal();
                    // 判断是否还有更多
                    if (index > pageSize || currentCount > total) {
                        ToastUtils.showShotToast("已经没有更多了");
                        mAdapter.loadMoreEnd(true); // 已经没有更多了
                    } else {
//                        mAdapter.addData(CONVERTER.setJsonData("request_page").convert());
                        mAdapter.refresh(CONVERTER.setJsonData("request_page").convert());
                        BEAN.setCurrentCount(mAdapter.getData().size());
                        BEAN.addIndex();
                        mAdapter.loadMoreComplete(); // 加载成功
                    }
                }
                LatteLoader.stopLoading();
            }
        }, 1000);
    }

    // 初始化bean
    private void initData() {
        CONVERTER.clearData();
        BEAN.setTotal(30).setPageSize(5).setCurrentCount(0).setPageIndex(0).setDelayed(1000);
    }
}
