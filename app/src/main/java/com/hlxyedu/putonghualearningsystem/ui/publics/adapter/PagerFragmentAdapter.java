package com.hlxyedu.putonghualearningsystem.ui.publics.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment.ChildSoundFragment;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment.HanZiLearningFragment;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment.PinYinLearningFragment;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment.ShortEssayFragment;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment.SoftWordFragment;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment.WordFollowFragment;
import com.hlxyedu.putonghualearningsystem.ui.teacherclass.fragment.ExerciseFragment;

public class PagerFragmentAdapter extends FragmentPagerAdapter {
    private String[] mTitles;
    private int from;

    public PagerFragmentAdapter(FragmentManager fm, int mFrom, String... titles) {
        super(fm);
        mTitles = titles;
        from = mFrom;
    }

    @Override
    public Fragment getItem(int position) {
        if (from == 1){
            switch (position){
                case 0:
                    return PinYinLearningFragment.newInstance(mTitles[position]);
                case 1:
                    return HanZiLearningFragment.newInstance(mTitles[position]);
                case 2:
                    return WordFollowFragment.newInstance(mTitles[position]);
                case 3:
                    return ShortEssayFragment.newInstance(mTitles[position]);
                case 4:
                    return SoftWordFragment.newInstance(mTitles[position]);
                case 5:
                    return ChildSoundFragment.newInstance(mTitles[position]);
            }
        }else if (from == 2){
            return ExerciseFragment.newInstance(mTitles[position]);
        }
        return null;
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
