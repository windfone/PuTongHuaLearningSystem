package com.hlxyedu.putonghualearningsystem.ui.home.presenter;

import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.ui.home.contract.TicketInquiryContract;

import javax.inject.Inject;

/**
 * Created by zhangguihua
 */
public class TicketInquiryPresenter extends RxPresenter<TicketInquiryContract.View> implements TicketInquiryContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public TicketInquiryPresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(TicketInquiryContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

    }
}