package com.example.latte_core.net.download;

import android.os.AsyncTask;

import com.example.latte_core.net.callback.IRequest;
import com.example.latte_core.net.callback.ISuccess;

import java.io.File;

public class SaveFileTask extends AsyncTask<Object, Void, File> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;

    public SaveFileTask(IRequest REQUEST, ISuccess SUCCESS) {
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
    }

    @Override
    protected File doInBackground(Object... objects) {
        return null;
    }
}
