package com.example.latte_ec.sign;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Toast;

import com.example.latte_core.detegates.LatteDelegate;
import com.example.latte_core.ui.loader.LatteLoader;
import com.example.latte_core.wechat.LatteWeChat;
import com.example.latte_core.wechat.callback.IWeChatSignInCallback;
import com.example.latte_ec.R;

/**
 * 登录Fragment
 */
public class SignInDelegate extends LatteDelegate implements View.OnClickListener {

    private TextInputEditText mPhoneEdit = null;
    private TextInputEditText mPwdEdit = null;
    private AppCompatButton mSignInBen = null;

    Handler handler = new Handler();
    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ISignListener) {
            mISignListener = (ISignListener) context;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {
        initView();
        initEvent();
    }

    private void initView() {
        mPhoneEdit = $(R.id.edit_sign_in_phone);
        mPwdEdit = $(R.id.edit_sign_in_pwd);
        mSignInBen = $(R.id.btn_sign_in);
    }

    private void initEvent() {
        mSignInBen.setOnClickListener(this);
        $(R.id.txt_link_sign_up).setOnClickListener(this);
        $(R.id.txt_weixin_sign_in).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn_sign_in) {
            onClickSignIn();
        }

        if (i == R.id.txt_link_sign_up) {
            onClickLink();
        }

        if (i == R.id.txt_weixin_sign_in) {
            onClickWeiChat();
        }
    }

    private void onClickSignIn() {
        LatteLoader.showLoading(mContext);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (checkForm()) {
                    String userJson = "{\"data\":{\"userId\":\"1\",\"name\" : " + mPhoneEdit.getText().toString()
                            + ",\"avatar\" :" + mPwdEdit.getText().toString()
                            + ",\"gender\":\"gender\",\"address\":\"address\"}}";
                    SignHandler.onSignIn(userJson, mISignListener);
                }
                LatteLoader.stopLoading();
            }
        }, 1000);
    }

    private boolean checkForm() {
        String phoneStr = mPhoneEdit.getText().toString();
        String pwdStr = mPwdEdit.getText().toString();
        boolean isPass = true;

        if (phoneStr.isEmpty() || phoneStr.length() != 11) {
            mPhoneEdit.setError("手机号错误");
            isPass = false;
        } else {
            mPhoneEdit.setError(null);
        }
        if (pwdStr.isEmpty() || pwdStr.length() < 6) {
            mPwdEdit.setError("请填写至少6位密码");
            isPass = false;
        } else {
            mPwdEdit.setError(null);
        }
        return isPass;
    }

    private void onClickLink() {
        getSupportDelegate().start(new SignUpDelegate());
    }

    private void onClickWeiChat() {
        LatteWeChat.getInstance()
                .onSignSuccess(new IWeChatSignInCallback() {
                    @Override
                    public void onSignInSuccess(String userInfo) {
                        Toast.makeText(getActivity(), userInfo, Toast.LENGTH_SHORT).show();
                    }
                })
                .signIn();
    }
}
