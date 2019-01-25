package com.example.latte_core.ui.camera;

import android.net.Uri;

/**
 * 图片Uri的bean
 */
public final class CameraImageBean {

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
