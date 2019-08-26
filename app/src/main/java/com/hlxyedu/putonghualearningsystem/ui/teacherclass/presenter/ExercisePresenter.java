package com.hlxyedu.putonghualearningsystem.ui.teacherclass.presenter;

import com.blankj.utilcode.util.ToastUtils;
import com.hlxyedu.putonghualearningsystem.base.RxBus;
import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.model.bean.VideoVO;
import com.hlxyedu.putonghualearningsystem.model.event.ActionEvent;
import com.hlxyedu.putonghualearningsystem.model.http.response.HttpResponseCode;
import com.hlxyedu.putonghualearningsystem.ui.teacherclass.contract.ExerciseContract;
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
public class ExercisePresenter extends RxPresenter<ExerciseContract.View> implements ExerciseContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public ExercisePresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(ExerciseContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {
        //排序
        addSubscribe(RxBus.getDefault().toFlowable(ActionEvent.class)
                .compose(RxUtil.<ActionEvent>rxSchedulerHelper())
                .filter(new Predicate<ActionEvent>() {
                    @Override
                    public boolean test(@NonNull ActionEvent actionEvent) throws Exception {
                        return actionEvent.getType().equals(ActionEvent.SORT);
                    }
                })
                .subscribeWith(new CommonSubscriber<ActionEvent>(mView) {
                    @Override
                    public void onNext(ActionEvent s) {
                        mView.orderBy(s.getSortType());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                })
        );

    }

    @Override
    public void getVideoList(int typeId, int orderBy, int currentPage, int pageSize) {
        addSubscribe(
                mDataManager.getTeacherClassList(typeId, orderBy, currentPage, pageSize)
                        .compose(RxUtil.rxSchedulerHelper())
                        .compose(RxUtil.handleTestResult())
                        .subscribeWith(
                                new CommonSubscriber<List<VideoVO>>(mView) {
                                    @Override
                                    public void onNext(List<VideoVO> dataVOS) {
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