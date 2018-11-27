package com.example.latte_ec.main.index;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.latte_core.detegates.bottom.BottomItemDelegate;
import com.example.latte_core.ui.recycler.BaseDescription;
import com.example.latte_core.ui.refresh.RefreshHandler;
import com.example.latte_ec.R;
import com.example.latte_ec.main.EcBottomDelegate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * 首页delegate
 */
public class IndexDelagate extends BottomItemDelegate implements View.OnClickListener, View.OnFocusChangeListener {

    private SwipeRefreshLayout mSwipeRefreshLayout = null;
    private RecyclerView mIndexList = null;

    private RefreshHandler mRefreshHandler = null;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Toolbar toolbar = view.findViewById(R.id.tb_index);
        toolbar.setAlpha(0);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {
        initView();
        mRefreshHandler = RefreshHandler.create(getActivityContext(), mSwipeRefreshLayout, mIndexList, new IndexDataConverter());
    }

    private void initView() {
        mSwipeRefreshLayout = $(R.id.srl_index);
        mIndexList = $(R.id.rv_index);
        $(R.id.icon_index_scan).setOnClickListener(this);
        $(R.id.icon_index_message).setOnClickListener(this);
        $(R.id.et_search_view).setOnFocusChangeListener(this);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        // 懒加载
        super.onLazyInitView(savedInstanceState);
        initRefresh();
        initRecycleView();
        mRefreshHandler.requestFirst();
    }

    private void initRefresh() {
        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        // scale是否由小变大、回弹由小变大
        mSwipeRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    private void initRecycleView() {
        final Context context = getActivityContext();
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 4);
        mIndexList.setLayoutManager(gridLayoutManager);
        if (context != null) {
            mIndexList.addItemDecoration(BaseDescription.create(ContextCompat.getColor(context, R.color.BACKGROUND_GRAY_LIGHT), 3));
        }
        final EcBottomDelegate ecBottomDelegate = getParentDelegate();
        mIndexList.addOnItemTouchListener(new IndexItemClickListener(ecBottomDelegate));
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.icon_index_scan) {

        }
        if (id == R.id.icon_index_message) {

        }
    }

    @Override
    public void onFocusChange(View view, boolean b) {

    }
}
