package com.hlxyedu.putonghualearningsystem.ui.main.presenter;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.JsonUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.model.bean.DataVO;
import com.hlxyedu.putonghualearningsystem.model.bean.UserVO;
import com.hlxyedu.putonghualearningsystem.model.http.response.HttpResponseCode;
import com.hlxyedu.putonghualearningsystem.ui.main.contract.PersonalCenterContract;
import com.hlxyedu.putonghualearningsystem.utils.RegUtils;
import com.hlxyedu.putonghualearningsystem.utils.RxUtil;
import com.hlxyedu.putonghualearningsystem.weight.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

import retrofit2.adapter.rxjava2.HttpException;

/**
 * Created by zhangguihua
 */
public class PersonalCenterPresenter extends RxPresenter<PersonalCenterContract.View> implements PersonalCenterContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public PersonalCenterPresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(PersonalCenterContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

    }

    @Override
    public void getUserInfo(int userId) {
        addSubscribe(
                mDataManager.getUserInfo(userId)
                        .compose(RxUtil.rxSchedulerHelper())
                        .compose(RxUtil.handleTestResult())
                        .subscribeWith(
                                new CommonSubscriber<UserVO>(mView) {
                                    @Override
                                    public void onNext(UserVO userVO) {
                                        mView.onSuccess(userVO);

                                        // 保存登录信息到sp 中
                                        String userInfo = GsonUtils.toJson(userVO);
                                        mDataManager.setUserInfo(userInfo);
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