package com.hlxyedu.putonghualearningsystem.ui.main.presenter;

import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.ui.main.contract.PersonalCenterContract;

import javax.inject.Inject;

/**
 * Created by zhangguihua
 */
public class PersonalCenterPresenter extends RxPresenter<PersonalCenterContract.View> implements PersonalCenterContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public PersonalCenterPresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(PersonalCenterContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

    }
}