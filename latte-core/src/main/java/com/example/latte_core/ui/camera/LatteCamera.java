package com.example.latte_core.ui.camera;

import android.net.Uri;

import com.example.latte_core.detegates.PermissionCheckDelegate;
import com.example.latte_core.util.file.FileUtil;

/**
 * 图片选择调用类
 */
public class LatteCamera {
    public static Uri createCropFile() {
        return Uri.parse(FileUtil.createFile("crop_image",
                FileUtil.getFileNameByTime("IMG", "jpg")).getPath());
    }

    public static void start(PermissionCheckDelegate delegate) {
        new CameraHandler(delegate).beginCameraDialog();
    }
}
