package com.hlxyedu.putonghualearningsystem.weight.bottombar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hlxyedu.putonghualearningsystem.R;


/**
 * 作者：weidingqiang on 2018/12/15 10:55
 * 邮箱：weidingqiang@163.com
 */

public class BottomBarItem  extends LinearLayout {
    private ImageView mIcon;
    private TextView mTvTitle;
    private Context mContext;
    private int mTabPosition = -1;

    private Drawable sicon;
    private Drawable icon;
    private String text;

    private TextView unMsg_tv;

    public BottomBarItem(Context context) {
        this(context,null);
    }

    public BottomBarItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BottomBarItem);
        sicon = typedArray.getDrawable(R.styleable.BottomBarItem_icon_Selected);
        icon = typedArray.getDrawable(R.styleable.BottomBarItem_icon_Normal);
        text = typedArray.getString(R.styleable.BottomBarItem_icon_Text);

        typedArray.recycle();

        init(context);
    }

    private void init(Context context) {
        mContext = context;

        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.bottom_bar_item_bg, this, true);

        mIcon = (ImageView) this.findViewById(R.id.mIcon);
        mIcon.setImageDrawable(icon);
        mTvTitle = (TextView) this.findViewById(R.id.mTvTitle);
        mTvTitle.setText(text);

        unMsg_tv = (TextView) this.findViewById(R.id.unMsg_tv);

        setUnMsgNum(0);
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if (selected) {
//            mIcon.setImageDrawable(sicon);

            mTvTitle.setTextColor(ContextCompat.getColor(mContext, R.color.blueEDE));
        } else {
//            mIcon.setImageDrawable(icon);
            mTvTitle.setTextColor(ContextCompat.getColor(mContext, R.color.blackE3E));
        }
    }

    public void setTabPosition(int position) {
        mTabPosition = position;
        if (position == 0) {
            setSelected(true);
        }
        else {
            setSelected(false);
        }
    }

    public int getTabPosition() {
        return mTabPosition;
    }

    public void setUnMsgNum(int count){
        if(count == 0){
            unMsg_tv.setVisibility(GONE);
        }else {
            unMsg_tv.setVisibility(VISIBLE);
        }
        unMsg_tv.setText(count+"");
    }

    public void setContentAndDrawable(Drawable drawable,String text){
        mIcon.setImageDrawable(drawable);
        mTvTitle.setText(text);
    }
}
