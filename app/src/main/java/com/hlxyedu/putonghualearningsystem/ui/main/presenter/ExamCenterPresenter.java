package com.hlxyedu.putonghualearningsystem.ui.main.presenter;

import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.ui.main.contract.ExamCenterContract;

import javax.inject.Inject;

/**
 * Created by zhangguihua
 */
public class ExamCenterPresenter extends RxPresenter<ExamCenterContract.View> implements ExamCenterContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public ExamCenterPresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(ExamCenterContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

    }
}