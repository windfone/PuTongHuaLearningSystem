package com.hlxyedu.putonghualearningsystem.ui.teacherclass.contract;

import com.hlxyedu.putonghualearningsystem.model.bean.VideoVO;
import com.skyworth.rxqwelibrary.base.BasePresenter;
import com.skyworth.rxqwelibrary.base.BaseView;

import java.util.List;

/**
 * Created by zhangguihua
 */
public interface ExerciseContract {

    interface View extends BaseView {
        //返回登陆结果
        void responeError(String errorMsg);

        void onSuccess(List<VideoVO> lists);

        void orderBy(int orderType);
    }

    interface Presenter extends BasePresenter<View> {

        void getVideoList(int typeId,int orderBy, int currentPage,int pageSize);
    }
}