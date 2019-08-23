package com.hlxyedu.putonghualearningsystem.weight.topbar;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RxBus;
import com.hlxyedu.putonghualearningsystem.model.event.ActionEvent;

/**
 * 作者：weidingqiang on 2018/1/18 15:39
 * 邮箱：dqwei@iflytek.com
 */

public class XTopBar extends LinearLayout {

    private LinearLayout time_layout;

    private ProgressBar progressBar;

    private TextView time_tv;

    private int total;

    private ValueAnimator v;

    private int cur = 0;

    public XTopBar(Context context) {
        super(context);
    }

    public XTopBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.x_topbar_layout, this, true);

        time_layout = (LinearLayout) this.findViewById(R.id.time_layout);
        progressBar = (ProgressBar) this.findViewById(R.id.progressBar);
        time_tv = (TextView) this.findViewById(R.id.time_tv);
    }

    /**
     * 设置总共的时间 单位s
     * 默认为60s
     *
     * @param total
     */
    public void setTotal(int total) {
        this.total = total;
        progressBar.setMax(total);
        progressBar.setProgress(0);
        initAnimator();
    }

    /**
     * 初始化anim
     */
    private void initAnimator() {
        v = ValueAnimator.ofInt(0, total);
        v.setDuration(total * 1000);
        v.setInterpolator(new LinearInterpolator());
        //监听器 用来刷新progress
        v.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                cur = (int) animation.getAnimatedValue();
                progressBar.setProgress(cur);
                time_tv.setText((total - cur) + "s");
//                //5s 发一个事件 结束录音 听后回答需要
//                if(time_count - cur == 4)
//                {
//                    RxBus.getDefault().post(new ActionEvent(ActionEvent.SHOW_JUMP_4));
//                }
//                //10s 发一个事件 完成录音
//                if(time_count - cur == 60)
//                {
//                    RxBus.getDefault().post(new ActionEvent(ActionEvent.SHOW_JUMP_60));
//                }
//
//                if ((time_count - cur)<= 5) {
//                    time_count_txt.setTextColor(getResources().getColor(R.color.text_red));
//                }
            }
        });
        v.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                hideTimeLayout();
                RxBus.getDefault().post(new ActionEvent(ActionEvent.PROGRESS_FINISH));
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    /**
     * 恢复到初始化状态
     */
    public void init() {
        //即将删除的方法
        if (v != null && !v.equals("") && v.isRunning()) {
            v.cancel();
        }
    }

    /**
     * 开始
     */
    public void start() {
        time_layout.setVisibility(VISIBLE);
        v.start();
    }

    public void hideTimeLayout() {
        time_layout.setVisibility(INVISIBLE);
    }
}
