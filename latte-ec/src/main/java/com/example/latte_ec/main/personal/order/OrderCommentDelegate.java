package com.example.latte_ec.main.personal.order;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.latte_core.detegates.LatteDelegate;
import com.example.latte_core.ui.widget.AutoPhotoLayout;
import com.example.latte_core.ui.widget.StarLayout;
import com.example.latte_core.util.ToastUtils;
import com.example.latte_core.util.callback.CallbackManager;
import com.example.latte_core.util.callback.CallbackType;
import com.example.latte_core.util.callback.IGlobalCallback;
import com.example.latte_core.util.image.GlideUtils;
import com.example.latte_ec.R;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import de.hdodenhof.circleimageview.CircleImageView;

public class OrderCommentDelegate extends LatteDelegate {

    private CircleImageView mGoodsImg;
    private StarLayout mStarLayout = null;
    private AutoPhotoLayout mAutoPhotoLayout = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_comment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {
        mGoodsImg = $(R.id.img_comment);
        mStarLayout = $(R.id.star_comment);
        mAutoPhotoLayout = $(R.id.photo_comment);
        mAutoPhotoLayout.setDelegate(this);
        ((AppCompatTextView) $(R.id.txt_toolbar_title)).setText("晒单评价");
        ((AppCompatTextView) $(R.id.txt_toolbar_right)).setText("提交");
        CallbackManager.getInstance().addCallbacks(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
            @Override
            public void executeCallback(@Nullable Uri args) {
                mAutoPhotoLayout.onCropTarget(args);
            }
        });
        $(R.id.txt_toolbar_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSubmint();
            }
        });
    }

    private void onClickSubmint() {
        ToastUtils.showShotToast("评分" + mStarLayout.getStarCount());
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        GlideUtils.loadNormalImg(mContext, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1554112914285&di=02c084f626321df68628ed07d804be8c&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20180115%2F4c776949e08940a5ac12e5845fe8fcb7.jpeg",
                mGoodsImg);
    }
}
