package com.example.latte_core.ui.camera;

import android.net.Uri;

public class CameraImageBean {

    private Uri mPath = null;

    private static final CameraImageBean INSTANCE = new CameraImageBean();

    public static CameraImageBean getInstance() {
        return INSTANCE;
    }

    public Uri getPath() {
        return mPath;
    }

    public void setmPath(Uri path) {
        this.mPath = path;
    }
}
