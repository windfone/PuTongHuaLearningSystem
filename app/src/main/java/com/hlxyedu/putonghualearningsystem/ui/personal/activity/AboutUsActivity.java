package com.hlxyedu.putonghualearningsystem.ui.personal.activity;

import android.content.Context;
import android.content.Intent;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootActivity;
import com.hlxyedu.putonghualearningsystem.ui.personal.contract.AboutUsContract;
import com.hlxyedu.putonghualearningsystem.ui.personal.presenter.AboutUsPresenter;
import com.hlxyedu.putonghualearningsystem.weight.actionbar.XBaseTopBar;
import com.hlxyedu.putonghualearningsystem.weight.actionbar.XBaseTopBarImp;

import butterknife.BindView;

/**
 * Created by zhangguihua
 */
public class AboutUsActivity extends RootActivity<AboutUsPresenter> implements AboutUsContract.View, XBaseTopBarImp {

    @BindView(R.id.xbase_topbar)
    XBaseTopBar xbase_topbar;

    /**
     * 打开新Activity
     *
     * @param context
     * @return
     */
    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, AboutUsActivity.class);
        return intent;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void initEventAndData() {
        xbase_topbar.setxBaseTopBarImp(this);
    }

    @Override
    public void responeError(String errorMsg) {

    }

    @Override
    public void left() {
        finish();
    }

    @Override
    public void right() {

    }
}