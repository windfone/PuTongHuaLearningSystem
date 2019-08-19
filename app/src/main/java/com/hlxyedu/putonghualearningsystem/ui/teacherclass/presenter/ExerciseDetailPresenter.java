package com.hlxyedu.putonghualearningsystem.ui.teacherclass.presenter;

import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.ui.teacherclass.contract.ExerciseDetailContract;

import javax.inject.Inject;

/**
 * Created by zhangguihua
 */
public class ExerciseDetailPresenter extends RxPresenter<ExerciseDetailContract.View> implements ExerciseDetailContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public ExerciseDetailPresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(ExerciseDetailContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

    }
}