package com.example.latte_core.ui.scanner;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import me.dm7.barcodescanner.core.ViewFinderView;

/**
 * 扫描框view
 */
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
        setBorderColor(Color.RED);  // 四脚线颜色
        setBorderLineLength(100);   // 四角线长度
        setBorderAlpha(0.5f);       // 四角线透明度
        setBorderCornerRadius(20);  // 四脚线圆角
        setBorderStrokeWidth(20);   // 四脚线宽度
        // 扫描线
        setLaserEnabled(true);
        setLaserColor(Color.RED);
        // 背景色
        setMaskColor(Color.GREEN);
        // 偏移量
        setViewFinderOffset(0);
        // 主题色
        mLaserPaint.setColor(Color.GREEN);
    }
}
