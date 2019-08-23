package com.hlxyedu.putonghualearningsystem.ui.exam.presenter;

import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.ui.exam.contract.EShortEssayContract;

import javax.inject.Inject;

/**
 * Created by zhangguihua
 */
public class EShortEssayPresenter extends RxPresenter<EShortEssayContract.View> implements EShortEssayContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public EShortEssayPresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(EShortEssayContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

    }
}