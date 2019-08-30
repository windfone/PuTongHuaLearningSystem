package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.presenter;

import com.blankj.utilcode.util.ToastUtils;
import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.model.bean.DataVO;
import com.hlxyedu.putonghualearningsystem.model.http.response.HttpResponseCode;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.contract.WordFollowContract;
import com.hlxyedu.putonghualearningsystem.utils.RegUtils;
import com.hlxyedu.putonghualearningsystem.utils.RxUtil;
import com.hlxyedu.putonghualearningsystem.weight.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

import retrofit2.adapter.rxjava2.HttpException;

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

    @Override
    public void getLearningList(int typeId,int pageSize,int currentPage) {
        addSubscribe(
                mDataManager.getOnLineLearningList(typeId,pageSize,currentPage)
                        .compose(RxUtil.rxSchedulerHelper())
                        .compose(RxUtil.handleTestResult())
                        .subscribeWith(
                                new CommonSubscriber<List<DataVO>>(mView) {
                                    @Override
                                    public void onNext(List<DataVO> dataVOS) {
                                        mView.onSuccess(dataVOS);
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