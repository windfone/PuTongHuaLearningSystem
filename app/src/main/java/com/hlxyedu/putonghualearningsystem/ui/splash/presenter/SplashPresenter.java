package com.hlxyedu.putonghualearningsystem.ui.splash.presenter;

import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.ui.splash.contract.SplashContract;

import javax.inject.Inject;

/**
 * Created by zhangguihua
 */
public class SplashPresenter extends RxPresenter<SplashContract.View> implements SplashContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public SplashPresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(SplashContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

    }

}