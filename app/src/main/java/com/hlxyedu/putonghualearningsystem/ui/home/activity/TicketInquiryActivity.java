package com.hlxyedu.putonghualearningsystem.ui.home.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootActivity;
import com.hlxyedu.putonghualearningsystem.ui.home.contract.TicketInquiryContract;
import com.hlxyedu.putonghualearningsystem.ui.home.presenter.TicketInquiryPresenter;
import com.hlxyedu.putonghualearningsystem.weight.actionbar.XBaseTopBar;
import com.hlxyedu.putonghualearningsystem.weight.actionbar.XBaseTopBarImp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhangguihua
 */
public class TicketInquiryActivity extends RootActivity<TicketInquiryPresenter> implements TicketInquiryContract.View, XBaseTopBarImp {

    @BindView(R.id.xbase_topbar)
    XBaseTopBar xbase_topbar;
    @BindView(R.id.candidate_name_tv)
    TextView candidate_name_tv;
    @BindView(R.id.ID_number_tv)
    TextView ID_number_tv;
    @BindView(R.id.sex_tv)
    TextView sex_tv;
    @BindView(R.id.nation_tv)
    TextView nation_tv;
    @BindView(R.id.job_tv)
    TextView job_tv;
    @BindView(R.id.unit_tv)
    TextView unit_tv;
    @BindView(R.id.ticket_tv)
    TextView ticket_tv;
    @BindView(R.id.level_tv)
    TextView level_tv;
    @BindView(R.id.photo_iv)
    ImageView photo_iv;
    @BindView(R.id.save_btn)
    Button save_btn;

    /**
     * 打开新Activity
     *
     * @param context
     * @return
     */
    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, TicketInquiryActivity.class);
        return intent;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_ticket_inquiry;
    }

    @Override
    protected void initEventAndData() {
        xbase_topbar.setxBaseTopBarImp(this);
    }

    @Override
    public void responeError(String errorMsg) {

    }

    @OnClick(R.id.save_btn)
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