package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootFragment;
import com.hlxyedu.putonghualearningsystem.model.bean.PinYinBean;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.adapter.WordFollowAdapter;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.contract.WordFollowContract;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.presenter.WordFollowPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zhangguihua
 */
public class WordFollowFragment extends RootFragment<WordFollowPresenter> implements WordFollowContract.View {

    @BindView(R.id.rcy)
    RecyclerView rcy;

    private WordFollowAdapter mAdapter;

    public static WordFollowFragment newInstance(String mTitles) {
        Bundle args = new Bundle();
        args.putString("title",mTitles);

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

        List<PinYinBean> lists = new ArrayList<>();
        PinYinBean bean = new PinYinBean();
        bean.setTitle("普通话单词跟读教程词语表1 第001-100条");
        for (int i = 0; i < 10; i++) {
            lists.add(bean);
        }
        stateMain();
        mAdapter = new WordFollowAdapter(R.layout.item_pinyin,lists,getArguments().getString("title"));
        rcy.setLayoutManager(
                new LinearLayoutManager(mActivity));
        rcy.setAdapter(mAdapter);
    }

    @Override
    public void responeError(String errorMsg) {

    }

}