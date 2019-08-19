package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.presenter;

import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.contract.HanZiLearningContract;

import javax.inject.Inject;

/**
 * Created by zhangguihua
 */
public class HanZiLearningPresenter extends RxPresenter<HanZiLearningContract.View> implements HanZiLearningContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public HanZiLearningPresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(HanZiLearningContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

    }
}