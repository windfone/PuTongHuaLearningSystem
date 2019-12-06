package com.hlxyedu.putonghualearningsystem.ui.home.presenter;

import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.ui.home.contract.QueryContract;

import javax.inject.Inject;

/**
 * Created by zhangguihua
 */
public class QueryPresenter extends RxPresenter<QueryContract.View> implements QueryContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public QueryPresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(QueryContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

    }
}