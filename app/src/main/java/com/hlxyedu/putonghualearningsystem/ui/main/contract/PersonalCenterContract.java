package com.hlxyedu.putonghualearningsystem.ui.main.contract;

import com.hlxyedu.putonghualearningsystem.model.bean.UserVO;
import com.skyworth.rxqwelibrary.base.BasePresenter;
import com.skyworth.rxqwelibrary.base.BaseView;

/**
 * Created by zhangguihua
 */
public interface PersonalCenterContract {

    interface View extends BaseView {
        //返回登陆结果
        void responeError(String errorMsg);

        void onSuccess(UserVO userVO);
    }

    interface Presenter extends BasePresenter<View> {

        void getUserInfo(int userId);

    }
}