package com.hlxyedu.putonghualearningsystem.ui.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootFragment;
import com.hlxyedu.putonghualearningsystem.model.bean.ExamCenterVO;
import com.hlxyedu.putonghualearningsystem.ui.main.adapter.ExamCenterAdapter;
import com.hlxyedu.putonghualearningsystem.ui.main.contract.ExamCenterContract;
import com.hlxyedu.putonghualearningsystem.ui.main.presenter.ExamCenterPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zhangguihua
 * 模考中心
 */
public class ExamCenterFragment extends RootFragment<ExamCenterPresenter> implements ExamCenterContract.View {

    @BindView(R.id.rcy)
    RecyclerView rcy;

    private ExamCenterAdapter mAdapter;

    public static ExamCenterFragment newInstance() {
        Bundle args = new Bundle();

        ExamCenterFragment fragment = new ExamCenterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_exam_center;
    }

    @Override
    protected void initEventAndData() {

        List<ExamCenterVO> lists = new ArrayList<>();
        ExamCenterVO examCenterVO;
        for (int i = 0; i < 10; i++) {
            examCenterVO = new ExamCenterVO();
            examCenterVO.setPaperTitle("普通话模拟考试试卷第" + i + "套");
            lists.add(examCenterVO);
        }
        mAdapter = new ExamCenterAdapter(R.layout.item_pinyin,lists);
        rcy.setLayoutManager(new LinearLayoutManager(mActivity));
        rcy.setAdapter(mAdapter);
    }

    @Override
    public void responeError(String errorMsg) {

    }

}