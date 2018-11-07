package com.example.latte_ec.main.index;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import com.example.latte_core.app.Latte;
import com.example.latte_core.ui.recycler.RGBValue;
import com.example.latte_ec.R;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/**
 * Toolbar：被依赖的对象
 */
public class TransluncentBehavior extends CoordinatorLayout.Behavior<Toolbar> {

    // 距离顶部的距离
    private int mOffset = 0;
    // 延长滑动过程
    private static final int MORE = 100;
    // 颜色变化速度
    private static final int SHOW_SPEED = 3;
    // 自定义颜色
    private final RGBValue RGB_VALUE = RGBValue.create(255, 124, 2);

    // 一定要有构造方法（通过反射获取）
    public TransluncentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // 依赖的对象
    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull Toolbar child,
                                   @NonNull View dependency) {
        return dependency.getId() == R.id.rv_index;
    }

    // 接管其事件
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull Toolbar child,
                                       @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return true;
    }

    /**
     * 处理逻辑
     *
     * @param child  behavior关联的view
     * @param target 滑动的view
     * @param dx     水平滑动距离
     * @param dy     垂直滑动距离
     */
    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull Toolbar child,
                                  @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        old(child, dy);
    }

    private void old(Toolbar child, int dy) {
        mOffset += dy;
        // toolbar的高度
        final int targetHeight = child.getBottom();
        if (mOffset > 0 && mOffset <= targetHeight) {
            final float floatScale = (float) mOffset / targetHeight;
            final float alpha = floatScale * 255;
            child.setBackgroundColor(Color.argb((int) alpha, RGB_VALUE.red(), RGB_VALUE.green(), RGB_VALUE.blue()));
        } else if (mOffset > targetHeight) {
            child.setBackgroundColor(Color.rgb(RGB_VALUE.red(), RGB_VALUE.green(), RGB_VALUE.blue()));
        }
    }

    private void neww(Toolbar child, int dy) {
        final Context context = Latte.getApplication();
        final int startOffset = 0;
        final int endOffset = context.getResources().getDimensionPixelOffset(R.dimen._toolbar_height) + MORE;
        mOffset += dy;
        if (mOffset <= startOffset) {
            child.getBackground().setAlpha(0);
        } else if (mOffset < endOffset) {
            final float floatScale = (float) (mOffset - startOffset / endOffset);
            // 数字舍入为最接近的整数
            final int alpha = Math.round(floatScale * 255);
            child.getBackground().setAlpha(alpha);
        } else if (mOffset >= endOffset) {
            child.getBackground().setAlpha(255);
        }
    }
}
