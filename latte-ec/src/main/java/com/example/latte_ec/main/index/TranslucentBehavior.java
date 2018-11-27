package com.example.latte_ec.main.index;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.latte_ec.R;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/**
 * Toolbar：被依赖的对象
 */
public class TranslucentBehavior extends CoordinatorLayout.Behavior<Toolbar> {

    // 距离顶部的距离
    private int mOffset = 0;
    private final int endOffset;
    // 延长滑动过程
    private static final int MORE = 100;
    // 颜色变化速度
    private static final int SHOW_SPEED = 3;

    // 一定要有构造方法（通过反射获取）
    public TranslucentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        endOffset = context.getResources().getDimensionPixelOffset(R.dimen._toolbar_height) + MORE;
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
        mOffset = (int) (child.getAlpha()*endOffset);
        return true;
    }

    /**
     * 处理逻辑
     *
     * @param child        behavior关联的view
     * @param target       滑动的view
     * @param dxConsumed 水平滑动距离
     * @param dyConsumed 垂直滑动距离（手指头上滑+，手指头下滑-）
     */
    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull Toolbar child,
                               @NonNull View target, int dxConsumed, int dyConsumed,
                               int dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed,
                dyConsumed, dxUnconsumed, dyUnconsumed, type);
        mOffset += dyConsumed;
        // 顶部
        int startOffset = 0;
        if (mOffset <= startOffset) {
            child.setAlpha(0);
        } else if (mOffset < endOffset) {
            final float floatScale = (float) (mOffset - startOffset) / endOffset;
//            final int alpha = Math.round(floatScale * 255);   // 数字舍入为最接近的整数
            child.setAlpha(floatScale);
        } else if (mOffset >= endOffset) {
            child.setAlpha(1);
        }
    }

    @Override
    public void onStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull Toolbar child, @NonNull View target, int type) {
        super.onStopNestedScroll(coordinatorLayout, child, target, type);

    }
}
