package com.hlxyedu.putonghualearningsystem.ui.main.contract;

import com.hlxyedu.putonghualearningsystem.model.bean.TopTitleVO;
import com.skyworth.rxqwelibrary.base.BasePresenter;
import com.skyworth.rxqwelibrary.base.BaseView;

import java.util.List;

/**
 * Created by zhangguihua
 */
public interface FamousClassroomContract {

    interface View extends BaseView {
        //返回登陆结果
        void responeError(String errorMsg);

        void onSuccess(List<TopTitleVO> lists);
    }

    interface Presenter extends BasePresenter<View> {

        void getTeacherTitle();
    }
}