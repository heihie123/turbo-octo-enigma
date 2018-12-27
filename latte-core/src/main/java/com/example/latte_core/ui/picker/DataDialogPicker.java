package com.example.latte_core.ui.picker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * 时间选择器的dialog
 */
public class DataDialogPicker {

    private IDateListener mIDateListener = null;

    public interface IDateListener {
        void onDateChange(String date);
    }

    public void setIDateListener(IDateListener iDateListener) {
        this.mIDateListener = iDateListener;
    }

    public void showDialog(Context context) {
        final LinearLayout linearLayout = new LinearLayout(context);
        final DatePicker picker = new DatePicker(context);
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        picker.setLayoutParams(layoutParams);

        picker.init(1990, 1, 1, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                final Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
                final String data = dateFormat.format(calendar.getTime());
                if (mIDateListener != null) {
                    mIDateListener.onDateChange(data);
                }
            }
        });

        linearLayout.addView(picker);

        new AlertDialog.Builder(context)
                .setTitle("选择日期")
                .setView(linearLayout)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
    }
}
