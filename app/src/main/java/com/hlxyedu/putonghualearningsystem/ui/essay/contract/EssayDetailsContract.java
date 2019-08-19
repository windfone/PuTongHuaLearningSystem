package com.hlxyedu.putonghualearningsystem.ui.essay.contract;

import com.hlxyedu.putonghualearningsystem.model.bean.EssayDetailVO;
import com.skyworth.rxqwelibrary.base.BasePresenter;
import com.skyworth.rxqwelibrary.base.BaseView;

/**
 * Created by zhangguihua
 */
public interface EssayDetailsContract {

    interface View extends BaseView {
        //返回登陆结果
        void responeError(String errorMsg);

        void onDetailsSuccess(EssayDetailVO essayDetailVO);

    }

    interface Presenter extends BasePresenter<View> {

        void getEssayDetails(String audioName);

    }
}