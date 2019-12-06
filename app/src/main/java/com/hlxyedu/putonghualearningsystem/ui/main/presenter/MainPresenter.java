package com.hlxyedu.putonghualearningsystem.ui.main.presenter;

import com.hlxyedu.putonghualearningsystem.base.RxBus;
import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.model.event.LoginEvent;
import com.hlxyedu.putonghualearningsystem.model.event.TabSelectEvent;
import com.hlxyedu.putonghualearningsystem.ui.main.contract.MainContract;
import com.hlxyedu.putonghualearningsystem.utils.RxUtil;
import com.hlxyedu.putonghualearningsystem.weight.CommonSubscriber;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Predicate;

/**
 * Created by zhangguihua
 */
public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public MainPresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(MainContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {
        // 切换tab
        addSubscribe(RxBus.getDefault().toFlowable(TabSelectEvent.class)
                .compose(RxUtil.<TabSelectEvent>rxSchedulerHelper())
                .filter(new Predicate<TabSelectEvent>() {
                    @Override
                    public boolean test(@NonNull TabSelectEvent tabSelectEvent) throws Exception {
                        return tabSelectEvent.getType().equals(TabSelectEvent.CHANGE);
                    }
                })
                .subscribeWith(new CommonSubscriber<TabSelectEvent>(mView) {
                    @Override
                    public void onNext(TabSelectEvent s) {
                        mView.changeSelTab(s.getPos());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                })
        );

    }

}