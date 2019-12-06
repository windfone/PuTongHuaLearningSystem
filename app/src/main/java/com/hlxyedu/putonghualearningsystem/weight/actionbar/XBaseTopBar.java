package com.hlxyedu.putonghualearningsystem.weight.actionbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.hlxyedu.putonghualearningsystem.R;

/**
 * @FileName: XBaseTopBar
 * @author: skyworth
 * @data: 2016-10-18
 */
public class XBaseTopBar extends RelativeLayout implements View.OnClickListener{

    //根布局
    protected RelativeLayout rlayout_topbar_root;

    //左侧组件
    protected ImageView img_topbar_left;

    //右侧组件
    protected ViewStub stub_right;

    private ImageView right_iv;

    //要加载的组件id
    protected int rightResId;

    //是否显示右侧组件
    protected boolean isShowRight;

    //是否显示左侧组件
    protected boolean isShowLeft;

    //中间标题
    protected String middleText;
    protected TextView txt_topbar_name;

    protected XBaseTopBarImp xBaseTopBarImp;

    //通过ViewStub加载的组件
    protected View stubView;

    private LinearLayout middle_layout;

    private RightListener rightListener;

    public void setRightListener(RightListener listener){
        this.rightListener = listener;
    }

    public void setxBaseTopBarImp(XBaseTopBarImp xBaseTopBarImp) {
        this.xBaseTopBarImp = xBaseTopBarImp;
    }

    public XBaseTopBar(Context context) {
        super(context);
    }

    public XBaseTopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.XBaseTopBar);

        middleText = typedArray.getString(R.styleable.XBaseTopBar_middleText_x);
        //判断是否显示右侧组件
        isShowRight = typedArray.getBoolean(R.styleable.XBaseTopBar_isShowRight_x,false);

        if(isShowRight){
            //获取右侧组件的resourceId
            rightResId = typedArray.getResourceId(R.styleable.XBaseTopBar_rightView,R.layout.layout_button);
        }

        //判断是否显示左侧组件
        isShowLeft = typedArray.getBoolean(R.styleable.XBaseTopBar_isShowLeft_x, true);

        initView(context);

        typedArray.recycle();

    }

    private void initView(Context context){

        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.x_base_topbar, this, true);

        txt_topbar_name = (TextView) this.findViewById(R.id.txt_topbar_name);
        img_topbar_left = (ImageView) this.findViewById(R.id.img_topbar_left);
        stub_right = (ViewStub) this.findViewById(R.id.stub_right);
        rlayout_topbar_root = (RelativeLayout) this.findViewById(R.id.rlayout_topbar_root);
        img_topbar_left.setOnClickListener(this);
        stub_right.setOnClickListener(this);
        txt_topbar_name.setText(middleText);

        //判断是否显示左侧组件
        if (isShowLeft == false) {
            img_topbar_left.setVisibility(GONE);
        }else {
            img_topbar_left.setVisibility(VISIBLE);
        }

        //当确定显示右侧组件并且写入了布局id时加载
        if(isShowRight && rightResId!=0){
            stub_right.setLayoutResource(rightResId);
            stubView = stub_right.inflate();
            stub_right.setVisibility(VISIBLE);
            stubView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    xBaseTopBarImp.right();
                }
            });
        }

        if (rightResId != 0){
            right_iv = (ImageView) this.findViewById(R.id.right_iv);
            right_iv.setOnClickListener(this);
        }

    }

    public void setMiddleText(String s){
        txt_topbar_name.setText(s);
    }

    public void setRightIv(Drawable drawable){
        if (right_iv != null){
            right_iv.setImageDrawable(drawable);
        }
    }

    //获取通过ViewStub加载的组件view
    public View getStubView(){
        if(isShowRight){
            return stubView;
        }
        return null;
    }

    @Override
    public void onClick(View v) {

        if(ObjectUtils.isEmpty(xBaseTopBarImp ))
            return;

        switch (v.getId()){
            case R.id.img_topbar_left:
                xBaseTopBarImp.left();
                break;
            case R.id.stub_right:
                xBaseTopBarImp.right();
                break;
            case R.id.right_iv:
                rightListener.right();
                xBaseTopBarImp.right();
                break;
        }
    }


    public void setLeftImg(Drawable drawable) {
        img_topbar_left.setImageDrawable(drawable);
    }

    public interface RightListener{
        void right();
    }
}
