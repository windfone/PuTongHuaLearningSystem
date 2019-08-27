package com.hlxyedu.putonghualearningsystem.ui.splash.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootActivity;
import com.hlxyedu.putonghualearningsystem.ui.login.activity.LoginActivity;
import com.hlxyedu.putonghualearningsystem.ui.main.activity.MainActivity;
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
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }

        new Handler().postDelayed(() -> {

            if (mPresenter.isLogin()) {
                startActivity(MainActivity.newInstance(getBaseContext()));
            } else {
                startActivity(LoginActivity.newInstance(getBaseContext()));
            }
            finish();

        },1000);

        /*findViewById(R.id.jump_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(LoginActivity.newInstance(SplashActivity.this));
                finish();
            }
        });*/
    }

    @Override
    public void responeError(String errorMsg) {

    }
}