package com.hlxyedu.putonghualearningsystem.ui.exam.presenter;

import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.ui.exam.contract.EScoreContract;

import javax.inject.Inject;

/**
 * Created by zhangguihua
 */
public class EScorePresenter extends RxPresenter<EScoreContract.View> implements EScoreContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public EScorePresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(EScoreContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

    }
}