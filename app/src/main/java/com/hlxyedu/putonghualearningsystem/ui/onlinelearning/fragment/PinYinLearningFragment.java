package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootFragment;
import com.hlxyedu.putonghualearningsystem.model.bean.PinYinBean;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.adapter.PinYinAdapter;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.contract.PinYinLearningContract;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.presenter.PinYinLearningPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zhangguihua
 */
public class PinYinLearningFragment extends RootFragment<PinYinLearningPresenter> implements PinYinLearningContract.View {


    @BindView(R.id.rcy)
    RecyclerView rcy;

    private PinYinAdapter mAdapter;

    public static PinYinLearningFragment newInstance(String mTitles) {
        Bundle args = new Bundle();
        args.putString("title",mTitles);

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

        List<PinYinBean> lists = new ArrayList<>();
        PinYinBean bean = new PinYinBean();
        bean.setTitle("普通话拼音学习教程第一课 a o e");
        for (int i = 0; i < 10; i++) {
            lists.add(bean);
        }
        stateMain();
        mAdapter = new PinYinAdapter(R.layout.item_pinyin,lists,getArguments().getString("title"));
        rcy.setLayoutManager(
                new LinearLayoutManager(mActivity));
        rcy.setAdapter(mAdapter);
    }

    @Override
    public void responeError(String errorMsg) {
        stateError();
    }

}