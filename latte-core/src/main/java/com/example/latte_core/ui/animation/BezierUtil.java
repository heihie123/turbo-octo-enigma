package com.example.latte_core.ui.animation;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.latte_core.R;

/**
 * 贝塞尔动画的工具类
 */
public final class BezierUtil {

    /**
     * 使用DecorView
     */
    static ViewGroup createAnimLayout(Activity activity) {
        final ViewGroup rootView = (ViewGroup) activity.getWindow().getDecorView();
        final LinearLayout animLayout = new LinearLayout(activity);
        final LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        animLayout.setLayoutParams(lp);
        animLayout.setId(Integer.MAX_VALUE - 1);
        animLayout.setBackgroundResource(android.R.color.transparent);
        rootView.addView(animLayout);
        return animLayout;
    }

    /**
     * 设置imageview的一些params并返回
     */
    static View addViewToAnimLayout(Context mContext, View view, int[] location, boolean wrap_content) {
        if (view == null) return null;
        int x = location[0];
        int y = location[1];
        final LinearLayout.LayoutParams params;
        // 是否自适应
        if (wrap_content) {
            Drawable drawable = null;
            if (view instanceof ImageView) {
                drawable = ((ImageView) view).getDrawable();
            }
            if (drawable == null) {
                params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
            } else {
                params = new LinearLayout.LayoutParams(
                        drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight());
            }
        } else {
            final int wh = mContext.getResources().getDimensionPixelSize(R.dimen._12dp);
            params = new LinearLayout.LayoutParams(wh, wh);
        }
        params.leftMargin = x;
        params.topMargin = y;
        view.setLayoutParams(params);
        return view;
    }

    /**
     * 开始动画
     */
    static void startAnimationForJd(final View view, int fromXDelta, int fromYDelta, int fx, int fy,
                                    int mx, int my, int tx, int ty, final AnimationListener listener) {
        final AnimationSet set = new AnimationSet(false);
        // 从起点到中心点的动画
        final TranslateAnimation translateAnimation1 = new TranslateAnimation(fromXDelta, mx - fx,
                fromYDelta, my - fy);
        translateAnimation1.setInterpolator(new DecelerateInterpolator());
        translateAnimation1.setRepeatCount(0);
        translateAnimation1.setFillAfter(false);
        set.addAnimation(translateAnimation1);
        // 从中心点到尾点的动画
        final TranslateAnimation translateAnimation2 = new TranslateAnimation(fromXDelta, tx - mx,
                fromYDelta, ty - my);
        translateAnimation2.setInterpolator(new AccelerateInterpolator());
        translateAnimation2.setRepeatCount(0);
        translateAnimation2.setFillAfter(false);
        set.addAnimation(translateAnimation2);

        set.setDuration(700);
        set.setFillAfter(false);
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);
                if (listener != null) {
                    listener.onAnimationEnd();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        view.startAnimation(set);
    }

    public interface AnimationListener {
        // 处理动画结束后的逻辑，不要涉及动画相关的View
        void onAnimationEnd();
    }
}
