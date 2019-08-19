package com.hlxyedu.putonghualearningsystem.ui.personal.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootActivity;
import com.hlxyedu.putonghualearningsystem.ui.personal.contract.PersonalStatisticsContract;
import com.hlxyedu.putonghualearningsystem.ui.personal.presenter.PersonalStatisticsPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhangguihua
 * 个人统计
 */
public class PersonalStatisticsActivity extends RootActivity<PersonalStatisticsPresenter> implements PersonalStatisticsContract.View {

    @BindView(R.id.back_iv)
    ImageView back_iv;

    /**
     * 打开新Activity
     *
     * @param context
     * @return
     */
    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, PersonalStatisticsActivity.class);
        return intent;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_personal_statistics;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void responeError(String errorMsg) {

    }


    @OnClick(R.id.back_iv)
    public void onViewClicked() {
        finish();
    }
}