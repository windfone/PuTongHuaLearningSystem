package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.presenter;

import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.contract.PinYinLearningContract;

import javax.inject.Inject;

/**
 * Created by zhangguihua
 */
public class PinYinLearningPresenter extends RxPresenter<PinYinLearningContract.View> implements PinYinLearningContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public PinYinLearningPresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(PinYinLearningContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

    }
}