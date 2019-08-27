package com.hlxyedu.putonghualearningsystem.ui.main.contract;

import com.hlxyedu.putonghualearningsystem.model.bean.DataVO;
import com.hlxyedu.putonghualearningsystem.model.bean.TopTitleVO;
import com.skyworth.rxqwelibrary.base.BasePresenter;
import com.skyworth.rxqwelibrary.base.BaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangguihua
 */
public interface OnLineLearningContract {

    interface View extends BaseView {
        //返回登陆结果
        void responeError(String errorMsg);

        void onTopSuccess(List<TopTitleVO> topTitleVOS);

        void isLogin(int pos, ArrayList<DataVO> lists, String title, String conTitle);
    }

    interface Presenter extends BasePresenter<View> {

        void getTopTitle();

        boolean loginStatus();
    }
}