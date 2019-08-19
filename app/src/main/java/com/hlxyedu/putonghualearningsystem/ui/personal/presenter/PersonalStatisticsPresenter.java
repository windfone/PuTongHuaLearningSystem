package com.hlxyedu.putonghualearningsystem.ui.personal.presenter;

import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.ui.personal.contract.PersonalStatisticsContract;

import javax.inject.Inject;

/**
 * Created by zhangguihua
 */
public class PersonalStatisticsPresenter extends RxPresenter<PersonalStatisticsContract.View> implements PersonalStatisticsContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public PersonalStatisticsPresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(PersonalStatisticsContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

    }
}