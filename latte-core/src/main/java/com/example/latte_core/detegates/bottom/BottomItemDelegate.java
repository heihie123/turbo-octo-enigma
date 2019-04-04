package com.example.latte_core.detegates.bottom;

import android.view.KeyEvent;
import android.view.View;

import com.example.latte_core.R;
import com.example.latte_core.detegates.LatteDelegate;
import com.example.latte_core.util.ToastUtils;

/**
 * 首页四个底部Delegate的基类
 */
public abstract class BottomItemDelegate extends LatteDelegate implements View.OnKeyListener {

    private long mExitTime = 0;
    private static final int EXIT_TIME = 2000;

    @Override
    public void onResume() {
        super.onResume();
        final View rootView = getView();
        if (rootView != null) {
            rootView.setFocusableInTouchMode(true);     // 获取焦点
            rootView.requestFocus();
            rootView.setOnKeyListener(this);
        }
    }

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
        if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - mExitTime > EXIT_TIME) {
                ToastUtils.showShotToast("双击退出" + getString(R.string.app_name));
                mExitTime = System.currentTimeMillis();
            } else {
                _mActivity.finish();
                if (mExitTime != 0) {
                    mExitTime = 0;
                }
            }
            return true;
        }
        return false;
    }
}
