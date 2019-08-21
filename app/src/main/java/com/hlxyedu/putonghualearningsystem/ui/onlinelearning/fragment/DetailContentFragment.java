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
    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.essay_tv)
    TextView essay_tv;

    private String itemStr;

    public static DetailContentFragment newInstance(String itemStr) {
        Bundle args = new Bundle();

        args.putString("itemStr",itemStr);
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
        itemStr = getArguments().getString("itemStr");
        title_tv.setText(itemStr);
    }

    @Override
    public void responeError(String errorMsg) {

    }

    @Override
    public void setEassyTxt(String txt,String title) {
        essay_tv.setText(txt);
        title_tv.setText(title);
    }

}