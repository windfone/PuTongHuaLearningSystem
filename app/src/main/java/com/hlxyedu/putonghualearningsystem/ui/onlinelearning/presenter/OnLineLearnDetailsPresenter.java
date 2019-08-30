package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.presenter;

import com.blankj.utilcode.util.ToastUtils;
import com.hlxyedu.putonghualearningsystem.base.RxBus;
import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.model.bean.DetailVO;
import com.hlxyedu.putonghualearningsystem.model.event.ActionEvent;
import com.hlxyedu.putonghualearningsystem.model.event.RecordEvent;
import com.hlxyedu.putonghualearningsystem.model.http.response.HttpResponseCode;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.contract.OnLineLearnDetailsContract;
import com.hlxyedu.putonghualearningsystem.utils.RegUtils;
import com.hlxyedu.putonghualearningsystem.utils.RxUtil;
import com.hlxyedu.putonghualearningsystem.weight.CommonSubscriber;

import java.util.List;

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

        //播放音频
        addSubscribe(RxBus.getDefault().toFlowable(ActionEvent.class)
                .compose(RxUtil.<ActionEvent>rxSchedulerHelper())
                .filter(new Predicate<ActionEvent>() {
                    @Override
                    public boolean test(@NonNull ActionEvent actionEvent) throws Exception {
                        return actionEvent.getType().equals(ActionEvent.PLAYAUDIO);
                    }
                })
                .subscribeWith(new CommonSubscriber<ActionEvent>(mView) {
                    @Override
                    public void onNext(ActionEvent s) {
                        mView.playAudio();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                })
        );

        // 单词跟读 播放音频，由于单词跟读详情是列表形式，需要将数据传回
        addSubscribe(RxBus.getDefault().toFlowable(ActionEvent.class)
                .compose(RxUtil.<ActionEvent>rxSchedulerHelper())
                .filter(new Predicate<ActionEvent>() {
                    @Override
                    public boolean test(@NonNull ActionEvent actionEvent) throws Exception {
                        return actionEvent.getType().equals(ActionEvent.PLAYWORDAUDIO);
                    }
                })
                .subscribeWith(new CommonSubscriber<ActionEvent>(mView) {
                    @Override
                    public void onNext(ActionEvent s) {
                        mView.playWordAudio(s.getDetailVO());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                })
        );

    }

    /**
     * 请求 短文跟读 详情
     * @param conId
     * @param pinYinOrder
     */
    @Override
    public void getShortEssayDetails(int conId,String pinYinOrder) {
        addSubscribe(
                mDataManager.getEssayDetails(conId,pinYinOrder)
                        .compose(RxUtil.rxSchedulerHelper())
                        .compose(RxUtil.handleTestResult())
                        .subscribeWith(
                                new CommonSubscriber<DetailVO>(mView) {
                                    @Override
                                    public void onNext(DetailVO detailVO) {
                                        mView.onShortEssayDetailsSuccess(detailVO);
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

    /**
     *  拼音学习 详情
     * @param conId
     * @param pinYinOrder
     */
    @Override
    public void getPinYinDetails(int conId, String pinYinOrder) {
        addSubscribe(
                mDataManager.getPinYinLearningDetails(conId,pinYinOrder)
                        .compose(RxUtil.rxSchedulerHelper())
                        .compose(RxUtil.handleTestResult())
                        .subscribeWith(
                                new CommonSubscriber<DetailVO>(mView) {
                                    @Override
                                    public void onNext(DetailVO detailVO) {
                                        mView.onPinYinDetailsSuccess(detailVO);
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

    /**
     * 请求汉字 轻声字 儿化音 详情
     * @param conId
     * @param pinYin
     */
    @Override
    public void getHanZiDetails(int conId,String pinYin,String pinYinOrder) {
        addSubscribe(
                mDataManager.getHanZiDetails(conId,pinYin,pinYinOrder)
                        .compose(RxUtil.rxSchedulerHelper())
                        .compose(RxUtil.handleTestResult())
                        .subscribeWith(
                                new CommonSubscriber<DetailVO>(mView) {
                                    @Override
                                    public void onNext(DetailVO detailVO) {
                                        mView.onHanZiDetailsSuccess(detailVO);
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

    /**
     * 单词跟读 详情
     * @param pinYinOrder
     */
    @Override
    public void getWordFollowDetails(String pinYinOrder) {
        addSubscribe(
                mDataManager.getWordFollowDetails(pinYinOrder)
                        .compose(RxUtil.rxSchedulerHelper())
                        .compose(RxUtil.handleTestResult())
                        .subscribeWith(
                                new CommonSubscriber<List<DetailVO>>(mView) {
                                    @Override
                                    public void onNext(List<DetailVO> detailVOS) {
                                        mView.onWordFollowDetailsSuccess(detailVOS);
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