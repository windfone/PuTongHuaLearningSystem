package com.hlxyedu.putonghualearningsystem.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

public class MyFragmentPagerAdapter <E extends Fragment> extends FragmentPagerAdapter {

    // 要显示的页面
    private List<E> frgList;

    public MyFragmentPagerAdapter(FragmentManager fm, List<E> frgList) {
        super(fm);
        this.frgList = frgList;
    }

    @Override
    public E getItem(int arg0) {
        return frgList.get(arg0);
    }

    @Override
    public int getCount() {
        return frgList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
