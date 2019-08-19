package com.skyworth.rxqwelibrary.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by codeest on 16/9/27.
 */

public class ProgressImageView extends AppCompatImageView {

    public ProgressImageView(Context context) {
        super(context);
    }

    public ProgressImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void start() {
        AnimationDrawable animationDrawable = (AnimationDrawable) getDrawable();
        //开始动画
        animationDrawable.start();
    }

    public void stop() {
        AnimationDrawable animationDrawable = (AnimationDrawable) getDrawable();
        //停止动画
        animationDrawable.stop();
    }
}
