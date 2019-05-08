package com.example.latte_core.detegates.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.latte_core.R;
import com.example.latte_core.detegates.LatteDelegate;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import me.yokeyword.fragmentation.ISupportFragment;

/**
 * 首页基类
 */
public abstract class BaseBottomDelegate extends LatteDelegate implements View.OnClickListener {

    private LinearLayoutCompat mBottomBar = null;
    private LayoutInflater inflate = null;

    private final List<BottomTabBean> TAB_BEANS = new ArrayList<>();                        // 底部标题iocn集合
    private final ArrayList<BottomItemDelegate> ITEM_DELEGATE = new ArrayList<>();          // 底部fragment集合
    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();   // 底部集合
    private int mCurrentDelegate = 0;   // 当前位置下标
    private int mClickedColor = Color.RED;

    public abstract int setIndexDelegate(); // 设置初始按钮的position

    @ColorInt
    public abstract int setClickedColor();  // 设置点击按钮颜色

    public abstract LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder); // 设置ITEMS

    public ArrayList<BottomItemDelegate> getItemDelegates() {
        return ITEM_DELEGATE;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCurrentDelegate = setIndexDelegate();
        if (setClickedColor() != 0) {
            mClickedColor = setClickedColor();
        }
        // 初始化底部数据
        final ItemBuilder builder = ItemBuilder.builder();
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = setItems(builder);
        ITEMS.putAll(items);
        for (Map.Entry<BottomTabBean, BottomItemDelegate> item : items.entrySet()) {
            TAB_BEANS.add(item.getKey());
            ITEM_DELEGATE.add(item.getValue());
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_bottom;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {
        init();
        initBottom();
    }

    private void init() {
        mBottomBar = $(R.id.bottom_bar);
        inflate = LayoutInflater.from(getActivityContext());
    }

    private void initBottom() {
        final int size = ITEMS.size();
        for (int i = 0; i < size; i++) {
            inflate.inflate(R.layout.bottom_item_icon_text_layout, mBottomBar);
            final ConstraintLayout item = (ConstraintLayout) mBottomBar.getChildAt(i);
            item.setTag(i);
            item.setOnClickListener(this);
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            final BottomTabBean tabBean = TAB_BEANS.get(i);
            itemIcon.setText(tabBean.getICON());
            itemTitle.setText(tabBean.getTITLE());
            if (i == mCurrentDelegate) {
                itemIcon.setTextColor(mClickedColor);
                itemTitle.setTextColor(mClickedColor);
            }
        }

        final ISupportFragment[] delegateArray = ITEM_DELEGATE.toArray(new ISupportFragment[size]);
        getSupportDelegate().loadMultipleRootFragment(R.id.bottom_bar_delegate_container, mCurrentDelegate, delegateArray);
    }

    @Override
    public void onClick(View view) {
        final int tabIndex = (int) view.getTag();
        changeColor(tabIndex);
        // 第一个显示，第二个隐藏
        getSupportDelegate().showHideFragment(ITEM_DELEGATE.get(tabIndex), ITEM_DELEGATE.get(mCurrentDelegate));
        mCurrentDelegate = tabIndex;
    }

    public void changeColor(int tabIndex) {
        resetColor();
        final ConstraintLayout item = (ConstraintLayout) mBottomBar.getChildAt(tabIndex);
        final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
        final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
        itemIcon.setTextColor(mClickedColor);
        itemTitle.setTextColor(mClickedColor);
    }

    private void resetColor() {
        final int count = mBottomBar.getChildCount();
        for (int i = 0; i < count; i++) {
            final ConstraintLayout item = (ConstraintLayout) mBottomBar.getChildAt(i);
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            itemIcon.setTextColor(Color.GRAY);
            itemTitle.setTextColor(Color.GRAY);
        }
    }
}
