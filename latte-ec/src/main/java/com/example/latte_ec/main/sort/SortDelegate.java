package com.example.latte_ec.main.sort;

import android.os.Bundle;
import android.view.View;

import com.example.latte_core.detegates.bottom.BottomItemDelegate;
import com.example.latte_ec.R;
import com.example.latte_ec.main.sort.content.ContentDelegate;
import com.example.latte_ec.main.sort.list.VerticalListDelegate;

import androidx.annotation.Nullable;

/**
 * 分类delegate
 */
public class SortDelegate extends BottomItemDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final VerticalListDelegate verticalListDelegate = new VerticalListDelegate();
        getSupportDelegate().loadRootFragment(R.id.sort_list_container, verticalListDelegate);
        getSupportDelegate().loadRootFragment(R.id.sort_content_container, ContentDelegate.newInstance(1));
    }
}
