package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootFragment;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.contract.DetailEssayContract;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.presenter.DetailEssayPresenter;

import butterknife.BindView;

/**
 * Created by zhangguihua
 * 拼音学习，短文跟读 详情
 */
public class DetailEssayFragment extends RootFragment<DetailEssayPresenter> implements DetailEssayContract.View {
    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.essay_tv)
    TextView essay_tv;

    private String itemStr;

    public static DetailEssayFragment newInstance(String itemStr) {
        Bundle args = new Bundle();

        args.putString("itemStr",itemStr);
        DetailEssayFragment fragment = new DetailEssayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_essay;
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