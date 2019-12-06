package com.hlxyedu.putonghualearningsystem.ui.home.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootActivity;
import com.hlxyedu.putonghualearningsystem.ui.home.contract.QueryContract;
import com.hlxyedu.putonghualearningsystem.ui.home.presenter.QueryPresenter;
import com.hlxyedu.putonghualearningsystem.weight.actionbar.XBaseTopBar;
import com.hlxyedu.putonghualearningsystem.weight.actionbar.XBaseTopBarImp;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhangguihua
 */
public class QueryActivity extends RootActivity<QueryPresenter> implements QueryContract.View, XBaseTopBarImp {

    @BindView(R.id.xbase_topbar)
    XBaseTopBar xbase_topbar;
    @BindView(R.id.input_name_edit)
    EditText input_name_edit;
    @BindView(R.id.input_ID_edit)
    EditText input_ID_edit;
    @BindView(R.id.input_ticket_edit)
    EditText input_ticket_edit;
    @BindView(R.id.query_btn)
    Button query_btn;
    @BindView(R.id.QR_code_rl)
    RelativeLayout QR_code_rl;

    private int from;

    /**
     * 打开新Activity
     *
     * @param context
     * @return
     */
    public static Intent newInstance(Context context, int from) {
        Intent intent = new Intent(context, QueryActivity.class);
        intent.putExtra("from", from);
        return intent;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_query;
    }

    @Override
    protected void initEventAndData() {
        xbase_topbar.setxBaseTopBarImp(this);
        getIntentData();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        from = intent.getIntExtra("from", 1);
        switch (from) {
            case 1:
                xbase_topbar.setMiddleText(getResources().getString(R.string.registration_inquiry));
                break;
            case 2:
                xbase_topbar.setMiddleText(getResources().getString(R.string.admission_ticket_inquiry));
                break;
            case 3:
                xbase_topbar.setMiddleText(getResources().getString(R.string.result_inquiry));
                input_ticket_edit.setVisibility(View.VISIBLE);
                break;
            case 7:
                xbase_topbar.setMiddleText(getResources().getString(R.string.certificate_verification));
                input_ticket_edit.setVisibility(View.VISIBLE);
                QR_code_rl.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void responeError(String errorMsg) {

    }

    @OnClick({R.id.QR_code_rl, R.id.query_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.QR_code_rl:
                break;
            case R.id.query_btn:
                switch (from) {
                    case 1:
                        startActivity(RegistrationQueryActivity.newInstance(this));
                        break;
                    case 2:
                        startActivity(TicketInquiryActivity.newInstance(this));
                        break;
                    case 3:
                        startActivity(GradeQueryActivity.newInstance(this));
                        break;
                    case 7:
                        startActivity(CertificateVerificationQueryActivity.newInstance(this));
                        break;
                }
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