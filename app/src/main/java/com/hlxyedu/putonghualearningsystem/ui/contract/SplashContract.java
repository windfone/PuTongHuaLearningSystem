package com.hlxyedu.putonghualearningsystem.ui.contract;

import com.hlxyedu.putonghualearningsystem.model.bean.OnLineLearnTitleVO;
import com.skyworth.rxqwelibrary.base.BasePresenter;
import com.skyworth.rxqwelibrary.base.BaseView;

import java.util.List;

/**
 * Created by zhangguihua
 */
public interface SplashContract {

    interface View extends BaseView {
        //返回登陆结果
        void responeError(String errorMsg);

        void onTopSuccess(List<OnLineLearnTitleVO> onLineLearnTitleVOS);
    }

    interface Presenter extends BasePresenter<View> {

        void getTopTitle();

    }
}