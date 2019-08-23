package com.hlxyedu.putonghualearningsystem.ui.exam.fragment;

import android.os.Bundle;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootFragment;
import com.hlxyedu.putonghualearningsystem.ui.exam.contract.EZuoWenContract;
import com.hlxyedu.putonghualearningsystem.ui.exam.presenter.EZuoWenPresenter;

/**
 * Created by zhangguihua
 */
public class EZuoWenFragment extends RootFragment<EZuoWenPresenter> implements EZuoWenContract.View {


    public static EZuoWenFragment newInstance() {
        Bundle args = new Bundle();

        EZuoWenFragment fragment = new EZuoWenFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ezuo_wen;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void responeError(String errorMsg) {

    }

}