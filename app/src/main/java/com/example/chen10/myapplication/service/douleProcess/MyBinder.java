package com.example.chen10.myapplication.service.douleProcess;

import android.os.RemoteException;

import com.example.chen10.myapplication.IMyAidlInterface;

public class MyBinder extends IMyAidlInterface.Stub {

    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }
}
