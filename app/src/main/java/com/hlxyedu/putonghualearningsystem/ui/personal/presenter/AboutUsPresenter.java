package com.hlxyedu.putonghualearningsystem.ui.personal.presenter;

import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.ui.personal.contract.AboutUsContract;

import javax.inject.Inject;

/**
 * Created by zhangguihua
 */
public class AboutUsPresenter extends RxPresenter<AboutUsContract.View> implements AboutUsContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public AboutUsPresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(AboutUsContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

    }
}