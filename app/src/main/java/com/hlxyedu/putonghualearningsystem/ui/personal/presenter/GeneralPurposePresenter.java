package com.hlxyedu.putonghualearningsystem.ui.personal.presenter;

import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.ui.personal.contract.GeneralPurposeContract;

import javax.inject.Inject;

/**
 * Created by zhangguihua
 */
public class GeneralPurposePresenter extends RxPresenter<GeneralPurposeContract.View> implements GeneralPurposeContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public GeneralPurposePresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(GeneralPurposeContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

    }
}