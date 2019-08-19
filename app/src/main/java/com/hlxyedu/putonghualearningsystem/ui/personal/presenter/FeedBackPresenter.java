package com.hlxyedu.putonghualearningsystem.ui.personal.presenter;

import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.ui.personal.contract.FeedBackContract;

import javax.inject.Inject;

/**
 * Created by zhangguihua
 */
public class FeedBackPresenter extends RxPresenter<FeedBackContract.View> implements FeedBackContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public FeedBackPresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(FeedBackContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

    }
}