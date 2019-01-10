package com.example.latte_core.ui.camera;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;

import com.example.latte_core.R;
import com.example.latte_core.detegates.PermissionCheckDelegate;

import androidx.appcompat.app.AlertDialog;

public class CameraHandler implements View.OnClickListener {

    private final AlertDialog DIALOG;
    private final PermissionCheckDelegate DELEGATE;

    public CameraHandler(PermissionCheckDelegate delegate) {
        this.DELEGATE = delegate;
        this.DIALOG = new AlertDialog.Builder(delegate.getContext()).create();
    }

    final public void beginCameraDialog() {
        DIALOG.show();
        final Window window = DIALOG.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_camera_panel);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_cancel) {
            DIALOG.cancel();
        } else if (id == R.id.btn_photo) {
            takePhoto();
            DIALOG.cancel();
        } else if (id == R.id.btn_native) {
            pickPhoto();
            DIALOG.cancel();
        }
    }

    private void takePhoto() {

    }

    private void pickPhoto() {

    }
}
