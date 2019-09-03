package com.hlxyedu.putonghualearningsystem.ui.teacherclass.contract;

import com.hlxyedu.putonghualearningsystem.model.bean.CommentVO;
import com.skyworth.rxqwelibrary.base.BasePresenter;
import com.skyworth.rxqwelibrary.base.BaseView;

import java.util.List;

/**
 * Created by zhangguihua
 */
public interface ExerciseDetailContract {

    interface View extends BaseView {
        //返回登陆结果
        void responeError(String errorMsg);

        void onCommentSuccess(List<CommentVO> commentVOS);
    }

    interface Presenter extends BasePresenter<View> {

        void getCommentList(int teaId,int currentPage,int pageSize);

    }
}