package com.hlxyedu.putonghualearningsystem.weight.bottombar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.hlxyedu.putonghualearningsystem.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：weidingqiang on 2018/12/15 10:32
 * 邮箱：weidingqiang@163.com
 * 底部导航
 */

public class BottomBar extends LinearLayout implements View.OnClickListener{

    private LinearLayout containers_ll;

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

        containers_ll = this.findViewById(R.id.containers_ll);

        arrayList = new ArrayList<>();
    }

    public void initBottomBar(int screenWidth,Context context,List<String> navigations,List<Integer> drawables){
        if (navigations.size() != drawables.size()) {
            ToastUtils.showShort("导航栏设置错误");
            return;
        }

        BottomBarItem bottomBarItem;
        for (int i = 0; i < navigations.size(); i++) {
            //循环创建底部标签
            bottomBarItem = new BottomBarItem(context);
            bottomBarItem.setContentAndDrawable(getResources().getDrawable(drawables.get(i)),navigations.get(i));

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(screenWidth/navigations.size(), ViewGroup.LayoutParams.MATCH_PARENT);
            bottomBarItem.setOnClickListener(this);
            containers_ll.addView(bottomBarItem, layoutParams);
            arrayList.add(bottomBarItem);
        }
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

    public void clickWhichItem(int position){
          onClick(arrayList.get(position));
    }

    public interface OnTabSelectedListener {
        void onTabSelected(int position, int prePosition);
    }

    public void setImMesCount(int count){
//        bottom_bar_im.setUnMsgNum(count);
    }

}
