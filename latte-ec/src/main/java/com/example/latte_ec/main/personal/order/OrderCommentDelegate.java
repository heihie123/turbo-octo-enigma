package com.example.latte_ec.main.personal.order;

import android.os.Bundle;
import android.view.View;

import com.example.latte_core.detegates.LatteDelegate;
import com.example.latte_core.ui.widget.AutoPhotoLayout;
import com.example.latte_core.ui.widget.StarLayout;
import com.example.latte_core.util.ToastUtils;
import com.example.latte_core.util.callback.CallbackManager;
import com.example.latte_core.util.callback.CallbackType;
import com.example.latte_core.util.callback.IGlobalCallback;
import com.example.latte_ec.R;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class OrderCommentDelegate extends LatteDelegate {

    private StarLayout mStarLayout = null;
    private AutoPhotoLayout mAutoPhotoLayout = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_comment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {
        mStarLayout = $(R.id.star_comment);
        mAutoPhotoLayout = $(R.id.photo_comment);
        mAutoPhotoLayout.setDelegate(this);
        ((AppCompatTextView) $(R.id.txt_toolbar_title)).setText("晒单评价");
        ((AppCompatTextView) $(R.id.txt_toolbar_title)).setText("提交");
        CallbackManager.getInstance().addCallbacks(CallbackType.ON_CROP, new IGlobalCallback() {
            @Override
            public void executeCallback(Object args) {
                mAutoPhotoLayout.onCropTarget(args);
            }
        });
        $(R.id.txt_toolbar_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSubmint();
            }
        });
    }

    private void onClickSubmint() {
        ToastUtils.showShotToast("评分" + mStarLayout.getStarCount());
    }
}
