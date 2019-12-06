package com.hlxyedu.putonghualearningsystem.ui.home.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootActivity;
import com.hlxyedu.putonghualearningsystem.ui.home.contract.RegistrationQueryContract;
import com.hlxyedu.putonghualearningsystem.ui.home.presenter.RegistrationQueryPresenter;
import com.hlxyedu.putonghualearningsystem.weight.actionbar.XBaseTopBar;
import com.hlxyedu.putonghualearningsystem.weight.actionbar.XBaseTopBarImp;

import butterknife.BindView;

/**
 * Created by zhangguihua
 */
public class RegistrationQueryActivity extends RootActivity<RegistrationQueryPresenter> implements RegistrationQueryContract.View, XBaseTopBarImp {

    @BindView(R.id.xbase_topbar)
    XBaseTopBar xbase_topbar;
    @BindView(R.id.candidate_name_tv)
    TextView candidate_name_tv;
    @BindView(R.id.ID_number_tv)
    TextView ID_number_tv;
    @BindView(R.id.registration_status_tv)
    TextView registration_status_tv;
    @BindView(R.id.registration_outlets_tv)
    TextView registration_outlets_tv;
    @BindView(R.id.payment_details_tv)
    TextView payment_details_tv;

    /**
     * 打开新Activity
     *
     * @param context
     * @return
     */
    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, RegistrationQueryActivity.class);
        return intent;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_registration_query;
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