package com.example.latte_ec.launcher;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.latte_core.app.AccountManager;
import com.example.latte_core.app.IUserChecker;
import com.example.latte_core.detegates.LatteDelegate;
import com.example.latte_core.ui.Laucher.ILaucherListener;
import com.example.latte_core.ui.Laucher.LaucherHoldCreator;
import com.example.latte_core.ui.Laucher.OnLauncherFinishTag;
import com.example.latte_core.ui.Laucher.ScrollLaucherTag;
import com.example.latte_core.util.storage.LattePreference;
import com.example.latte_ec.R;

import java.util.ArrayList;

/**
 * 滑动页
 */
public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenienBanner = null;
    private static final ArrayList<Integer> BANNER_IDS = new ArrayList<>();
    private ILaucherListener mILaucherListener = null;

    private void initBanner() {
        BANNER_IDS.add(R.mipmap.launcher_11);
        BANNER_IDS.add(R.mipmap.launcher_22);
        // 业务代码中能用图片代替代码的就用图片（简单/快速迭代）
        // 框架中能用代码代替图片的就用代码(占用空间少，代码可控)
        mConvenienBanner
                .setPages(new LaucherHoldCreator(), BANNER_IDS)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ILaucherListener) {
            mILaucherListener = (ILaucherListener) context;
        }
    }

    @Override
    public Object setLayout() {
        mConvenienBanner = new ConvenientBanner<>(getActivityContext());
        return mConvenienBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        if (position == BANNER_IDS.size() - 1) {
            LattePreference.setAppFlag(ScrollLaucherTag.HAS_FIRST_LAUCHER_APP.name(), true);
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mILaucherListener != null) {
                        mILaucherListener.onLaucherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mILaucherListener != null) {
                        mILaucherListener.onLaucherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }
    }
}
