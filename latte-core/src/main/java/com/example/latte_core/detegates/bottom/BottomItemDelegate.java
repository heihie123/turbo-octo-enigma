package com.example.latte_core.detegates.bottom;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.example.latte_core.R;
import com.example.latte_core.detegates.BaseDelegate;

public abstract class BottomItemDelegate extends BaseDelegate implements View.OnKeyListener {

    private long mExitTime = 0;
    private static final int EXIT_TIME = 2000;

    @Override
    public void onResume() {
        super.onResume();
        final View rootView = getView();
        if (rootView != null) {
            rootView.setFocusableInTouchMode(true);
            rootView.requestFocus();
            rootView.setOnKeyListener(this);
        }
    }

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
        if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - mExitTime > mExitTime) {
                Toast.makeText(getActivityContext(), "双击退出" + getString(R.string.app_name), Toast.LENGTH_SHORT).show();
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
