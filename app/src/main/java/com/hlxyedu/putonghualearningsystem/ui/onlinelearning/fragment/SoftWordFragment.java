package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootFragment;
import com.hlxyedu.putonghualearningsystem.model.bean.PinYinBean;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.adapter.SoftWordAdapter;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.contract.SoftWordContract;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.presenter.SoftWordPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zhangguihua
 * 轻声字
 */
public class SoftWordFragment extends RootFragment<SoftWordPresenter> implements SoftWordContract.View {

    @BindView(R.id.rcy)
    RecyclerView rcy;

    private SoftWordAdapter mAdapter;

    public static SoftWordFragment newInstance(String mTitles) {
        Bundle args = new Bundle();
        args.putString("title",mTitles);

        SoftWordFragment fragment = new SoftWordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_soft_word;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        stateLoading();

        List<PinYinBean> lists = new ArrayList<>();
        PinYinBean bean = new PinYinBean();
        bean.setTitle("普通话拼音学习教程第一课 a o e");
        for (int i = 0; i < 10; i++) {
            lists.add(bean);
        }
        stateMain();
        mAdapter = new SoftWordAdapter(R.layout.item_pinyin,lists,getArguments().getString("title"));
        rcy.setLayoutManager(
                new LinearLayoutManager(mActivity));
        rcy.setAdapter(mAdapter);
    }

    @Override
    public void responeError(String errorMsg) {

    }

}