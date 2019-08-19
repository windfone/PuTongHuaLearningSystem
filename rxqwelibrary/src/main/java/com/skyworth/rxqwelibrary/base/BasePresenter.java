package com.skyworth.rxqwelibrary.base;

import io.reactivex.disposables.Disposable;

/**
 * Created by skyworth on 2016/8/2.
 * Presenter基类
 */
public interface BasePresenter<T extends BaseView>{

    void attachView(T view);

    void detachView();

    /**
     * Add rxBing subscribe manager
     *
     * @param disposable Disposable
     */
    void addRxBindingSubscribe(Disposable disposable);

}
