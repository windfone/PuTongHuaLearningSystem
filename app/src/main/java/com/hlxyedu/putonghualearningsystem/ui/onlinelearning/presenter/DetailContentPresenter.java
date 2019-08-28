package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.presenter;

import com.hlxyedu.putonghualearningsystem.base.RxBus;
import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.model.event.EssayTxtEvent;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.contract.DetailContentContract;
import com.hlxyedu.putonghualearningsystem.utils.RxUtil;
import com.hlxyedu.putonghualearningsystem.weight.CommonSubscriber;

import javax.inject.Inject;

import io.reactivex.functions.Predicate;

/**
 * Created by zhangguihua
 */
public class DetailContentPresenter extends RxPresenter<DetailContentContract.View> implements DetailContentContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public DetailContentPresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(DetailContentContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

        //接收传过来的 文章标题 和 内容文字信息
        addSubscribe(RxBus.getDefault().toFlowable(EssayTxtEvent.class)
                .compose(RxUtil.<EssayTxtEvent>rxSchedulerHelper())
                .filter(new Predicate<EssayTxtEvent>() {

                    @Override
                    public boolean test(EssayTxtEvent essayTxtEvent) throws Exception {
                        return true;
                    }
                })
                .subscribeWith(new CommonSubscriber<EssayTxtEvent>(mView) {
                    @Override
                    public void onNext(EssayTxtEvent s) {
                        mView.setEassyTxt(s.getShortEssayTxt(),s.getItemName());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                })
        );

    }
}