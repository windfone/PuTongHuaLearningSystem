package com.hlxyedu.putonghualearningsystem.ui.teacherclass.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootFragment;
import com.hlxyedu.putonghualearningsystem.model.bean.VideoVO;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.adapter.PinYinAdapter;
import com.hlxyedu.putonghualearningsystem.ui.teacherclass.adapter.ExerciseListAdapter;
import com.hlxyedu.putonghualearningsystem.ui.teacherclass.contract.ExerciseContract;
import com.hlxyedu.putonghualearningsystem.ui.teacherclass.presenter.ExercisePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;

/**
 * Created by zhangguihua
 */
public class ExerciseFragment extends RootFragment<ExercisePresenter> implements ExerciseContract.View {

    @BindView(R.id.rcy)
    RecyclerView rcy;

    private ExerciseListAdapter mAdapter;

    public static ExerciseFragment newInstance(String mTitles, int typeId) {
        Bundle args = new Bundle();
        args.putString("title", mTitles);
        args.putInt("typeId", typeId);

        ExerciseFragment fragment = new ExerciseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_exercise;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        stateLoading();
        mPresenter.getVideoList(getArguments().getInt("typeId"),0,1,5); // 默认是阅读量排序

    }

    @Override
    public void orderBy(int orderType) {
        mPresenter.getVideoList(getArguments().getInt("typeId"),orderType,1,5);
    }

    @Override
    public void onSuccess(List<VideoVO> lists) {
        stateMain();
        if (lists == null || lists.isEmpty()) {
            stateEmpty("暂无内容");
        } else {
            mAdapter = new ExerciseListAdapter(R.layout.item_exercise_list, lists, getArguments().getString("title"));
            rcy.setLayoutManager(
                    new LinearLayoutManager(mActivity));
            rcy.setAdapter(mAdapter);
        }
    }


    @Override
    public void responeError(String errorMsg) {

    }

}