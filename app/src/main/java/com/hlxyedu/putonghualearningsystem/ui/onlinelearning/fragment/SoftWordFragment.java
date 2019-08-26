package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootFragment;
import com.hlxyedu.putonghualearningsystem.model.bean.DataVO;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.adapter.SoftWordAdapter;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.contract.SoftWordContract;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.presenter.SoftWordPresenter;

import java.util.List;
import java.util.UUID;

import butterknife.BindView;

/**
 * Created by zhangguihua
 * 轻声字
 */
public class SoftWordFragment extends RootFragment<SoftWordPresenter> implements SoftWordContract.View {

    @BindView(R.id.rcy)
    RecyclerView rcy;

    private SoftWordAdapter mAdapter;

    public static SoftWordFragment newInstance(String mTitles, int typeId) {
        Bundle args = new Bundle();
        args.putString("title", mTitles);
        args.putInt("typeId", typeId);

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

//        mPresenter.getLearningList(getArguments().getInt("typeId"));
    }

    @Override
    public void onSuccess(List<DataVO> dataVOS) {
        stateMain();
        for (int i = 0; i < dataVOS.size(); i++) {
            dataVOS.get(i).setId(UUID.randomUUID() + "");
        }
        if (dataVOS == null || dataVOS.isEmpty()) {
            stateEmpty("暂无内容");
        } else {
            mAdapter = new SoftWordAdapter(R.layout.item_pinyin, dataVOS, getArguments().getString("title"));
            rcy.setLayoutManager(
                    new LinearLayoutManager(mActivity));
            rcy.setAdapter(mAdapter);
        }
    }

    @Override
    public void responeError(String errorMsg) {

    }

}