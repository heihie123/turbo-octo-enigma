package com.example.latte_ec.sign;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import com.example.latte_core.detegates.LatteDelegate;
import com.example.latte_core.ui.loader.LatteLoader;
import com.example.latte_ec.R;

/**
 * 登录
 */
public class SignInDelegate extends LatteDelegate implements View.OnClickListener {

    private TextInputEditText mPhoneEdit;
    private TextInputEditText mPwdEdit;
    private AppCompatButton mSignInBen;
    private AppCompatTextView mLinkSignUpTxt;

    Handler handler = new Handler();

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
        mLinkSignUpTxt = $(R.id.txt_link_sign_up);
    }

    private void initEvent() {
        mSignInBen.setOnClickListener(this);
        mLinkSignUpTxt.setOnClickListener(this);
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
    }

    private void onClickSignIn() {
        LatteLoader.showLoading(mContext);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (checkForm()) {
                    Toast.makeText(mContext, "登录成功", Toast.LENGTH_SHORT).show();
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
}
