package com.hlxyedu.putonghualearningsystem.ui.personal.presenter;

import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.ui.personal.contract.PersonalInfoContract;

import javax.inject.Inject;

/**
 * Created by zhanggihua
 */
public class PersonalInfoPresenter extends RxPresenter<PersonalInfoContract.View> implements PersonalInfoContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public PersonalInfoPresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(PersonalInfoContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

    }
}