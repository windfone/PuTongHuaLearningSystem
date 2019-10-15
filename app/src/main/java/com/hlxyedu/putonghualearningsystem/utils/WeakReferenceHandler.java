package com.hlxyedu.putonghualearningsystem.utils;

import android.os.Handler;

import java.lang.ref.WeakReference;

public class WeakReferenceHandler<T> extends Handler {

    private WeakReference<T> weakReference;

    public WeakReferenceHandler(T activity) {
        weakReference = new WeakReference<>(activity);
    }

    public T getRef(){
        return weakReference != null ? weakReference.get():null;
    }

}
