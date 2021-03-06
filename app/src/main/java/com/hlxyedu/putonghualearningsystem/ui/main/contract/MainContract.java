package com.hlxyedu.putonghualearningsystem.ui.main.contract;

import com.skyworth.rxqwelibrary.base.BasePresenter;
import com.skyworth.rxqwelibrary.base.BaseView;

/**
 * Created by zhangguihua
 */
public interface MainContract {

    interface View extends BaseView {
        //返回登陆结果
        void responeError(String errorMsg);

        void changeSelTab(int which);
    }

    interface Presenter extends BasePresenter<View> {


    }
}