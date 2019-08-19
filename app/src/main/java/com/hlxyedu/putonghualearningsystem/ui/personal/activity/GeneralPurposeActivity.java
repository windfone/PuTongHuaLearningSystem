package com.hlxyedu.putonghualearningsystem.ui.personal.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootActivity;
import com.hlxyedu.putonghualearningsystem.ui.personal.contract.GeneralPurposeContract;
import com.hlxyedu.putonghualearningsystem.ui.personal.presenter.GeneralPurposePresenter;
import com.hlxyedu.putonghualearningsystem.weight.actionbar.XBaseTopBar;
import com.hlxyedu.putonghualearningsystem.weight.actionbar.XBaseTopBarImp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhangguihua
 * 通用
 */

public class GeneralPurposeActivity extends RootActivity<GeneralPurposePresenter> implements GeneralPurposeContract.View, XBaseTopBarImp {

    @BindView(R.id.xbase_topbar)
    XBaseTopBar xbase_topbar;
    @BindView(R.id.version_rl)
    RelativeLayout version_rl;
    @BindView(R.id.version_tv)
    TextView version_tv;
    @BindView(R.id.clear_cache_rl)
    RelativeLayout clear_cache_rl;
    @BindView(R.id.ideas_rl)
    RelativeLayout ideas_rl;
    @BindView(R.id.about_us_rl)
    RelativeLayout about_us_rl;
    @BindView(R.id.enter_us_rl)
    RelativeLayout enter_us_rl;

    /**
     * 打开新Activity
     *
     * @param context
     * @return
     */
    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, GeneralPurposeActivity.class);
        return intent;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_general_purpose;
    }

    @Override
    protected void initEventAndData() {
        xbase_topbar.setxBaseTopBarImp(this);
        version_tv.setText("v" + AppUtils.getAppVersionName());
    }

    @Override
    public void responeError(String errorMsg) {

    }

    @OnClick({R.id.version_rl, R.id.clear_cache_rl, R.id.ideas_rl, R.id.about_us_rl, R.id.enter_us_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.version_rl:
                break;
            case R.id.clear_cache_rl:
                break;
            case R.id.ideas_rl:
                startActivity(FeedBackActivity.newInstance(this));
                break;
            case R.id.about_us_rl:
                startActivity(AboutUsActivity.newInstance(this));
                break;
            case R.id.enter_us_rl:
                startActivity(EnterUsActivity.newInstance(this));
                break;
        }
    }

    @Override
    public void left() {
        finish();
    }

    @Override
    public void right() {

    }
}