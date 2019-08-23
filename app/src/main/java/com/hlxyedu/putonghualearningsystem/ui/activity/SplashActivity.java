package com.hlxyedu.putonghualearningsystem.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootActivity;
import com.hlxyedu.putonghualearningsystem.model.bean.OnLineLearnTitleVO;
import com.hlxyedu.putonghualearningsystem.ui.contract.SplashContract;
import com.hlxyedu.putonghualearningsystem.ui.main.activity.MainActivity;
import com.hlxyedu.putonghualearningsystem.ui.presenter.SplashPresenter;

import java.util.List;

/**
 * Created by zhangguihua
 */
public class SplashActivity extends RootActivity<SplashPresenter> implements SplashContract.View {

    private List<OnLineLearnTitleVO> lists;
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
                startActivity(MainActivity.newInstance(SplashActivity.this,lists));
                finish();
            }
        });
        mPresenter.getTopTitle();
    }

    @Override
    public void onTopSuccess(List<OnLineLearnTitleVO> onLineLearnTitleVOS) {
        lists = onLineLearnTitleVOS;
    }

    @Override
    public void responeError(String errorMsg) {

    }
}