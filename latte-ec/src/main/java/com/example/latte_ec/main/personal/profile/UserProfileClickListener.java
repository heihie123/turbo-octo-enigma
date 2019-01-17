package com.example.latte_ec.main.personal.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.example.latte_core.detegates.LatteDelegate;
import com.example.latte_core.net.rx.RxRestClient;
import com.example.latte_core.ui.picker.DataDialogPicker;
import com.example.latte_core.util.callback.CallbackManager;
import com.example.latte_core.util.callback.CallbackType;
import com.example.latte_core.util.callback.IGlobalCallback;
import com.example.latte_core.util.image.GlideUtils;
import com.example.latte_ec.R;
import com.example.latte_ec.main.personal.list.ListBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 个人信息item点击事件
 */
public class UserProfileClickListener extends SimpleClickListener {

    private final UserProfileDelegate DELEGATE;

    private String[] mGenders = new String[]{"男", "女", "保密"};

    public UserProfileClickListener(UserProfileDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, final View view, int position) {
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        final int id = bean.getId();
        switch (id) {
            case 1:
                CallbackManager.getInstance().addCallbacks(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
                    @Override
                    public void executeCallback(Uri args) {
                        final ImageView avatar = view.findViewById(R.id.img_arrow_avatar);
                        GlideUtils.loadNormalImg(DELEGATE.getContext(), args, avatar);

                    }
                });
                break;
            case 2:
                final LatteDelegate nameDelegate = bean.getDelegate();
                DELEGATE.getSupportDelegate().start(nameDelegate);
                break;
            case 3:
                getGenderDialog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final TextView textView = view.findViewById(R.id.txt_arrow_value);
                        textView.setText(mGenders[i]);
                        dialogInterface.cancel();
                    }
                });
                break;
            case 4:
                final DataDialogPicker dataDialogPicker = new DataDialogPicker();
                dataDialogPicker.setIDateListener(new DataDialogPicker.IDateListener() {
                    @Override
                    public void onDateChange(String date) {
                        final TextView textView = view.findViewById(R.id.txt_arrow_value);
                        textView.setText(date);
                    }
                });
                dataDialogPicker.showDialog(DELEGATE.getContext());
                break;
            default:
                break;
        }
    }

    private void requestUpload(Uri args) {
        RxRestClient.builder()
                .url(UploadConfig.UPLOAD_IMG)
                .loader(DELEGATE.getContext())
                .file(args.getPath())
                .build()
                .post()
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        final String path = JSON.parseObject(s)
                                .getJSONObject("result")
                                .getString("path");
                        requestUpdata(path);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void requestUpdata(String path) {
        RxRestClient.builder()
                .url(UploadConfig.UPLOAD_IMG)
                .loader(DELEGATE.getContext())
                .params("avatar", path)
                .build()
                .post()
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void getGenderDialog(DialogInterface.OnClickListener listener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(DELEGATE.getContext());
        builder.setSingleChoiceItems(mGenders, 0, listener);
        builder.show();
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
