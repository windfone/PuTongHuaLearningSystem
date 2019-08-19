package com.hlxyedu.putonghualearningsystem.ui.personal.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootActivity;
import com.hlxyedu.putonghualearningsystem.ui.personal.contract.PersonalInfoContract;
import com.hlxyedu.putonghualearningsystem.ui.personal.presenter.PersonalInfoPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhanggihua
 */
public class PersonalInfoActivity extends RootActivity<PersonalInfoPresenter> implements PersonalInfoContract.View {

    @BindView(R.id.back_iv)
    ImageView back_iv;
    @BindView(R.id.headportrait_iv)
    ImageView headportrait_iv;
    @BindView(R.id.input_name_edit)
    EditText input_name_edit;
    @BindView(R.id.man_rb)
    RadioButton man_rb;
    @BindView(R.id.woman_rb)
    RadioButton woman_rb;
    @BindView(R.id.date_of_birth_tv)
    TextView date_of_birth_tv;
    @BindView(R.id.district_tv)
    TextView district_tv;
    @BindView(R.id.area_edit)
    EditText area_edit;
    @BindView(R.id.affiliation_edit)
    EditText affiliation_edit;
    @BindView(R.id.save_btn)
    Button save_btn;

    /**
     * 打开新Activity
     *
     * @param context
     * @return
     */
    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, PersonalInfoActivity.class);
        return intent;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_personal_info;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void responeError(String errorMsg) {

    }

    @OnClick({R.id.back_iv, R.id.headportrait_iv, R.id.man_rb, R.id.woman_rb, R.id.date_of_birth_tv, R.id.district_tv, R.id.save_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_iv:
                finish();
                break;
            case R.id.headportrait_iv:
                break;
            case R.id.man_rb:
                break;
            case R.id.woman_rb:
                break;
            case R.id.date_of_birth_tv:
                break;
            case R.id.district_tv:
                break;
            case R.id.save_btn:
                break;
        }
    }
}