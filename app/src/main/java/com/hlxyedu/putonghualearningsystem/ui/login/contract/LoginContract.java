package com.hlxyedu.putonghualearningsystem.ui.login.contract;

import com.hlxyedu.putonghualearningsystem.model.bean.UserVO;
import com.skyworth.rxqwelibrary.base.BasePresenter;
import com.skyworth.rxqwelibrary.base.BaseView;

/**
 * Created by zhangguihua
 */
public interface LoginContract {

    interface View extends BaseView {
        //返回登陆结果
        void responeError(String errorMsg);

        void loginSuccess(UserVO userVO);

        void closeLogin();
    }

    interface Presenter extends BasePresenter<View> {

        void login(String username,String pwd);
    }
}