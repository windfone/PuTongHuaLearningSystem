package com.hlxyedu.putonghualearningsystem.ui.main.presenter;

import com.blankj.utilcode.util.ToastUtils;
import com.hlxyedu.putonghualearningsystem.base.RxBus;
import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.model.bean.TopTitleVO;
import com.hlxyedu.putonghualearningsystem.model.event.LoginEvent;
import com.hlxyedu.putonghualearningsystem.model.http.response.HttpResponseCode;
import com.hlxyedu.putonghualearningsystem.ui.main.contract.OnLineLearningContract;
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
        // 检查是否登录
        addSubscribe(RxBus.getDefault().toFlowable(LoginEvent.class)
                .compose(RxUtil.<LoginEvent>rxSchedulerHelper())
                .filter(new Predicate<LoginEvent>() {
                    @Override
                    public boolean test(@NonNull LoginEvent actionEvent) throws Exception {
                        return actionEvent.getType().equals(LoginEvent.LOGIN);
                    }
                })
                .subscribeWith(new CommonSubscriber<LoginEvent>(mView) {
                    @Override
                    public void onNext(LoginEvent s) {
                        mView.isLogin(s.getPos(),s.getLists(),s.getTitle(),s.getConTitle());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                })
        );
    }

    @Override
    public void getTopTitle() {
        addSubscribe(
                mDataManager.getOnLineLearningTitle()
                        .compose(RxUtil.rxSchedulerHelper())
                        .compose(RxUtil.handleTestResult())
                        .subscribeWith(
                                new CommonSubscriber<List<TopTitleVO>>(mView) {
                                    @Override
                                    public void onNext(List<TopTitleVO> topTitleVOS) {
                                        mView.onTopSuccess(topTitleVOS);
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

    @Override
    public boolean loginStatus() {
        return mDataManager.getLoginStatus();
    }

}