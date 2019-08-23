package com.hlxyedu.putonghualearningsystem.ui.main.presenter;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.model.bean.OnLineLearnTitleVO;
import com.hlxyedu.putonghualearningsystem.model.bean.UserVO;
import com.hlxyedu.putonghualearningsystem.model.http.response.HttpResponseCode;
import com.hlxyedu.putonghualearningsystem.ui.main.contract.OnLineLearningContract;
import com.hlxyedu.putonghualearningsystem.utils.RegUtils;
import com.hlxyedu.putonghualearningsystem.utils.RxUtil;
import com.hlxyedu.putonghualearningsystem.weight.CommonSubscriber;

import javax.inject.Inject;

import retrofit2.adapter.rxjava2.HttpException;

/**
 * Created by zhangguihua
 */
public class OnLineLearningPresenter extends RxPresenter<OnLineLearningContract.View> implements OnLineLearningContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public OnLineLearningPresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(OnLineLearningContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

    }

}