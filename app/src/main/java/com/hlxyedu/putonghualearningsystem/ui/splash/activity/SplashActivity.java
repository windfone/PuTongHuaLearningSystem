package com.hlxyedu.putonghualearningsystem.ui.splash.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootActivity;
import com.hlxyedu.putonghualearningsystem.ui.login.activity.LoginActivity;
import com.hlxyedu.putonghualearningsystem.ui.splash.contract.SplashContract;
import com.hlxyedu.putonghualearningsystem.ui.splash.presenter.SplashPresenter;

/**
 * Created by zhangguihua
 */
public class SplashActivity extends RootActivity<SplashPresenter> implements SplashContract.View {

    /**
     * 打开新Activity
     *
     * @param context
     * @return
     */
    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        return intent;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initEventAndData() {

        findViewById(R.id.jump_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(LoginActivity.newInstance(SplashActivity.this));
                finish();
            }
        });
    }

    @Override
    public void responeError(String errorMsg) {

    }
}