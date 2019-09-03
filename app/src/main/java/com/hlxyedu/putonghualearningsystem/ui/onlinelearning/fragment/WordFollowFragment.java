package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootFragment;
import com.hlxyedu.putonghualearningsystem.model.bean.DataVO;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.adapter.WordFollowAdapter;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.contract.WordFollowContract;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.presenter.WordFollowPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;

/**
 * Created by zhangguihua
 */
public class WordFollowFragment extends RootFragment<WordFollowPresenter> implements WordFollowContract.View {

    @BindView(R.id.rcy)
    RecyclerView rcy;

    private WordFollowAdapter mAdapter;

    private List<DataVO> dataVOList = new ArrayList<>();
    private int pageSize = 20;
    private int count = 1; // 当前页数;

    public static WordFollowFragment newInstance(String mTitles, int typeId) {
        Bundle args = new Bundle();
        args.putString("title", mTitles);
        args.putInt("typeId", typeId);

        WordFollowFragment fragment = new WordFollowFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_word_follow;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        stateLoading();

        mAdapter = new WordFollowAdapter(R.layout.item_pinyin, dataVOList, getArguments().getString("title"));
        rcy.setLayoutManager(
                new LinearLayoutManager(mActivity));
        rcy.setAdapter(mAdapter);

        count = 1;
        if (!dataVOList.isEmpty()) {
            dataVOList.clear();
        }
        mPresenter.getLearningList(getArguments().getInt("typeId"),pageSize,count);

        mAdapter.setPreLoadNumber(1);
        mAdapter.setOnLoadMoreListener(() -> {
            count++;
            mPresenter.getLearningList(getArguments().getInt("typeId"),pageSize,count);
        }, rcy);
    }

    @Override
    public void responeError(String errorMsg) {

    }

    @Override
    public void onSuccess(List<DataVO> dataVOS) {
        if (!dataVOS.isEmpty()) {
            dataVOList.addAll(dataVOS);
            mAdapter.setNewData(dataVOList);
            if (dataVOS.size() < pageSize) {
                mAdapter.loadMoreEnd();
            } else {
                mAdapter.loadMoreComplete();
            }
            stateMain();
        } else {
            if (count == 1) {
                stateEmpty("暂无内容");
            } else {
                mAdapter.loadMoreEnd();
            }
        }
    }

}