package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootFragment;
import com.hlxyedu.putonghualearningsystem.model.bean.DataVO;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.adapter.ShortEssayAdapter;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.contract.ShortEssayContract;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.presenter.ShortEssayPresenter;

import java.util.List;
import java.util.UUID;

import butterknife.BindView;

/**
 * Created by zhangguihua
 * 短文跟读
 */
public class ShortEssayFragment extends RootFragment<ShortEssayPresenter> implements ShortEssayContract.View {

    @BindView(R.id.rcy)
    RecyclerView rcy;

    private ShortEssayAdapter mAdapter;

    public static ShortEssayFragment newInstance(String mTitles,int typeId) {
        Bundle args = new Bundle();
        args.putString("title", mTitles);
        args.putInt("typeId", typeId);

        ShortEssayFragment fragment = new ShortEssayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_short_essay;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        stateLoading();
        mPresenter.getLearningList(getArguments().getInt("typeId"));

    }

    @Override
    public void onSuccess(List<DataVO> essayVOS) {
        stateMain();
        for (int i = 0; i < essayVOS.size(); i++) {
            essayVOS.get(i).setId(UUID.randomUUID() + "");
        }
        if (essayVOS == null || essayVOS.isEmpty()) {
            stateEmpty("暂无内容");
        } else {
            mAdapter = new ShortEssayAdapter(R.layout.item_pinyin, essayVOS, getArguments().getString("title"));
            rcy.setLayoutManager(new LinearLayoutManager(mActivity));
            rcy.setAdapter(mAdapter);
        }
    }

    @Override
    public void responeError(String errorMsg) {
        stateError();
    }

}