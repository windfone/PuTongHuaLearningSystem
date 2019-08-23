package com.hlxyedu.putonghualearningsystem.ui.presenter;

import com.blankj.utilcode.util.ToastUtils;
import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.model.bean.OnLineLearnTitleVO;
import com.hlxyedu.putonghualearningsystem.model.http.response.HttpResponseCode;
import com.hlxyedu.putonghualearningsystem.ui.contract.SplashContract;
import com.hlxyedu.putonghualearningsystem.utils.RegUtils;
import com.hlxyedu.putonghualearningsystem.utils.RxUtil;
import com.hlxyedu.putonghualearningsystem.weight.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

import retrofit2.adapter.rxjava2.HttpException;

/**
 * Created by zhangguihua
 */
public class SplashPresenter extends RxPresenter<SplashContract.View> implements SplashContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public SplashPresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(SplashContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

    }

    @Override
    public void getTopTitle() {
        addSubscribe(
                mDataManager.getOnLineLearningTitle()
                        .compose(RxUtil.rxSchedulerHelper())
                        .compose(RxUtil.handleTestResult())
                        .subscribeWith(
                                new CommonSubscriber<List<OnLineLearnTitleVO>>(mView) {
                                    @Override
                                    public void onNext(List<OnLineLearnTitleVO> titleVOS) {
                                        mView.onTopSuccess(titleVOS);
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        //当数据返回为null时 做特殊处理
                                        if (e instanceof HttpException) {
                                            HttpResponseCode httpResponseCode = RegUtils
                                                    .onError((HttpException) e);
                                            ToastUtils.showShort(httpResponseCode.getMsg());
                                        }
                                        mView.responeError("数据请求失败，请检查网络！");
                                    }
                                }
                        )
        );
    }
}