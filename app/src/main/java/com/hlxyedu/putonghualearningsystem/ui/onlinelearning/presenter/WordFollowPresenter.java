package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.presenter;

import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.contract.WordFollowContract;

import javax.inject.Inject;

/**
 * Created by zhangguihua
 */
public class WordFollowPresenter extends RxPresenter<WordFollowContract.View> implements WordFollowContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public WordFollowPresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(WordFollowContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

    }
}