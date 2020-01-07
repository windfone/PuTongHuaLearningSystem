package com.hlxyedu.putonghualearningsystem.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootFragmentActivity;
import com.hlxyedu.putonghualearningsystem.ui.main.contract.MainContract;
import com.hlxyedu.putonghualearningsystem.ui.main.fragment.ExamCenterFragment;
import com.hlxyedu.putonghualearningsystem.ui.main.fragment.FamousClassroomFragment;
import com.hlxyedu.putonghualearningsystem.ui.main.fragment.HomeFragment;
import com.hlxyedu.putonghualearningsystem.ui.main.fragment.OnLineLearningFragment;
import com.hlxyedu.putonghualearningsystem.ui.main.fragment.PersonalCenterFragment;
import com.hlxyedu.putonghualearningsystem.ui.main.presenter.MainPresenter;
import com.hlxyedu.putonghualearningsystem.weight.bottombar.BottomBar;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by zhangguihua
 */
public class MainActivity extends RootFragmentActivity<MainPresenter> implements MainContract.View {

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;
    public static final int FIFTH = 4;
    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;
    private SupportFragment[] mFragments = new SupportFragment[5];
    List<String> navigations = Arrays.asList("首页","在线学习","模考中心","名师课堂","个人中心");
    List<Integer> bottomIcons = Arrays.asList(R.drawable.icon_bar_selector_home,R.drawable.icon_bar_selector_online_learning,
                                            R.drawable.icon_bar_selector_exam_center,R.drawable.icon_bar_selector_famous_classroom,
                                            R.drawable.icon_bar_selector_personal_center);
//    @BindView(R.id.main_topbar)
//    MainTopBar main_topbar;

    /**
     * 打开新Activity
     *
     * @param context
     * @return
     */
    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, MainActivity.class);

        return intent;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
        //设置宽高
        int screenWidth = getWindowManager().getDefaultDisplay().getWidth();
        mBottomBar.initBottomBar(screenWidth,MainActivity.this,navigations,bottomIcons);

        SupportFragment firstFragment = findFragment(HomeFragment.class);
        if (firstFragment == null) {
            mFragments[FIRST] = HomeFragment.newInstance();
            mFragments[SECOND] = OnLineLearningFragment.newInstance();
            mFragments[THIRD] = ExamCenterFragment.newInstance();
            mFragments[FOURTH] = FamousClassroomFragment.newInstance();
            mFragments[FIFTH] = PersonalCenterFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_tab_container, FIRST,
                    mFragments[FIRST], mFragments[SECOND], mFragments[THIRD], mFragments[FOURTH], mFragments[FIFTH]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题

            // 这里我们需要拿到mFragments的引用,也可以通过getChildFragmentManager.findFragmentByTag自行进行判断查找(效率更高些),用下面的方法查找更方便些
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findFragment(OnLineLearningFragment.class);
            mFragments[THIRD] = findFragment(ExamCenterFragment.class);
            mFragments[FOURTH] = findFragment(FamousClassroomFragment.class);
            mFragments[FIFTH] = findFragment(PersonalCenterFragment.class);
        }

        initView();
        mBottomBar.setCurrentItem(0);
    }

    private void initView() {
        mBottomBar.setOnTabSelectedListener((position, prePosition) -> {
            showHideFragment(mFragments[position], mFragments[prePosition]);
            switch (position) {
                case 0:
//                    main_topbar.setVisibility(View.VISIBLE);
//                    main_topbar.setMiddleText(getResources().getString(R.string.home_title));
                    break;
                case 1:
//                    main_topbar.setVisibility(View.VISIBLE);
//                    main_topbar.setMiddleText(getResources().getString(R.string.news_flash));
                    break;
                case 2:
//                    main_topbar.setVisibility(View.VISIBLE);
//                    main_topbar.setMiddleText(getResources().getString(R.string.bookshelf));
                    break;
                case 3:
//                    main_topbar.setVisibility(View.GONE);
                    break;
            }
        });
    }

    /**
     * 退出登录后 需要重新进入main时 调用， 在此需要清空数据
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mBottomBar.setCurrentItem(0);
    }

    //连续俩下 退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void responeError(String errorMsg) {

    }

    @Override
    public void changeSelTab(int which) {
        mBottomBar.clickWhichItem(which);
    }

}