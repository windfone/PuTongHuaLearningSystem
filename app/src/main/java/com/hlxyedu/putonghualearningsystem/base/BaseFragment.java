package com.hlxyedu.putonghualearningsystem.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.hlxyedu.putonghualearningsystem.app.AppContext;
import com.hlxyedu.putonghualearningsystem.di.component.DaggerFragmentComponent;
import com.hlxyedu.putonghualearningsystem.di.component.FragmentComponent;
import com.hlxyedu.putonghualearningsystem.di.module.FragmentModule;
import com.skyworth.rxqwelibrary.base.BasePresenter;
import com.skyworth.rxqwelibrary.base.BaseView;
import com.skyworth.rxqwelibrary.base.SimpleFragment;

import javax.inject.Inject;

/**
 * Created by skyworth on 2016/8/2.
 * MVP Fragment基类
 */
public abstract class BaseFragment<T extends BasePresenter> extends SimpleFragment implements BaseView {

    @Inject
    protected T mPresenter;

    protected FragmentComponent getFragmentComponent(){
        return DaggerFragmentComponent.builder()
                .appComponent(AppContext.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule(){
        return new FragmentModule(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initInject();
        mPresenter.attachView(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) mPresenter.detachView();
        super.onDestroyView();
    }

    @Override
    public void showErrorMsg(String msg) {
    }


    @Override
    public void stateError() {

    }

    @Override
    public void stateEmpty(String msg) {

    }

    @Override
    public void stateLoading() {

    }


    @Override
    public void stateMain() {

    }

    protected abstract void initInject();
}