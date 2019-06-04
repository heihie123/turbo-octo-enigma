package com.example.chen10.myapplication.keep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.chen10.myapplication.keep.KeepManager;

/**
 * 1像素activity
 */
public class KeepActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("keep", "启动Keep");
        Window window = getWindow();
        window.setGravity(Gravity.START | Gravity.TOP);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = 1;
        layoutParams.height = 1;
        layoutParams.x = 0;
        layoutParams.y = 0;
        window.setAttributes(layoutParams);
        KeepManager.getInstance().setKeepActivity(this);
    }
}
