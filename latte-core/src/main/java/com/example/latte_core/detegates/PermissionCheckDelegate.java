package com.example.latte_core.detegates;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

import com.example.latte_core.ui.camera.CameraImageBean;
import com.example.latte_core.ui.camera.LatteCamera;
import com.example.latte_core.ui.camera.RequestCodes;
import com.example.latte_core.ui.scanner.ScannerDelegate;
import com.example.latte_core.util.ToastUtils;
import com.example.latte_core.util.callback.CallbackManager;
import com.example.latte_core.util.callback.CallbackType;
import com.example.latte_core.util.callback.IGlobalCallback;
import com.yalantis.ucrop.UCrop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * 安卓6.0以后动态权限配置的Fragment基类
 */
@RuntimePermissions
public abstract class PermissionCheckDelegate extends BaseDelegate {

    @NeedsPermission({Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void startCamera() {
        LatteCamera.start(this);
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    void startScan(BaseDelegate delegate) {
        delegate.getSupportDelegate().startForResult(new ScannerDelegate(), RequestCodes.SCAN);
    }

    public void startCameraWithCheck() {
        PermissionCheckDelegatePermissionsDispatcher.startCameraWithPermissionCheck(this);
    }

    public void startScanWithCheck(BaseDelegate delegate) {
        PermissionCheckDelegatePermissionsDispatcher.startScanWithPermissionCheck(this, delegate);
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    void onCameraDenied() {
        ToastUtils.showLongToast("不允许拍照");
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void onCameraNever() {
        ToastUtils.showLongToast("永久拒绝权限");
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    void onCameraRationale(PermissionRequest request) {
        showRationaleDialog(request);
    }

    private void showRationaleDialog(final PermissionRequest request) {
        if (mContext != null) {
            new AlertDialog.Builder(mContext)
                    .setPositiveButton("同意使用", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            request.proceed();
                        }
                    })
                    .setNegativeButton("拒绝使用", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            request.cancel();
                        }
                    })
                    .setCancelable(false)
                    .setMessage("权限管理")
                    .show();
        }
    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionCheckDelegatePermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RequestCodes.TAKE_PHOTO:
                    final Uri resultUri = CameraImageBean.getInstance().getPath();
                    if (mContext != null) {
                        UCrop.of(resultUri, resultUri)
                                .withMaxResultSize(400, 400)
                                .start(mContext, this);
                    }
                    break;
                case RequestCodes.PICK_PHOTO:
                    final Uri pickPath = data.getData();
                    final String pickCropPath = LatteCamera.createCropFile().getPath();
                    if (pickPath != null && mContext != null) {
                        UCrop.of(pickPath, Uri.parse(pickCropPath))
                                .withMaxResultSize(400, 400)
                                .start(mContext, this);
                    }
                    break;
                case RequestCodes.CROR_PHOTO:
                    final Uri cropUri = UCrop.getOutput(data);
                    final IGlobalCallback<Uri> callback = CallbackManager.getInstance().getCallbacks(CallbackType.ON_CROP);
                    if (callback != null) {
                        callback.executeCallback(cropUri);
                    }
                    break;
                case RequestCodes.CROR_ERROR:
                    ToastUtils.showShotToast("裁剪出错");
                    break;
            }
        } else {
            ToastUtils.showShotToast("获取回调失败");
        }
    }
}
