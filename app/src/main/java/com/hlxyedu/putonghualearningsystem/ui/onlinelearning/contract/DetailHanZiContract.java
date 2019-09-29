package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.contract;

import com.skyworth.rxqwelibrary.base.BasePresenter;
import com.skyworth.rxqwelibrary.base.BaseView;

/**
 * Created by zhangguihau
 */
public interface DetailHanZiContract {

    interface View extends BaseView {
        //返回登陆结果
        void responeError(String errorMsg);

//        void setContentText(String pinYin,String hanZi,String videoUrl);
        void setContentText(String pinYin,String wordImg,String videoUrl);

        void switchInitView();
    }

    interface Presenter extends BasePresenter<View> {
    }
}