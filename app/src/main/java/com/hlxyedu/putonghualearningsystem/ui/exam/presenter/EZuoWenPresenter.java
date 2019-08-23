package com.hlxyedu.putonghualearningsystem.ui.exam.presenter;

import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.ui.exam.contract.EZuoWenContract;

import javax.inject.Inject;

/**
 * Created by zhangguihua
 */
public class EZuoWenPresenter extends RxPresenter<EZuoWenContract.View> implements EZuoWenContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public EZuoWenPresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(EZuoWenContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

    }
}