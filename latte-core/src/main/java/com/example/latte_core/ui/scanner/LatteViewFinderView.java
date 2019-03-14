package com.example.latte_core.ui.scanner;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import me.dm7.barcodescanner.core.ViewFinderView;

public class LatteViewFinderView extends ViewFinderView {

    public LatteViewFinderView(Context context) {
        this(context, null);
    }

    public LatteViewFinderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 正方形
        mSquareViewFinder = true;
        // 边框颜色
        mBorderPaint.setColor(Color.YELLOW);
        // 主题色
        mLaserPaint.setColor(Color.GREEN);
    }
}
