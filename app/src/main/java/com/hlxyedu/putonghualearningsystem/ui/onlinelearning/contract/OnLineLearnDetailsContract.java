package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.contract;

import com.hlxyedu.putonghualearningsystem.model.bean.EssayDetailVO;
import com.skyworth.rxqwelibrary.base.BasePresenter;
import com.skyworth.rxqwelibrary.base.BaseView;

/**
 * Created by zhangguihua
 */
public interface OnLineLearnDetailsContract {

    interface View extends BaseView {
        //返回登陆结果
        void responeError(String errorMsg);

        void onDetailsSuccess(EssayDetailVO essayDetailVO);

        void recordSecond();
    }

    interface Presenter extends BasePresenter<OnLineLearnDetailsContract.View> {

        void getEssayDetails(String audioName);

    }
}