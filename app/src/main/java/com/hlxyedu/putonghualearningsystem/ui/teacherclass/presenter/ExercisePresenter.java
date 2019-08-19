package com.hlxyedu.putonghualearningsystem.ui.teacherclass.presenter;

import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.ui.teacherclass.contract.ExerciseContract;

import javax.inject.Inject;

/**
 * Created by zhangguihua
 */
public class ExercisePresenter extends RxPresenter<ExerciseContract.View> implements ExerciseContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public ExercisePresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(ExerciseContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

    }
}