package com.hlxyedu.putonghualearningsystem.ui.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootFragment;
import com.hlxyedu.putonghualearningsystem.ui.main.contract.FamousClassroomContract;
import com.hlxyedu.putonghualearningsystem.ui.main.presenter.FamousClassroomPresenter;
import com.hlxyedu.putonghualearningsystem.ui.publics.fragment.ViewPagerFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhangguihua
 */
public class FamousClassroomFragment extends RootFragment<FamousClassroomPresenter> implements FamousClassroomContract.View {

    /*@BindView(R.id.top_indicator)
    MagicIndicator top_indicator;
    @BindView(R.id.view_pager)

    private List<Fragment> fragments;    ViewPager view_pager;*/

    private List<String> top = Arrays.asList("听力理解","阅读理解","书面表达","口语测试","考试冲刺");
    private ArrayList<String> mTitleDataList = new ArrayList<>(top);

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
        if (findChildFragment(ViewPagerFragment.class) == null) {
            loadRootFragment(R.id.fl_second_container, ViewPagerFragment.newInstance(2,mTitleDataList));
        }
    }

    @Override
    protected void initEventAndData() {
        /*initIndicator();
        fragments = new ArrayList<>();
        for (int i = 0; i < mTitleDataList.length; i++) {
            fragments.add(ExerciseFragment.newInstance(mTitleDataList[i]));
        }
        view_pager.setAdapter(new MyFragmentPagerAdapter(getFragmentManager(),fragments));*/
    }

   /* private void initIndicator() {
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
    }*/

    @Override
    public void responeError(String errorMsg) {

    }

}