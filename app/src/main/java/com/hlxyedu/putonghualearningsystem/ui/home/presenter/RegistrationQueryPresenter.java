package com.hlxyedu.putonghualearningsystem.ui.home.presenter;

import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.ui.home.contract.RegistrationQueryContract;

import javax.inject.Inject;

/**
 * Created by zhangguihua
 */
public class RegistrationQueryPresenter extends RxPresenter<RegistrationQueryContract.View> implements RegistrationQueryContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public RegistrationQueryPresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(RegistrationQueryContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

    }
}