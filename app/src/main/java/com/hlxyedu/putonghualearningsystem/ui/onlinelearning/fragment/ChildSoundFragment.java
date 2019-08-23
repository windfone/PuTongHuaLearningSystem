package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootFragment;
import com.hlxyedu.putonghualearningsystem.model.bean.PinYinBean;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.adapter.ChildSoundAdapter;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.contract.ChildSoundContract;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.presenter.ChildSoundPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zhangguihua
 */
public class ChildSoundFragment extends RootFragment<ChildSoundPresenter> implements ChildSoundContract.View {

    @BindView(R.id.rcy)
    RecyclerView rcy;

    private ChildSoundAdapter mAdapter;

    public static ChildSoundFragment newInstance(String mTitles,int typeId) {
        Bundle args = new Bundle();
        args.putString("title", mTitles);
        args.putInt("typeId", typeId);

        ChildSoundFragment fragment = new ChildSoundFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_child_sound;
    }

    @Override
    protected void initEventAndData() {
        List<PinYinBean> lists = new ArrayList<>();
        PinYinBean bean = new PinYinBean();
        bean.setTitle("普通话拼音学习教程第一课 a o e");
        for (int i = 0; i < 10; i++) {
            lists.add(bean);
        }
        stateMain();
        mAdapter = new ChildSoundAdapter(R.layout.item_table,lists,getArguments().getString("title"));
        rcy.setLayoutManager(
                new GridLayoutManager(mContext, 3, LinearLayoutManager.VERTICAL, false));
        rcy.setAdapter(mAdapter);
    }

    @Override
    public void responeError(String errorMsg) {

    }

}