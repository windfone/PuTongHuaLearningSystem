package com.hlxyedu.putonghualearningsystem.ui.teacherclass.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootFragment;
import com.hlxyedu.putonghualearningsystem.model.bean.VideoVO;
import com.hlxyedu.putonghualearningsystem.ui.teacherclass.adapter.ExerciseListAdapter;
import com.hlxyedu.putonghualearningsystem.ui.teacherclass.contract.ExerciseContract;
import com.hlxyedu.putonghualearningsystem.ui.teacherclass.presenter.ExercisePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhangguihua
 */
public class ExerciseFragment extends RootFragment<ExercisePresenter> implements ExerciseContract.View {


    /*@BindView(R.id.reading_sort_rl)
    RelativeLayout reading_sort_rl;
    @BindView(R.id.date_sort_rl)
    RelativeLayout date_sort_rl;*/
    @BindView(R.id.rcy)
    RecyclerView rcy;

    private ExerciseListAdapter mAdapter;

    public static ExerciseFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString("type", title);
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
        if (getArguments() != null) {
            String type = getArguments().getString("type");
        }

        List<VideoVO> lists = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            VideoVO bean = new VideoVO();
            lists.add(bean);
        }
        mAdapter = new ExerciseListAdapter(R.layout.item_exercise_list,lists);
        rcy.setLayoutManager(new LinearLayoutManager(mActivity));
        rcy.setAdapter(mAdapter);
    }

    @Override
    public void responeError(String errorMsg) {

    }

   /* @OnClick({R.id.reading_sort_rl, R.id.date_sort_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reading_sort_rl:
                break;
            case R.id.date_sort_rl:
                break;
        }
    }*/
}