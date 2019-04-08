package com.example.latte_core.ui.animation;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.latte_core.detegates.LatteDelegate;
import com.example.latte_core.util.dimen.DimenUtil;

/**
 * 购物车动画
 */
public final class BezierAnimation {

    public static void addCart(LatteDelegate delegate, View startView, View endView, ImageView target,
                               BezierUtil.AnimationListener animationListener) {
        // 起点
        final int[] startXY = new int[2];
        startView.getLocationInWindow(startXY);
        startXY[0] += startView.getWidth() / 2;
        final int fx = startXY[0];
        final int fy = startXY[1];

        // view布局
        final ViewGroup anim_mask_layout = BezierUtil.createAnimLayout(delegate.getProxyActivity());
        anim_mask_layout.addView(target);
        final View view = BezierUtil.addViewToAnimLayout(delegate.getContext(), target, startXY, true);
        if (view == null) return;

        // 终点
        final int[] endXY = new int[2];
        endView.getLocationInWindow(endXY);
        final int tx = endXY[0] + endView.getWidth() / 2 - 48;
        final int ty = endXY[1] + endView.getHeight() / 2;

        // 中点
        final int mx = (tx + fx) / 2;
        final int my = DimenUtil.getScreenHeight() / 10;
        BezierUtil.startAnimationForJd(view, 0, 0, fx, fy, mx, my, tx, ty, animationListener);
    }
}
