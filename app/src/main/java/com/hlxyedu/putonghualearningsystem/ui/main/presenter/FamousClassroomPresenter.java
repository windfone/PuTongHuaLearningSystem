package com.hlxyedu.putonghualearningsystem.ui.main.presenter;

import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.ui.main.contract.FamousClassroomContract;

import javax.inject.Inject;

/**
 * Created by zhangguihua
 */
public class FamousClassroomPresenter extends RxPresenter<FamousClassroomContract.View> implements FamousClassroomContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public FamousClassroomPresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(FamousClassroomContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

    }
}