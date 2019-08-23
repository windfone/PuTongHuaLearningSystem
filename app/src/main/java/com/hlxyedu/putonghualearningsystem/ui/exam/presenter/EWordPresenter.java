package com.hlxyedu.putonghualearningsystem.ui.exam.presenter;

import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.ui.exam.contract.EWordContract;

import javax.inject.Inject;

/**
 * Created by zhangguihua
 */
public class EWordPresenter extends RxPresenter<EWordContract.View> implements EWordContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public EWordPresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(EWordContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

    }
}