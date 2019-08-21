package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootFragment;
import com.hlxyedu.putonghualearningsystem.model.bean.DataVO;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.adapter.PinYinAdapter;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.contract.PinYinLearningContract;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.presenter.PinYinLearningPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zhangguihua
 */
public class PinYinLearningFragment extends RootFragment<PinYinLearningPresenter> implements PinYinLearningContract.View {


    @BindView(R.id.rcy)
    RecyclerView rcy;

    private PinYinAdapter mAdapter;

    public static PinYinLearningFragment newInstance(String mTitles) {
        Bundle args = new Bundle();
        args.putString("title", mTitles);

        PinYinLearningFragment fragment = new PinYinLearningFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pin_yin_learning;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        stateLoading();

        List<DataVO> lists = new ArrayList<>();
        DataVO bean = new DataVO();
        bean.setTitle("普通话拼音学习教程第一课 a o e");
        for (int i = 0; i < 10; i++) {
            lists.add(bean);
        }
        stateMain();
        mAdapter = new PinYinAdapter(R.layout.item_pinyin, lists, getArguments().getString("title"));
        rcy.setLayoutManager(
                new LinearLayoutManager(mActivity));
        rcy.setAdapter(mAdapter);
    }

    @Override
    public void responeError(String errorMsg) {
        stateError();
    }

}