package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.presenter;

import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.contract.ChildSoundContract;

import javax.inject.Inject;

/**
 * Created by zhangguihua
 */
public class ChildSoundPresenter extends RxPresenter<ChildSoundContract.View> implements ChildSoundContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public ChildSoundPresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(ChildSoundContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

    }
}