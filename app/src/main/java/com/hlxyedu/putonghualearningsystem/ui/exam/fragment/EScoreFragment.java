package com.hlxyedu.putonghualearningsystem.ui.exam.fragment;

import android.os.Bundle;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootFragment;
import com.hlxyedu.putonghualearningsystem.ui.exam.contract.EScoreContract;
import com.hlxyedu.putonghualearningsystem.ui.exam.presenter.EScorePresenter;

/**
 * Created by zhangguihua
 */
public class EScoreFragment extends RootFragment<EScorePresenter> implements EScoreContract.View {


    public static EScoreFragment newInstance() {
        Bundle args = new Bundle();

        EScoreFragment fragment = new EScoreFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_escore;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void responeError(String errorMsg) {

    }

}