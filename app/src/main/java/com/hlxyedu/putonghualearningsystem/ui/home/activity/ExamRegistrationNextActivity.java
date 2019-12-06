package com.hlxyedu.putonghualearningsystem.ui.home.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootActivity;
import com.hlxyedu.putonghualearningsystem.ui.home.contract.ExamRegistrationNextContract;
import com.hlxyedu.putonghualearningsystem.ui.home.presenter.ExamRegistrationNextPresenter;
import com.hlxyedu.putonghualearningsystem.weight.actionbar.XBaseTopBar;
import com.hlxyedu.putonghualearningsystem.weight.actionbar.XBaseTopBarImp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhangguihua
 */
public class ExamRegistrationNextActivity extends RootActivity<ExamRegistrationNextPresenter> implements ExamRegistrationNextContract.View, XBaseTopBarImp {

    @BindView(R.id.xbase_topbar)
    XBaseTopBar xbase_topbar;
    @BindView(R.id.input_faculty_edit)
    EditText input_faculty_edit;
    @BindView(R.id.input_class_edit)
    EditText input_class_edit;
    @BindView(R.id.input_mailaddress_edit)
    EditText input_mailaddress_edit;
    @BindView(R.id.input_phone_edit)
    EditText input_phone_edit;
    @BindView(R.id.query_btn)
    Button query_btn;

    /**
     * 打开新Activity
     *
     * @param context
     * @return
     */
    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, ExamRegistrationNextActivity.class);
        return intent;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_exam_registration_next;
    }

    @Override
    protected void initEventAndData() {
        xbase_topbar.setxBaseTopBarImp(this);
        xbase_topbar.setRightIv(getResources().getDrawable(R.drawable.icon_explain));
        xbase_topbar.setRightListener(() -> {
            startActivity(RegistrationIntroductionActivity.newInstance(this));
        });
    }

    @Override
    public void responeError(String errorMsg) {

    }

    @OnClick(R.id.query_btn)
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