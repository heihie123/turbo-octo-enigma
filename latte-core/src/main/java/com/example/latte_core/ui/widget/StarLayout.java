package com.example.latte_core.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.example.latte_core.R;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;

import androidx.appcompat.widget.LinearLayoutCompat;

/**
 * 自定义评价星星View
 */
public class StarLayout extends LinearLayoutCompat implements OnClickListener {

    private static final CharSequence ICON_UN_SELECT = "{fa-star-o}";   // 非选中的icon
    private static final CharSequence ICON_SELECT = "{fa-star}";        // 选中的icon
    private static final int START_TOTAL_COUNT = 5;                     // 总数
    private static final ArrayList<IconTextView> STARS = new ArrayList<>(); //icon的集合

    public StarLayout(Context context) {
        this(context, null);
    }

    public StarLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initStarIcon();
    }

    // 添加5个icon
    private void initStarIcon() {
        for (int i = 0; i < START_TOTAL_COUNT; i++) {
            final IconTextView star = new IconTextView(getContext());
            star.setGravity(Gravity.CENTER);
            final LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.weight = 1;
            star.setLayoutParams(layoutParams);
            star.setText(ICON_UN_SELECT);
            star.setTag(R.id.star_count, i);
            star.setTag(R.id.star_is_select, false);
            star.setOnClickListener(this);
            STARS.add(star);
            this.addView(star);
        }
    }

    @Override
    public void onClick(View view) {
        final IconTextView star = (IconTextView) view;
        final int count = (int) star.getTag(R.id.star_count);
        setselect(count);
    }

    // 循环遍历icon，设置样式
    private void setselect(int count) {
        for (int i = 0; i < START_TOTAL_COUNT; i++) {
            if (i <= count) {
                selectStar(i);
            } else {
                unSelectStar(i);
            }
        }
    }

    // 设置选中样式
    private void selectStar(int i) {
        final IconTextView star = STARS.get(i);
        star.setText(ICON_SELECT);
        star.setTextColor(Color.RED);
        star.setTag(R.id.star_is_select, true);
    }

    // 设置非选中样式
    private void unSelectStar(int i) {
        final IconTextView star = STARS.get(i);
        star.setText(ICON_UN_SELECT);
        star.setTextColor(Color.GRAY);
        star.setTag(R.id.star_is_select, false);
    }

    // 获取选中的星星数
    public int getStarCount() {
        int count = 0;
        for (int i = 0; i < START_TOTAL_COUNT; i++) {
            final IconTextView star = STARS.get(i);
            final boolean isSelect = (boolean) star.getTag(R.id.star_is_select);
            if (isSelect) {
                count++;
            }
        }
        return count;
    }
}
