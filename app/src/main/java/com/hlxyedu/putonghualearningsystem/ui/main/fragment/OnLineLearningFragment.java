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


   /* @BindView(R.id.top_indicator)
    MagicIndicator top_indicator;
    @BindView(R.id.view_pager)
    ViewPager view_pager;

    private List<Fragment> fragments;*/
    private String[] mTitleDataList = {"拼音学习","汉字学习","单词跟读","短文跟读","轻声字词","儿化音"};

    /*private PinYinLearningFragment pinYinLearningFragment = PinYinLearningFragment.newInstance();
    private HanZiLearningFragment hanZiLearningFragment = HanZiLearningFragment.newInstance();
    private WordFollowFragment wordFollowFragment = WordFollowFragment.newInstance();
    private ShortEssayFragment shortEssayFragment = ShortEssayFragment.newInstance();
    private SoftWordFragment softWordFragment = SoftWordFragment.newInstance();
    private ChildSoundFragment childSoundFragment = ChildSoundFragment.newInstance();*/

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
       /* initIndicator();
        fragments = new ArrayList<>();
        fragments.add(pinYinLearningFragment);
        fragments.add(hanZiLearningFragment);
        fragments.add(wordFollowFragment);
        fragments.add(shortEssayFragment);
        fragments.add(softWordFragment);
        fragments.add(childSoundFragment);

        view_pager.setAdapter(new MyFragmentPagerAdapter(getFragmentManager(),fragments));*/
    }

    /*private void initIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(mActivity);
        commonNavigator.setScrollPivotX(0.65f);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mTitleDataList == null ? 0 : mTitleDataList.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
                simplePagerTitleView.setTextSize(TypedValue.COMPLEX_UNIT_SP,13);
                simplePagerTitleView.setText(mTitleDataList[index]);
                simplePagerTitleView.setNormalColor(ContextCompat.getColor(mActivity,R.color.gray9B9));
                simplePagerTitleView.setSelectedColor(ContextCompat.getColor(mActivity,R.color.whitefff));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        view_pager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 6));
                indicator.setLineWidth(UIUtil.dip2px(context, 10));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(ContextCompat.getColor(mActivity,R.color.blue5FC));
                return indicator;
            }
        });
        top_indicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(top_indicator, view_pager);
        *//*top_indicator.setBackgroundColor(Color.BLACK);
        CommonNavigator commonNavigator = new CommonNavigator(mActivity);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return mTitleDataList.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setNormalColor(Color.GRAY);
                simplePagerTitleView.setSelectedColor(Color.WHITE);
                simplePagerTitleView.setText(mTitleDataList[index]);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        view_pager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                linePagerIndicator.setColors(Color.WHITE);
                return linePagerIndicator;
            }
        });
        top_indicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(top_indicator, view_pager);*//*
    }*/

    @Override
    public void responeError(String errorMsg) {

    }

}