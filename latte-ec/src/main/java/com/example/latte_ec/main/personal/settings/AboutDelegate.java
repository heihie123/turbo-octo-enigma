package com.example.latte_ec.main.personal.settings;

import android.os.Bundle;
import android.view.View;

import com.example.latte_core.detegates.LatteDelegate;
import com.example.latte_ec.R;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class AboutDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_about;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {
        ((AppCompatTextView) $(R.id.txt_toolbar_title)).setText("App简介");
        ((AppCompatTextView) $(R.id.txt_about_info)).setText("富强、民主、文明、和谐、自由、平等、公正、法治、爱国、敬业、诚信、友善");
    }
}
