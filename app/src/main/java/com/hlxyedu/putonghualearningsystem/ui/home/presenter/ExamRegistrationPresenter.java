package com.hlxyedu.putonghualearningsystem.ui.home.presenter;

import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.ui.home.contract.ExamRegistrationContract;

import javax.inject.Inject;

/**
 * Created by zhangguihua
 */
public class ExamRegistrationPresenter extends RxPresenter<ExamRegistrationContract.View> implements ExamRegistrationContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public ExamRegistrationPresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(ExamRegistrationContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

    }
}