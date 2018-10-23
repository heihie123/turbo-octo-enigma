package com.example.latte_ec.sign;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.latte_core.detegates.LatteDelegate;
import com.example.latte_core.ui.loader.LatteLoader;
import com.example.latte_ec.R;

/**
 * 注册
 */
public class SignUpDelegate extends LatteDelegate implements View.OnClickListener {

    private TextInputEditText mNameEdit;
    private TextInputEditText mEmailEdit;
    private TextInputEditText mPhoneEdit;
    private TextInputEditText mPwdEdit;
    private TextInputEditText mConfirmPwdEdit;
    private AppCompatButton mSignUpBen;
    private AppCompatTextView mLinkSignInTxt;

    private boolean isPass = true;
    Handler handler = new Handler();

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
        mSignUpBen = $(R.id.btn_sign_up);
        mLinkSignInTxt = $(R.id.txt_link_sign_in);
    }

    private void initEvent() {
        mSignUpBen.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn_sign_up) {
            if (checkForm()) {
                LatteLoader.showLoading(mContext);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mContext, "注册成功", Toast.LENGTH_SHORT).show();
                        LatteLoader.stopLoading();
                    }
                }, 1000);
            }
        }
    }

    private boolean checkForm() {
        String nameStr = mNameEdit.getText().toString();
        String emailStr = mEmailEdit.getText().toString();
        String phoneStr = mPhoneEdit.getText().toString();
        String pwdStr = mPwdEdit.getText().toString();
        String confirmPwdStr = mConfirmPwdEdit.getText().toString();

        if (nameStr.isEmpty()) {
            mNameEdit.setError("请输入姓名");
            isPass = false;
        } else {
            mNameEdit.setError(null);
        }
        if (emailStr.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailStr).matches()) {
            mEmailEdit.setError("邮箱格式错误");
            isPass = false;
        } else {
            mEmailEdit.setError(null);
        }
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
        if (confirmPwdStr.isEmpty() || confirmPwdStr.length() < 6 || !pwdStr.equals(confirmPwdStr)) {
            mConfirmPwdEdit.setError("密码验证错误");
            isPass = false;
        } else {
            mConfirmPwdEdit.setError(null);
        }
        return isPass;
    }
}
