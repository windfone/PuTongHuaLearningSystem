package com.hlxyedu.putonghualearningsystem.ui.main.presenter;

import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.ui.main.contract.OnLineLearningContract;

import javax.inject.Inject;

/**
 * Created by zhangguihua
 */
public class OnLineLearningPresenter extends RxPresenter<OnLineLearningContract.View> implements OnLineLearningContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public OnLineLearningPresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(OnLineLearningContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

    }
}