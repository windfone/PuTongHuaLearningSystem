package com.hlxyedu.putonghualearningsystem.ui.teacherclass.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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

    private List<VideoVO> dataVOList = new ArrayList<>();
    private int pageSize = 5;
    private int count = 1; // 当前页数;
    private int orderBy = 0; // 排序方式

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

        mAdapter = new ExerciseListAdapter(R.layout.item_exercise_list, dataVOList, getArguments().getString("title"));
        rcy.setLayoutManager(
                new LinearLayoutManager(mActivity));
        rcy.setAdapter(mAdapter);

        count = 1;
        if (!dataVOList.isEmpty()) {
            dataVOList.clear();
        }
        mPresenter.getVideoList(getArguments().getInt("typeId"),orderBy,count,pageSize); // 默认是阅读量排序

        mAdapter.setPreLoadNumber(1);
        mAdapter.setOnLoadMoreListener(() -> {
            count++;
            mPresenter.getVideoList(getArguments().getInt("typeId"),orderBy,count,pageSize); // 默认是阅读量排序
        }, rcy);


    }

    @Override
    public void orderBy(int orderType) {
        orderBy = orderType;
        dataVOList.clear();
        count = 1;
        mPresenter.getVideoList(getArguments().getInt("typeId"),orderBy,count,pageSize);
    }

    @Override
    public void onSuccess(List<VideoVO> lists) {
        if (!lists.isEmpty()) {
            dataVOList.addAll(lists);
            mAdapter.setNewData(dataVOList);
            if (lists.size() < pageSize) {
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


    @Override
    public void responeError(String errorMsg) {

    }

}