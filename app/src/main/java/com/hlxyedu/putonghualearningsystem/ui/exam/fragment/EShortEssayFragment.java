package com.hlxyedu.putonghualearningsystem.ui.exam.fragment;

import android.os.Bundle;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootFragment;
import com.hlxyedu.putonghualearningsystem.ui.exam.contract.EShortEssayContract;
import com.hlxyedu.putonghualearningsystem.ui.exam.presenter.EShortEssayPresenter;

/**
 * Created by zhangguihua
 */
public class EShortEssayFragment extends RootFragment<EShortEssayPresenter> implements EShortEssayContract.View {


    public static EShortEssayFragment newInstance() {
        Bundle args = new Bundle();

        EShortEssayFragment fragment = new EShortEssayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_eshort_essay;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void responeError(String errorMsg) {

    }

}