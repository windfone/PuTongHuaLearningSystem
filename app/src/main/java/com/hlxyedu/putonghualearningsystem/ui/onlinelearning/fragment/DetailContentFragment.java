package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootFragment;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.contract.DetailContentContract;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.presenter.DetailContentPresenter;

import butterknife.BindView;

/**
 * Created by zhangguihua
 * 拼音学习，短文跟读 详情
 */
public class DetailContentFragment extends RootFragment<DetailContentPresenter> implements DetailContentContract.View {
    @BindView(R.id.essay_tv)
    TextView essay_tv;

    public static DetailContentFragment newInstance() {
        Bundle args = new Bundle();

        DetailContentFragment fragment = new DetailContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_content;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void responeError(String errorMsg) {

    }

    @Override
    public void setEassyTxt(String txt) {
        essay_tv.setText(txt);
    }

}