package com.example.latte_ec.sign;

import android.content.Context;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.latte_core.app.Latte;
import com.example.latte_core.detegates.LatteDelegate;
import com.example.latte_core.ui.loader.LatteLoader;
import com.example.latte_ec.R;
import com.google.android.material.textfield.TextInputEditText;

/**
 * 注册Fragment
 */
public class SignUpDelegate extends LatteDelegate implements View.OnClickListener {

    private TextInputEditText mNameEdit = null;
    private TextInputEditText mEmailEdit = null;
    private TextInputEditText mPhoneEdit = null;
    private TextInputEditText mPwdEdit = null;
    private TextInputEditText mConfirmPwdEdit = null;

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
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {
        initView();
        initEvent();
    }

    private void initView() {
        mNameEdit = $(R.id.edit_sign_up_name);
        mEmailEdit = $(R.id.edit_sign_up_email);
        mPhoneEdit = $(R.id.edit_sign_up_phone);
        mPwdEdit = $(R.id.edit_sign_up_pwd);
        mConfirmPwdEdit = $(R.id.edit_sign_up_confirm_pwd);
        ((AppCompatTextView) $(R.id.txt_toolbar_title)).setText(R.string.app_sign_up);
    }

    private void initEvent() {
        $(R.id.btn_sign_up).setOnClickListener(this);
        $(R.id.txt_link_sign_in).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn_sign_up) {
            onClickSignUp();
        }

        if (i == R.id.txt_link_sign_in) {
            onClickLink();
        }
    }

    private void onClickSignUp() {
        LatteLoader.showLoading(mContext);
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (checkForm()) {
                    String userJson = "{\"data\":{\"userId\":\"1\",\"name\" : " + mPhoneEdit.getText().toString()
                            + ",\"avatar\" :" + mConfirmPwdEdit.getText().toString()
                            + ",\"gender\":\"gender\",\"address\":\"address\"}}";
                    SignHandler.onSignUp(userJson, mISignListener);
                }
                LatteLoader.stopLoading();
            }
        }, 1000);
    }

    private boolean checkForm() {
        String nameStr = mNameEdit.getText().toString();
        String emailStr = mEmailEdit.getText().toString();
        String phoneStr = mPhoneEdit.getText().toString();
        String pwdStr = mPwdEdit.getText().toString();
        String confirmPwdStr = mConfirmPwdEdit.getText().toString();

        if (nameStr.isEmpty()) {
            mNameEdit.setError("请输入姓名");
            return false;
        }
        if (emailStr.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailStr).matches()) {
            mEmailEdit.setError("邮箱格式错误");
            return false;
        }
        if (phoneStr.isEmpty() || phoneStr.length() != 11) {
            mPhoneEdit.setError("手机号错误");
            return false;
        }
        if (pwdStr.isEmpty() || pwdStr.length() < 6) {
            mPwdEdit.setError("请填写至少6位密码");
            return false;
        }
        if (confirmPwdStr.isEmpty() || confirmPwdStr.length() < 6 || !pwdStr.equals(confirmPwdStr)) {
            mConfirmPwdEdit.setError("密码验证错误");
            return false;
        }
        mNameEdit.setError(null);
        mEmailEdit.setError(null);
        mPhoneEdit.setError(null);
        mPwdEdit.setError(null);
        mConfirmPwdEdit.setError(null);
        return true;
    }

    private void onClickLink() {
        getSupportDelegate().start(new SignInDelegate());
    }
}
