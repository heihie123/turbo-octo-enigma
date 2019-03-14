package com.example.latte_ec.main.personal.settings;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.example.latte_core.detegates.LatteDelegate;
import com.example.latte_core.util.ToastUtils;
import com.example.latte_ec.R;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

/**
 * 输入姓名的Delegate
 */
public class NameDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_name;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {
        final AppCompatEditText nameEdit = $(R.id.edit_name);
        $(R.id.btn_name_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String nameStr = nameEdit.getText().toString();
                if (TextUtils.isEmpty(nameStr)) {
                    ToastUtils.showShotToast("请输入姓名");
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("nameStr", nameStr);
                    setFragmentResult(-1, bundle);
                    getSupportDelegate().pop();
                }
            }
        });
    }
}
