package com.hlxyedu.putonghualearningsystem.ui.essay.contract;

import com.hlxyedu.putonghualearningsystem.model.bean.EssayVO;
import com.skyworth.rxqwelibrary.base.BasePresenter;
import com.skyworth.rxqwelibrary.base.BaseView;

import java.util.List;

/**
 * Created by zhangguihua
 */
public interface EssayListContract {

    interface View extends BaseView {
        //返回登陆结果
        void responeError(String errorMsg);

        void onSuccess(List<EssayVO> essayVOS);
    }

    interface Presenter extends BasePresenter<View> {

        void getEssays();
    }
}