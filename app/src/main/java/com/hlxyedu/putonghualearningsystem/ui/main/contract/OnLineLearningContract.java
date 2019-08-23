package com.hlxyedu.putonghualearningsystem.ui.main.contract;

import com.hlxyedu.putonghualearningsystem.model.bean.OnLineLearnTitleVO;
import com.skyworth.rxqwelibrary.base.BasePresenter;
import com.skyworth.rxqwelibrary.base.BaseView;

/**
 * Created by zhangguihua
 */
public interface OnLineLearningContract {

    interface View extends BaseView {
        //返回登陆结果
        void responeError(String errorMsg);


    }

    interface Presenter extends BasePresenter<View> {


    }
}