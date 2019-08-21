package com.hlxyedu.putonghualearningsystem.ui.main.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootFragment;
import com.hlxyedu.putonghualearningsystem.ui.main.contract.OnLineLearningContract;
import com.hlxyedu.putonghualearningsystem.ui.main.presenter.OnLineLearningPresenter;
import com.hlxyedu.putonghualearningsystem.ui.publics.fragment.ViewPagerFragment;
import com.hlxyedu.putonghualearningsystem.utils.MyFragmentPagerAdapter;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment.ChildSoundFragment;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment.HanZiLearningFragment;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment.PinYinLearningFragment;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment.ShortEssayFragment;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment.SoftWordFragment;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment.WordFollowFragment;
import com.hlxyedu.putonghualearningsystem.utils.ColorFlipPagerTitleView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zhangguihua
 */
public class OnLineLearningFragment extends RootFragment<OnLineLearningPresenter> implements OnLineLearningContract.View {

    private String[] mTitleDataList = {"拼音学习","汉字学习","单词跟读","短文跟读","轻声字词","儿化音"};


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
        if (findChildFragment(ViewPagerFragment.class) == null) {
            loadRootFragment(R.id.fl_second_container, ViewPagerFragment.newInstance(1,mTitleDataList));
        }
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void responeError(String errorMsg) {

    }

}