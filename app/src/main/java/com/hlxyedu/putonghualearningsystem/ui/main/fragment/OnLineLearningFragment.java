package com.hlxyedu.putonghualearningsystem.ui.main.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootFragment;
import com.hlxyedu.putonghualearningsystem.model.bean.DataVO;
import com.hlxyedu.putonghualearningsystem.model.bean.TopTitleVO;
import com.hlxyedu.putonghualearningsystem.ui.login.activity.LoginActivity;
import com.hlxyedu.putonghualearningsystem.ui.main.contract.OnLineLearningContract;
import com.hlxyedu.putonghualearningsystem.ui.main.presenter.OnLineLearningPresenter;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.activity.OnLineLearnDetailsActivity;
import com.hlxyedu.putonghualearningsystem.ui.publics.fragment.ViewPagerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangguihua
 */
public class OnLineLearningFragment extends RootFragment<OnLineLearningPresenter> implements OnLineLearningContract.View {

//    private String[] mTitleDataList = {"拼音学习","汉字学习","单词跟读","短文跟读","轻声字词","儿化音"};
    private ArrayList<String> mTitleDataList;

    public static OnLineLearningFragment newInstance() {
        Bundle args = new Bundle();

        OnLineLearningFragment fragment = new OnLineLearningFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_online_learning;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mPresenter.getTopTitle();
    }

    @Override
    public void onTopSuccess(List<TopTitleVO> topTitleVOS) {
        mTitleDataList = new ArrayList<>();
        if (topTitleVOS != null){
            for (int i = 0; i < topTitleVOS.size(); i++) {
                mTitleDataList.add(topTitleVOS.get(i).getExType());
            }
        }

        if (findChildFragment(ViewPagerFragment.class) == null) {
            loadRootFragment(R.id.fl_second_container, ViewPagerFragment.newInstance(1,mTitleDataList,topTitleVOS));
        }
    }

    /**
     *  跳转详情需要登录，统一判断是否登录
     */
    @Override
    public void isLogin(int pos, ArrayList<DataVO> lists, String title, String conTitle) {
        if (mPresenter.loginStatus()){
            startActivity(OnLineLearnDetailsActivity.newInstance(mActivity,pos,lists,title,conTitle));
        }else {
            startActivity(LoginActivity.newInstance(mActivity));
        }
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void responeError(String errorMsg) {

    }


}