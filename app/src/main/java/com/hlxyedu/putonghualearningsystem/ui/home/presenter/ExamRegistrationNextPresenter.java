package com.hlxyedu.putonghualearningsystem.ui.home.presenter;

import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.ui.home.contract.ExamRegistrationNextContract;

import javax.inject.Inject;

/**
 * Created by zhangguihua
 */
public class ExamRegistrationNextPresenter extends RxPresenter<ExamRegistrationNextContract.View> implements ExamRegistrationNextContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public ExamRegistrationNextPresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(ExamRegistrationNextContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

    }
}