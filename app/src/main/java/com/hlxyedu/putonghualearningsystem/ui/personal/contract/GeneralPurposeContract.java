package com.hlxyedu.putonghualearningsystem.ui.personal.contract;

import com.skyworth.rxqwelibrary.base.BasePresenter;
import com.skyworth.rxqwelibrary.base.BaseView;

/**
 * Created by zhangguihua
 */
public interface GeneralPurposeContract {

    interface View extends BaseView {
        //返回登陆结果
        void responeError(String errorMsg);
    }

    interface Presenter extends BasePresenter<View> {

        boolean isLogin();

        void clearLoginInfo();
    }
}