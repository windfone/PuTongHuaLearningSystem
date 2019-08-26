package com.hlxyedu.putonghualearningsystem.ui.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootFragment;
import com.hlxyedu.putonghualearningsystem.model.bean.TopTitleVO;
import com.hlxyedu.putonghualearningsystem.ui.main.contract.FamousClassroomContract;
import com.hlxyedu.putonghualearningsystem.ui.main.presenter.FamousClassroomPresenter;
import com.hlxyedu.putonghualearningsystem.ui.publics.fragment.ViewPagerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangguihua
 */
public class FamousClassroomFragment extends RootFragment<FamousClassroomPresenter> implements FamousClassroomContract.View {

//    private List<String> top = Arrays.asList("听力理解", "阅读理解", "书面表达", "口语测试", "考试冲刺");
    private ArrayList<String> mTitleDataList;

    public static FamousClassroomFragment newInstance() {
        Bundle args = new Bundle();

        FamousClassroomFragment fragment = new FamousClassroomFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_famous_classroom;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.getTeacherTitle();
//        if (findChildFragment(ViewPagerFragment.class) == null) {
//            loadRootFragment(R.id.fl_second_container, ViewPagerFragment.newInstance(2, mTitleDataList));
//        }
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void responeError(String errorMsg) {

    }

    @Override
    public void onSuccess(List<TopTitleVO> lists) {
        mTitleDataList = new ArrayList<>();
        if (lists != null){
            for (int i = 0; i < lists.size(); i++) {
                mTitleDataList.add(lists.get(i).getTeType());
            }
        }
        if (findChildFragment(ViewPagerFragment.class) == null) {
            loadRootFragment(R.id.fl_second_container, ViewPagerFragment.newInstance(2, mTitleDataList,lists));
        }
    }

}