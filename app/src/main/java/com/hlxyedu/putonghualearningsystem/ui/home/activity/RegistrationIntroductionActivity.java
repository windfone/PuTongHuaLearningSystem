package com.hlxyedu.putonghualearningsystem.ui.home.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootActivity;
import com.hlxyedu.putonghualearningsystem.ui.home.contract.RegistrationIntroductionContract;
import com.hlxyedu.putonghualearningsystem.ui.home.presenter.RegistrationIntroductionPresenter;
import com.hlxyedu.putonghualearningsystem.weight.actionbar.XBaseTopBar;
import com.hlxyedu.putonghualearningsystem.weight.actionbar.XBaseTopBarImp;

import butterknife.BindView;

/**
 * Created by zhangguihua
 */
public class RegistrationIntroductionActivity extends RootActivity<RegistrationIntroductionPresenter> implements RegistrationIntroductionContract.View, XBaseTopBarImp {

    @BindView(R.id.xbase_topbar)
    XBaseTopBar xbase_topbar;
    @BindView(R.id.content_tv)
    TextView content_tv;

    /**
     * 打开新Activity
     *
     * @param context
     * @return
     */
    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, RegistrationIntroductionActivity.class);
        return intent;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_registration_introduction;
    }

    @Override
    protected void initEventAndData() {
        xbase_topbar.setxBaseTopBarImp(this);
        String[] city = getResources().getStringArray(R.array.registration_introduction_content);
        String strings = "";
        for (int i = 0; i < city.length; i++) {
            strings += city[i] + "\n\n";
        }
        content_tv.setText(strings);
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