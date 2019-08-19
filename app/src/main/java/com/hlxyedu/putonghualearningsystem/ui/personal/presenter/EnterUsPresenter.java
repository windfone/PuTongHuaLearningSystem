package com.hlxyedu.putonghualearningsystem.ui.personal.presenter;

import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.ui.personal.contract.EnterUsContract;

import javax.inject.Inject;

/**
 * Created by zhangguihua
 */
public class EnterUsPresenter extends RxPresenter<EnterUsContract.View> implements EnterUsContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public EnterUsPresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(EnterUsContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

    }
}