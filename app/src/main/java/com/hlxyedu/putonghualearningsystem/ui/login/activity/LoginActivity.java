package com.hlxyedu.putonghualearningsystem.ui.login.activity;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootActivity;
import com.hlxyedu.putonghualearningsystem.model.bean.UserVO;
import com.hlxyedu.putonghualearningsystem.ui.login.contract.LoginContract;
import com.hlxyedu.putonghualearningsystem.ui.login.presenter.LoginPresenter;
import com.hlxyedu.putonghualearningsystem.ui.main.activity.MainActivity;

import butterknife.BindView;
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
    @BindView(R.id.login_tv)
    TextView login_tv;
    @BindView(R.id.jump_login_tv)
    TextView jump_login_tv;
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

        setLoginBtnState();
    }

    @Override
    public void responeError(String errorMsg) {
        login_tv.setText(getResources().getString(R.string.login));
        login_rl.setEnabled(true);
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
        String account = user_name_edit.getText().toString().trim();
        String password = code_edit.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            ToastUtils.showShort("账号不为能空");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtils.showShort("密码不为能空");
            return;
        }
        login_tv.setText(getResources().getString(R.string.logining));
        login_rl.setEnabled(false);
        mPresenter.login(account, password);
    }

    @OnClick({R.id.login_rl, R.id.wechat_login_iv, R.id.qq_login_iv, R.id.alipay_login_iv, R.id.jump_login_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.jump_login_tv:
                startActivity(MainActivity.newInstance(this));
                finish();
                break;
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

    private void setLoginBtnState() {
        login_rl.setEnabled(false);
        user_name_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length() > 0 && !StringUtils.isTrimEmpty(code_edit.getText().toString())) {
                    login_rl.setEnabled(true);
                } else {
                    login_rl.setEnabled(false);
                }
            }
        });

        code_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length() > 0 && !StringUtils.isTrimEmpty(user_name_edit.getText().toString())) {
                    login_rl.setEnabled(true);
                } else {
                    login_rl.setEnabled(false);
                }
            }
        });
    }
}