package com.hlxyedu.putonghualearningsystem.ui.login.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootActivity;
import com.hlxyedu.putonghualearningsystem.model.bean.UserVO;
import com.hlxyedu.putonghualearningsystem.ui.login.contract.LoginContract;
import com.hlxyedu.putonghualearningsystem.ui.login.presenter.LoginPresenter;
import com.hlxyedu.putonghualearningsystem.ui.main.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhangguihua
 */
public class LoginActivity extends RootActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.user_name_edit)
    EditText user_name_edit;
    @BindView(R.id.code_edit)
    EditText code_edit;
    @BindView(R.id.login_rl)
    RelativeLayout login_rl;
    @BindView(R.id.code_tv)
    TextView code_tv;
    @BindView(R.id.wechat_login_iv)
    ImageView wechat_login_iv;
    @BindView(R.id.qq_login_iv)
    ImageView qq_login_iv;
    @BindView(R.id.alipay_login_iv)
    ImageView alipay_login_iv;

    /**
     * 打开新Activity
     *
     * @param context
     * @return
     */
    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void responeError(String errorMsg) {

    }

    @Override
    public void loginSuccess(UserVO userVO) {
        ToastUtils.showShort("登录成功");
        startActivity(MainActivity.newInstance(LoginActivity.this));
        finish();
    }

    @Override
    public void closeLogin() {
        finish();
    }

    @OnClick(R.id.login_rl)
    public void onViewClicked() {

    }

    @OnClick({R.id.login_rl,R.id.wechat_login_iv, R.id.qq_login_iv, R.id.alipay_login_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_rl:
                break;
            case R.id.wechat_login_iv:
                break;
            case R.id.qq_login_iv:
                break;
            case R.id.alipay_login_iv:
                break;
        }
    }

}