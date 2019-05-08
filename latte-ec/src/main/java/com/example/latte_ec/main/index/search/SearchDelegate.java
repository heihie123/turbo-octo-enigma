package com.example.latte_ec.main.index.search;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.StringUtils;
import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;
import com.example.latte_core.detegates.LatteDelegate;
import com.example.latte_core.util.storage.LattePreference;
import com.example.latte_ec.R;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索的delegate
 */
public class SearchDelegate extends LatteDelegate implements View.OnClickListener {

    private AppCompatEditText mSearchEdit = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_search;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {
        initView();
        initList();
    }

    private void initView() {
        mSearchEdit = $(R.id.et_search_view);
        final IconTextView backIcon = $(R.id.icon_index_scan);
        final IconTextView searchIcon = $(R.id.icon_index_message);

        backIcon.setText("{fa-arrow-left}");
        searchIcon.setText("搜索");
        backIcon.setOnClickListener(this);
        backIcon.setOnClickListener(this);
    }


    private void initList() {
        final RecyclerView searchList = $(R.id.list_search);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        searchList.setLayoutManager(linearLayoutManager);

        final SearchAdapter searchAdapter = new SearchAdapter(new SearchDataConverter().convert());
        searchList.setAdapter(searchAdapter);

        final DividerItemDecoration itemDecoration = new DividerItemDecoration();
        itemDecoration.setDividerLookup(new DividerItemDecoration.DividerLookup() {
            @Override
            public Divider getVerticalDivider(int position) {
                return null;
            }

            @Override
            public Divider getHorizontalDivider(int position) {
                return new Divider.Builder()
                        .size(2)
                        .margin(20, 20)
                        .color(Color.GRAY)
                        .build();
            }
        });
        searchList.addItemDecoration(itemDecoration);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.icon_index_scan) {
            getSupportDelegate().pop();
        }
        if (id == R.id.icon_index_message) {
            onClickSearch();
        }
    }

    private void onClickSearch() {
        final String searchitemText = mSearchEdit.getText().toString();
        saveItem(searchitemText);
        mSearchEdit.setText("");
    }

    // 缓存搜索记录
    private void saveItem(String item) {
        if (!StringUtils.isEmpty(item) && !StringUtils.isSpace(item)) {
            // 去除旧数据
            List<String> history;
            final String historyStr = LattePreference.getCustomAppProfile(SearchDataConverter.TAG_SEARCH_HISTORY);
            if (StringUtils.isEmpty(historyStr)) {
                history = new ArrayList<>();
            } else {
                history = JSON.parseObject(historyStr, ArrayList.class);
            }
            // 存放该条记录
            history.add(item);
            // 保存数据
            final String json = JSON.toJSONString(history);
            LattePreference.addCustomAppProfile(SearchDataConverter.TAG_SEARCH_HISTORY, json);
        }
    }
}
