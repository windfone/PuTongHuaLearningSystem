package com.hlxyedu.putonghualearningsystem.ui.home.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootActivity;
import com.hlxyedu.putonghualearningsystem.ui.home.contract.CertificateVerificationQueryContract;
import com.hlxyedu.putonghualearningsystem.ui.home.presenter.CertificateVerificationQueryPresenter;
import com.hlxyedu.putonghualearningsystem.weight.actionbar.XBaseTopBar;
import com.hlxyedu.putonghualearningsystem.weight.actionbar.XBaseTopBarImp;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhangguihua
 */
public class CertificateVerificationQueryActivity extends RootActivity<CertificateVerificationQueryPresenter>
        implements CertificateVerificationQueryContract.View, XBaseTopBarImp {

    @BindView(R.id.xbase_topbar)
    XBaseTopBar xbase_topbar;
    @BindView(R.id.name_tv)
    TextView name_tv;
    @BindView(R.id.candidate_name_tv)
    TextView candidate_name_tv;
    @BindView(R.id.jibie_tv)
    TextView jibie_tv;
    @BindView(R.id.level_tv)
    TextView level_tv;
    @BindView(R.id.zp_tv)
    TextView zp_tv;
    @BindView(R.id.photo_iv)
    ImageView photo_iv;
    @BindView(R.id.zhunkaozhenghao_tv)
    TextView zhunkaozhenghao_tv;
    @BindView(R.id.ticket_tv)
    TextView ticket_tv;
    @BindView(R.id.save_btn)
    Button save_btn;

    /**
     * 打开新Activity
     *
     * @param context
     * @return
     */
    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, CertificateVerificationQueryActivity.class);
        return intent;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_certificate_verification;
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