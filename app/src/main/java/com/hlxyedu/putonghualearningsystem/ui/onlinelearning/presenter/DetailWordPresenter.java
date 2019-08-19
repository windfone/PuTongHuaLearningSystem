package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.presenter;

import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.contract.DetailWordContract;

import javax.inject.Inject;

/**
 * Created by zhangguihua
 */
public class DetailWordPresenter extends RxPresenter<DetailWordContract.View> implements DetailWordContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public DetailWordPresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(DetailWordContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

    }
}