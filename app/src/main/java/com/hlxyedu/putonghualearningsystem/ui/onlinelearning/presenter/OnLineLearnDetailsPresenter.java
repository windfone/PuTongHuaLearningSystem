package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.presenter;

import com.blankj.utilcode.util.ToastUtils;
import com.hlxyedu.putonghualearningsystem.base.RxBus;
import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.model.bean.EssayDetailVO;
import com.hlxyedu.putonghualearningsystem.model.event.LoginEvent;
import com.hlxyedu.putonghualearningsystem.model.event.RecordEvent;
import com.hlxyedu.putonghualearningsystem.model.http.response.HttpResponseCode;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.contract.OnLineLearnDetailsContract;
import com.hlxyedu.putonghualearningsystem.utils.RegUtils;
import com.hlxyedu.putonghualearningsystem.utils.RxUtil;
import com.hlxyedu.putonghualearningsystem.weight.CommonSubscriber;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Predicate;
import retrofit2.adapter.rxjava2.HttpException;

/**
 * Created by zhangguihua
 */
public class OnLineLearnDetailsPresenter extends RxPresenter<OnLineLearnDetailsContract.View> implements OnLineLearnDetailsContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public OnLineLearnDetailsPresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(OnLineLearnDetailsContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {
        // 录音
        addSubscribe(RxBus.getDefault().toFlowable(RecordEvent.class)
                .compose(RxUtil.<RecordEvent>rxSchedulerHelper())
                .filter(new Predicate<RecordEvent>() {
                    @Override
                    public boolean test(@NonNull RecordEvent recordEvent) throws Exception {
                        return recordEvent.getType().equals(RecordEvent.START_RECORD);
                    }
                })
                .subscribeWith(new CommonSubscriber<RecordEvent>(mView) {
                    @Override
                    public void onNext(RecordEvent s) {
                        mView.recordSecond();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                })
        );
    }

    @Override
    public void getEssayDetails(String audioName) {
        addSubscribe(
                mDataManager.getEssayDetails(audioName)
                        .compose(RxUtil.rxSchedulerHelper())
                        .compose(RxUtil.handleTestResult())
                        .subscribeWith(
                                new CommonSubscriber<EssayDetailVO>(mView) {
                                    @Override
                                    public void onNext(EssayDetailVO detailVO) {
                                        mView.onDetailsSuccess(detailVO);
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