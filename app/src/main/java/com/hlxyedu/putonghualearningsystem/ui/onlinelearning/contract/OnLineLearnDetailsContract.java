package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.contract;

import com.hlxyedu.putonghualearningsystem.model.bean.DetailVO;
import com.skyworth.rxqwelibrary.base.BasePresenter;
import com.skyworth.rxqwelibrary.base.BaseView;

/**
 * Created by zhangguihua
 */
public interface OnLineLearnDetailsContract {

    interface View extends BaseView {
        //返回登陆结果
        void responeError(String errorMsg);

        void onShortEssayDetailsSuccess(DetailVO detailVO);

        void onHanZiDetailsSuccess(DetailVO detailVO);

        void recordSecond();

        void playAudio();
    }

    interface Presenter extends BasePresenter<OnLineLearnDetailsContract.View> {

        void getShortEssayDetails(String audioName);

        void getHanZiDetails(String conId,String pinYin);

    }
}