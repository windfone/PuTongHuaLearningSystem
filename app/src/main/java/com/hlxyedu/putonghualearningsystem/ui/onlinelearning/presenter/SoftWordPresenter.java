package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.presenter;

import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.contract.SoftWordContract;

import javax.inject.Inject;

/**
 * Created by zhangguihua
 */
public class SoftWordPresenter extends RxPresenter<SoftWordContract.View> implements SoftWordContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public SoftWordPresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(SoftWordContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

    }
}