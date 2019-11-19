package com.hlxyedu.putonghualearningsystem.weight.bottombar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.hlxyedu.putonghualearningsystem.R;

import java.util.ArrayList;

/**
 * 作者：weidingqiang on 2018/12/15 10:32
 * 邮箱：weidingqiang@163.com
 * 底部导航
 */

public class BottomBar extends LinearLayout implements View.OnClickListener{

    private BottomBarItem bottom_bar_home;

    private BottomBarItem bottom_bar_online_learning;

    private BottomBarItem bottom_bar_exam_center;

    private BottomBarItem bottom_bar_famous_classroom;

    private BottomBarItem bottom_bar_personal_center;

    private int position;

    private OnTabSelectedListener mListener;

    private ArrayList<BottomBarItem> arrayList;

    public void setOnTabSelectedListener(OnTabSelectedListener onTabSelectedListener) {
        mListener = onTabSelectedListener;
    }

    public BottomBar(Context context) {
        this(context, null);
    }

    public BottomBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.bottom_bar_bg, this, true);

//        bottom_bar_home = (BottomBarItem) this.findViewById(R.id.bottom_bar_home);
        bottom_bar_online_learning = (BottomBarItem) this.findViewById(R.id.bottom_bar_online_learning);
        bottom_bar_exam_center = (BottomBarItem) this.findViewById(R.id.bottom_bar_exam_center);
        bottom_bar_famous_classroom = (BottomBarItem) this.findViewById(R.id.bottom_bar_famous_classroom);
        bottom_bar_personal_center = (BottomBarItem) this.findViewById(R.id.bottom_bar_personal_center);

        arrayList = new ArrayList<>();
//        arrayList.add(bottom_bar_home);
        arrayList.add(bottom_bar_online_learning);
        arrayList.add(bottom_bar_exam_center);
        arrayList.add(bottom_bar_famous_classroom);
        arrayList.add(bottom_bar_personal_center);


//        bottom_bar_home.setOnClickListener(this);
        bottom_bar_online_learning.setOnClickListener(this);
        bottom_bar_exam_center.setOnClickListener(this);
        bottom_bar_famous_classroom.setOnClickListener(this);
        bottom_bar_personal_center.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (mListener == null) return;

        for (int i=0;i<arrayList.size();i++){
            BottomBarItem bottomBarItem = (BottomBarItem)arrayList.get(i);
            if(bottomBarItem == (BottomBarItem)v){
                if(position == i) return;
                mListener.onTabSelected(i,position);
                position = i;
                bottomBarItem.setSelected(true);
            }else {
                bottomBarItem.setSelected(false);
            }
        }
    }

    public void setCurrentItem(int position) {
        this.position = position;
        arrayList.get(position).setSelected(true);
    }

    public interface OnTabSelectedListener {
        void onTabSelected(int position, int prePosition);
    }

    public void setImMesCount(int count){
//        bottom_bar_im.setUnMsgNum(count);
    }

}
