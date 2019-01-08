package com.example.latte_core.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;

import androidx.appcompat.widget.LinearLayoutCompat;

public class StarLayout extends LinearLayoutCompat implements OnClickListener {

    private static final CharSequence ICON_UN_SELECT = "{fa-star-o}";
    private static final CharSequence ICON_SELECT = "{fa-star}";
    private static final int START_TOTAL_COUNT = 5;
    private static final ArrayList<IconTextView> STARS = new ArrayList<>();

    public StarLayout(Context context) {
        super(context);
    }

    public StarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initStarIcon();
    }

    private void initStarIcon() {
        for (int i = 0; i < START_TOTAL_COUNT; i++) {
            final IconTextView star = new IconTextView(getContext());
            star.setGravity(Gravity.CENTER);
            final LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.weight = 1;
            star.setLayoutParams(layoutParams);
            star.setText(ICON_UN_SELECT);
//            star.setTag();
//            star.setTag();
            star.setOnClickListener(this);
            STARS.add(star);
            this.addView(star);
        }
    }

    @Override
    public void onClick(View view) {
        final IconTextView star = (IconTextView) view;
        final int count = (int) star.getTag();
        final boolean isSelect = (boolean) star.getTag();
        if (!isSelect) {
            selectStar(count);
        } else {
            unSelectStar(count);
        }
    }

    private void selectStar(int count) {
    }

    private void unSelectStar(int count) {
    }

    public int getStarCount() {
        int count = 0;
        for (int i = 0; i < START_TOTAL_COUNT; i++) {
            final IconTextView star = STARS.get(i);
            final boolean isSelect = (boolean) star.getTag();
            if (isSelect) {
                count++;
            }
        }
        return count;
    }
}
