package com.hlxyedu.putonghualearningsystem.ui.publics.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hlxyedu.putonghualearningsystem.model.bean.TopTitleVO;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment.HanZiLearningFragment;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment.PinYinLearningFragment;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment.ShortEssayFragment;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment.WordFollowFragment;
import com.hlxyedu.putonghualearningsystem.ui.teacherclass.fragment.ExerciseFragment;

import java.util.ArrayList;
import java.util.List;

public class PagerFragmentAdapter extends FragmentPagerAdapter {
    private ArrayList<String> mTitles;
    private List<TopTitleVO> lists;
    private int from;

    public PagerFragmentAdapter(FragmentManager fm, int mFrom, ArrayList<String> titles, List<TopTitleVO> list) {
        super(fm);
        mTitles = titles;
        lists = list;
        from = mFrom;
    }

    @Override
    public Fragment getItem(int position) {
        if (from == 1){
            switch (position){
                /*case 0:
                    return PinYinLearningFragment.newInstance(mTitles.get(position),lists.get(position).getTypeId());
                case 1:
                    return HanZiLearningFragment.newInstance(mTitles.get(position),lists.get(position).getTypeId());
                case 2:
                    return WordFollowFragment.newInstance(mTitles.get(position),lists.get(position).getTypeId());
                case 3:
                    return ShortEssayFragment.newInstance(mTitles.get(position),lists.get(position).getTypeId());
                case 4:
                    return SoftWordFragment.newInstance(mTitles.get(position),lists.get(position).getTypeId());
                case 5:
                    return ChildSoundFragment.newInstance(mTitles.get(position),lists.get(position).getTypeId());*/
                case 0:
                    return PinYinLearningFragment.newInstance(mTitles.get(position),lists.get(position).getTypeId());
                case 2:
                    return WordFollowFragment.newInstance(mTitles.get(position),lists.get(position).getTypeId());
                case 3:
                    return ShortEssayFragment.newInstance(mTitles.get(position),lists.get(position).getTypeId());
                    // 1 4 5 汉字学习 轻声字 儿化音 页面完全相同
                case 1:
                    return HanZiLearningFragment.newInstance(mTitles.get(position),lists.get(position).getTypeId());
                case 4:
                    return HanZiLearningFragment.newInstance(mTitles.get(position),lists.get(position).getTypeId());
                case 5:
                    return HanZiLearningFragment.newInstance(mTitles.get(position),lists.get(position).getTypeId());
            }
        }else if (from == 2){
            return ExerciseFragment.newInstance(mTitles.get(position),lists.get(position).getTypeId());
        }
        return null;
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
