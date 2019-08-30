package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.presenter;

import com.hlxyedu.putonghualearningsystem.base.RxBus;
import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.model.event.EssayTxtEvent;
import com.hlxyedu.putonghualearningsystem.model.event.WordFollowEvent;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.contract.DetailWordContract;
import com.hlxyedu.putonghualearningsystem.utils.RxUtil;
import com.hlxyedu.putonghualearningsystem.weight.CommonSubscriber;

import javax.inject.Inject;

import io.reactivex.functions.Predicate;

/**
 * Created by zhangguihua
 */
public class DetailWordPresenter extends RxPresenter<DetailWordContract.View> implements DetailWordContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public DetailWordPresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(DetailWordContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

        //接收传过来的 文章标题 和 内容文字信息
        addSubscribe(RxBus.getDefault().toFlowable(WordFollowEvent.class)
                .compose(RxUtil.<WordFollowEvent>rxSchedulerHelper())
                .filter(new Predicate<WordFollowEvent>() {

                    @Override
                    public boolean test(WordFollowEvent wordFollowEvent) throws Exception {
                        return true;
                    }
                })
                .subscribeWith(new CommonSubscriber<WordFollowEvent>(mView) {
                    @Override
                    public void onNext(WordFollowEvent s) {
                        mView.setDatas(s.getDetailVOS());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                })
        );

    }
}