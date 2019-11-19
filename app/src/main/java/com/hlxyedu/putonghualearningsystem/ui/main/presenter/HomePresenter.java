package com.hlxyedu.putonghualearningsystem.ui.main.presenter;

import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.ui.main.contract.HomeContract;

import javax.inject.Inject;

/**
 * Created by zhangguihua
 */
public class HomePresenter extends RxPresenter<HomeContract.View> implements HomeContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public HomePresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(HomeContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

    }
}