package com.hlxyedu.putonghualearningsystem.ui.home.presenter;

import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.ui.home.contract.GradeQueryContract;

import javax.inject.Inject;

/**
 * Created by zhangguihua
 */
public class GradeQueryPresenter extends RxPresenter<GradeQueryContract.View> implements GradeQueryContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public GradeQueryPresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(GradeQueryContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

    }
}