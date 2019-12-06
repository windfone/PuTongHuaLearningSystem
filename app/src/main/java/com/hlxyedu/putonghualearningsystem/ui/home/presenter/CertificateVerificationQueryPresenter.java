package com.hlxyedu.putonghualearningsystem.ui.home.presenter;

import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.ui.home.contract.CertificateVerificationQueryContract;

import javax.inject.Inject;

/**
 * Created by zhangguihua
 */
public class CertificateVerificationQueryPresenter extends RxPresenter<CertificateVerificationQueryContract.View> implements CertificateVerificationQueryContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public CertificateVerificationQueryPresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(CertificateVerificationQueryContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

    }
}