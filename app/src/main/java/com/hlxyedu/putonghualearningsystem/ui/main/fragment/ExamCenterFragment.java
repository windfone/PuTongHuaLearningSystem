package com.hlxyedu.putonghualearningsystem.ui.main.fragment;

import android.os.Bundle;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootFragment;
import com.hlxyedu.putonghualearningsystem.ui.main.contract.ExamCenterContract;
import com.hlxyedu.putonghualearningsystem.ui.main.presenter.ExamCenterPresenter;

/**
 * Created by zhangguihua
 */
public class ExamCenterFragment extends RootFragment<ExamCenterPresenter> implements ExamCenterContract.View {


    public static ExamCenterFragment newInstance() {
        Bundle args = new Bundle();

        ExamCenterFragment fragment = new ExamCenterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_exam_center;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void responeError(String errorMsg) {

    }

}