package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.contract;

import com.skyworth.rxqwelibrary.base.BasePresenter;
import com.skyworth.rxqwelibrary.base.BaseView;

/**
 * Created by zhangguihua
 */
public interface DetailContentContract {

    interface View extends BaseView {
        //返回登陆结果
        void responeError(String errorMsg);

        void setEassyTxt(String txt);
    }

    interface Presenter extends BasePresenter<View> {
    }
}