package com.hlxyedu.putonghualearningsystem.ui.exam.fragment;

import android.os.Bundle;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootFragment;
import com.hlxyedu.putonghualearningsystem.ui.exam.contract.EWordContract;
import com.hlxyedu.putonghualearningsystem.ui.exam.presenter.EWordPresenter;

/**
 * Created by zhangguihua
 * 字词
 */
public class EWordFragment extends RootFragment<EWordPresenter> implements EWordContract.View {


    public static EWordFragment newInstance() {
        Bundle args = new Bundle();

        EWordFragment fragment = new EWordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_eword;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void responeError(String errorMsg) {

    }

}