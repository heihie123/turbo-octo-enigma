package com.example.chen10.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.example.latte_core.detegates.LatteDelegate;
import com.example.latte_core.net.RestClient;
import com.example.latte_core.net.callback.IError;
import com.example.latte_core.net.callback.IFailure;
import com.example.latte_core.net.callback.ISuccess;

public class MainFragment extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {
        testRequestCilent();
    }

    private void testRequestCilent() {
        RestClient.builder()
                .url("http://www.12345678g.com/KS/sb/14717.html")
                .sucess(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int coide, String msg) {

                    }
                })
                .build()
                .get();
    }
}
