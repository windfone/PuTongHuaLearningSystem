package com.hlxyedu.putonghualearningsystem.ui.publics.presenter;

import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.ui.publics.contract.ViewPagerContract;

import javax.inject.Inject;

/**
 * Created by zhangguihua
 */
public class ViewPagerPresenter extends RxPresenter<ViewPagerContract.View> implements ViewPagerContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public ViewPagerPresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(ViewPagerContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

    }
}