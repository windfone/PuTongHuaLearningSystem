package com.hlxyedu.putonghualearningsystem.ui.personal.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootActivity;
import com.hlxyedu.putonghualearningsystem.ui.personal.contract.FeedBackContract;
import com.hlxyedu.putonghualearningsystem.ui.personal.presenter.FeedBackPresenter;
import com.hlxyedu.putonghualearningsystem.weight.actionbar.XBaseTopBar;
import com.hlxyedu.putonghualearningsystem.weight.actionbar.XBaseTopBarImp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhangguihua
 * 意见反馈
 */
public class FeedBackActivity extends RootActivity<FeedBackPresenter> implements FeedBackContract.View, XBaseTopBarImp {

    @BindView(R.id.xbase_topbar)
    XBaseTopBar xbase_topbar;
    @BindView(R.id.commit_btn)
    Button commit_btn;

    /**
     * 打开新Activity
     *
     * @param context
     * @return
     */
    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, FeedBackActivity.class);
        return intent;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_feed_back;
    }

    @Override
    protected void initEventAndData() {
        xbase_topbar.setxBaseTopBarImp(this);
    }

    @Override
    public void responeError(String errorMsg) {

    }

    @OnClick(R.id.commit_btn)
    public void onViewClicked() {

    }

    @Override
    public void left() {
        finish();
    }

    @Override
    public void right() {

    }

}