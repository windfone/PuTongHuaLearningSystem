package com.hlxyedu.putonghualearningsystem.ui.login.presenter;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hlxyedu.putonghualearningsystem.api.Constants;
import com.hlxyedu.putonghualearningsystem.base.RxBus;
import com.hlxyedu.putonghualearningsystem.base.RxPresenter;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.model.bean.UserVO;
import com.hlxyedu.putonghualearningsystem.model.bean.VideoVO;
import com.hlxyedu.putonghualearningsystem.model.event.LoginEvent;
import com.hlxyedu.putonghualearningsystem.model.http.response.HttpResponse;
import com.hlxyedu.putonghualearningsystem.model.http.response.HttpResponseCode;
import com.hlxyedu.putonghualearningsystem.ui.login.contract.LoginContract;
import com.hlxyedu.putonghualearningsystem.utils.RegUtils;
import com.hlxyedu.putonghualearningsystem.utils.RxUtil;
import com.hlxyedu.putonghualearningsystem.weight.CommonSubscriber;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Predicate;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.adapter.rxjava2.HttpException;

/**
 * Created by zhangguihua
 */
public class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public LoginPresenter(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(LoginContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {
        addSubscribe(RxBus.getDefault().toFlowable(LoginEvent.class)
                .compose(RxUtil.<LoginEvent>rxSchedulerHelper())
                .filter(new Predicate<LoginEvent>() {
                    @Override
                    public boolean test(@NonNull LoginEvent loginEvent) throws Exception {
                        return loginEvent.getType().equals(LoginEvent.LOGIN);
                    }
                })
                .subscribeWith(new CommonSubscriber<LoginEvent>(mView) {
                    @Override
                    public void onNext(LoginEvent s) {
                        mView.closeLogin();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                })
        );
    }

    @Override
    public void login(String username, String pwd) {
        addSubscribe(
                mDataManager.getLoginBody(username, pwd)
                        .compose(RxUtil.rxSchedulerHelper())
                        .compose(RxUtil.handleTestResult())
                        .subscribeWith(
                                new CommonSubscriber<UserVO>(mView) {
                                    @Override
                                    public void onNext(UserVO userVO) {
                                        mDataManager.setLoginStatus(true);
                                        String s = GsonUtils.toJson(userVO);
                                        mDataManager.setUserInfo(s);
                                        mView.loginSuccess(userVO);
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        ToastUtils.showShort(e.getMessage());
                                        //当数据返回为null时 做特殊处理
                                        if (e instanceof HttpException) {
                                            HttpResponseCode httpResponseCode = RegUtils
                                                    .onError((HttpException) e);
//                                            ToastUtils.showShort(httpResponseCode.getMsg());
                                        }
                                        mView.responeError("数据请求失败，请检查网络！");
                                    }
                                }
                        )
        );

    }
}