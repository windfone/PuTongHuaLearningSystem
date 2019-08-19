package com.skyworth.rxqwelibrary.base;

/**
 * Created by skyworth on 2016/8/2.
 * View基类
 */
public interface BaseView {

    void showErrorMsg(String msg);

    //=======  State  =======
    void stateError();

    void stateEmpty(String msg);

    void stateLoading();

    void stateMain();

}
