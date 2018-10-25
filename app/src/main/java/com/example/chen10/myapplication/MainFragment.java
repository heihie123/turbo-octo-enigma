package com.example.chen10.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.example.latte_core.detegates.LatteDelegate;
import com.example.latte_core.net.rx.RxRestClient;
import com.example.latte_core.ui.loader.LoaderStyle;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
        Observable<String> observable = RxRestClient.builder()
                .url("http://www.12345678g.com/KS/sb/14717.html")
                .loader(getActivity(), LoaderStyle.BallPulseIndicator)
                .build()
                .get();

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

//        RestClient.builder()
//                .url("http://www.12345678g.com/KS/sb/14717.html")
//                .loader(getActivity())
//                .sucess(new ISuccess() {
//                    @Override
//                    public void onSuccess(String response) {
//                        Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .failure(new IFailure() {
//                    @Override
//                    public void onFailure() {
//
//                    }
//                })
//                .error(new IError() {
//                    @Override
//                    public void onError(int coide, String msg) {
//
//                    }
//                })
//                .build()
//                .get();
    }
}
