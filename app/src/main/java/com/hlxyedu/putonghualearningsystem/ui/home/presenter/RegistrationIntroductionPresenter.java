package com.hlxyedu.putonghualearningsystem.ui.home.presenter;

import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.ui.home.contract.RegistrationIntroductionContract;

import javax.inject.Inject;

/**
 * Created by zhangguihua
 */
public class RegistrationIntroductionPresenter extends RxPresenter<RegistrationIntroductionContract.View> implements RegistrationIntroductionContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public RegistrationIntroductionPresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(RegistrationIntroductionContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

    }
}