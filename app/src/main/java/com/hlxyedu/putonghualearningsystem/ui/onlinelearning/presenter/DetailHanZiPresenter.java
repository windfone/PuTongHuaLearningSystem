package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.presenter;

import com.hlxyedu.putonghualearningsystem.base.RxBus;
import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.model.event.ActionEvent;
import com.hlxyedu.putonghualearningsystem.model.event.EssayTxtEvent;
import com.hlxyedu.putonghualearningsystem.model.event.HanZiEvent;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.contract.DetailHanZiContract;
import com.hlxyedu.putonghualearningsystem.utils.RxUtil;
import com.hlxyedu.putonghualearningsystem.weight.CommonSubscriber;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Predicate;

/**
 * Created by zhangguihau
 */
public class DetailHanZiPresenter extends RxPresenter<DetailHanZiContract.View> implements DetailHanZiContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public DetailHanZiPresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(DetailHanZiContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

        //接收传过来的 拼音 和 汉字
        addSubscribe(RxBus.getDefault().toFlowable(HanZiEvent.class)
                .compose(RxUtil.<HanZiEvent>rxSchedulerHelper())
                .filter(new Predicate<HanZiEvent>() {

                    @Override
                    public boolean test(HanZiEvent hanZiEvent) throws Exception {
                        return true;
                    }
                })
                .subscribeWith(new CommonSubscriber<HanZiEvent>(mView) {
                    @Override
                    public void onNext(HanZiEvent s) {
                        mView.setContentText(s.getPinYin(),s.getPinYinCN(),s.getVideoUrl());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                })
        );

        // 切换上一个下一个 初始化view
        addSubscribe(RxBus.getDefault().toFlowable(ActionEvent.class)
                .compose(RxUtil.<ActionEvent>rxSchedulerHelper())
                .filter(new Predicate<ActionEvent>() {
                    @Override
                    public boolean test(@NonNull ActionEvent actionEvent) throws Exception {
                        return actionEvent.getType().equals(ActionEvent.INITVIEW);
                    }
                })
                .subscribeWith(new CommonSubscriber<ActionEvent>(mView) {
                    @Override
                    public void onNext(ActionEvent s) {
                        mView.switchInitView();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                })
        );

    }
}