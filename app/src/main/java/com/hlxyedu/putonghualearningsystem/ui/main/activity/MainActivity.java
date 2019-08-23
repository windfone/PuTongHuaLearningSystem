package com.hlxyedu.putonghualearningsystem.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Parcelable;
import android.support.v7.app.ActionBar;
import android.view.KeyEvent;
import android.view.View;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootFragmentActivity;
import com.hlxyedu.putonghualearningsystem.model.bean.OnLineLearnTitleVO;
import com.hlxyedu.putonghualearningsystem.ui.main.contract.MainContract;
import com.hlxyedu.putonghualearningsystem.ui.main.fragment.ExamCenterFragment;
import com.hlxyedu.putonghualearningsystem.ui.main.fragment.FamousClassroomFragment;
import com.hlxyedu.putonghualearningsystem.ui.main.fragment.OnLineLearningFragment;
import com.hlxyedu.putonghualearningsystem.ui.main.fragment.PersonalCenterFragment;
import com.hlxyedu.putonghualearningsystem.ui.main.presenter.MainPresenter;
import com.hlxyedu.putonghualearningsystem.ui.publics.fragment.ViewPagerFragment;
import com.hlxyedu.putonghualearningsystem.weight.bottombar.BottomBar;

import java.util.ArrayList;
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

    private SupportFragment[] mFragments = new SupportFragment[4];

    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;

//    @BindView(R.id.main_topbar)
//    MainTopBar main_topbar;

    /**
     * 打开新Activity
     *
     * @param context
     * @return
     */
    public static Intent newInstance(Context context,List<OnLineLearnTitleVO> lists) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putParcelableArrayListExtra("topTitle", (ArrayList<? extends Parcelable>) lists);
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
        SupportFragment firstFragment = findFragment(OnLineLearningFragment.class);
        if (firstFragment == null) {
            mFragments[FIRST] = OnLineLearningFragment.newInstance(getIntent().getParcelableArrayListExtra("topTitle"));
            mFragments[SECOND] = ExamCenterFragment.newInstance();
            mFragments[THIRD] = FamousClassroomFragment.newInstance();
            mFragments[FOURTH] = PersonalCenterFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_tab_container, FIRST,
                    mFragments[FIRST], mFragments[SECOND], mFragments[THIRD],mFragments[FOURTH]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题

            // 这里我们需要拿到mFragments的引用,也可以通过getChildFragmentManager.findFragmentByTag自行进行判断查找(效率更高些),用下面的方法查找更方便些
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findFragment(ExamCenterFragment.class);
            mFragments[THIRD] = findFragment(FamousClassroomFragment.class);
            mFragments[FOURTH] = findFragment(PersonalCenterFragment.class);
        }

        initView();
        mBottomBar.setCurrentItem(0);
    }

    private void initView() {
        mBottomBar.setOnTabSelectedListener((position, prePosition) -> {
            showHideFragment(mFragments[position], mFragments[prePosition]);
            switch (position){
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
}