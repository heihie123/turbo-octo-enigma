package com.example.latte_core.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.latte_core.R;
import com.example.latte_core.app.Latte;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * toast工具类
 */
public class ToastUtils {

    private static Toast mToast;

    @SuppressLint("InflateParams")
    private static void initToast(int gravity, int xOffset, int yOffset, int duration, String msg) {
        Context context = Latte.getApplication();
        View toastView = LayoutInflater.from(context).inflate(R.layout.common_toast, null);
        AppCompatTextView msgTxt = toastView.findViewById(R.id.txt_msg);
        msgTxt.setText(msg);
        if (mToast == null) {
            mToast = new Toast(context);
        }
        mToast.setGravity(gravity, xOffset, yOffset);
        mToast.setDuration(duration);
        mToast.setView(toastView);
    }

    public static void showShotToast(String msg) {
        initToast(Gravity.CENTER, 0, 0, Toast.LENGTH_SHORT, msg);
        mToast.show();
    }

    public static void showLongToast(String msg) {
        initToast(Gravity.CENTER, 0, 0, Toast.LENGTH_LONG, msg);
        mToast.show();
    }

    public static void showTopShotToast(String msg) {
        initToast(Gravity.TOP, 0, 0, Toast.LENGTH_SHORT, msg);
        mToast.show();
    }

    public static void showTopLongToast(String msg) {
        initToast(Gravity.TOP, 0, 0, Toast.LENGTH_LONG, msg);
        mToast.show();
    }

    private static void showBottomShotToast(String msg) {
        initToast(Gravity.BOTTOM, 0, 0, Toast.LENGTH_SHORT, msg);
        mToast.show();
    }

    public static void showBottomLongToast(String msg) {
        initToast(Gravity.BOTTOM, 0, 0, Toast.LENGTH_LONG, msg);
        mToast.show();
    }

    public static void cancleToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}
