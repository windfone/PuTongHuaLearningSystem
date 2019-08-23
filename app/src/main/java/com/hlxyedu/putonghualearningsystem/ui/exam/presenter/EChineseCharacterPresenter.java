package com.hlxyedu.putonghualearningsystem.ui.exam.presenter;

import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.ui.exam.contract.EChineseCharacterContract;

import javax.inject.Inject;

/**
 * Created by zhangguihua
 */
public class EChineseCharacterPresenter extends RxPresenter<EChineseCharacterContract.View> implements EChineseCharacterContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public EChineseCharacterPresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(EChineseCharacterContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

    }
}