package com.hlxyedu.putonghualearningsystem.ui.personal.activity;

import android.content.Context;
import android.content.Intent;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootActivity;
import com.hlxyedu.putonghualearningsystem.ui.login.activity.LoginActivity;
import com.hlxyedu.putonghualearningsystem.ui.main.activity.MainActivity;
import com.hlxyedu.putonghualearningsystem.ui.personal.contract.GeneralPurposeContract;
import com.hlxyedu.putonghualearningsystem.ui.personal.presenter.GeneralPurposePresenter;
import com.hlxyedu.putonghualearningsystem.weight.actionbar.XBaseTopBar;
import com.hlxyedu.putonghualearningsystem.weight.actionbar.XBaseTopBarImp;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.skyworth.rxqwelibrary.managers.AppManagers;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhangguihua
 * 通用
 */

public class GeneralPurposeActivity extends RootActivity<GeneralPurposePresenter> implements GeneralPurposeContract.View, XBaseTopBarImp {

    @BindView(R.id.xbase_topbar)
    XBaseTopBar xbase_topbar;
    @BindView(R.id.version_rl)
    RelativeLayout version_rl;
    @BindView(R.id.version_tv)
    TextView version_tv;
    @BindView(R.id.clear_cache_rl)
    RelativeLayout clear_cache_rl;
    @BindView(R.id.ideas_rl)
    RelativeLayout ideas_rl;
    @BindView(R.id.about_us_rl)
    RelativeLayout about_us_rl;
    @BindView(R.id.enter_us_rl)
    RelativeLayout enter_us_rl;
    @BindView(R.id.exit_btn)
    Button exit_btn;

    /**
     * 打开新Activity
     *
     * @param context
     * @return
     */
    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, GeneralPurposeActivity.class);
        return intent;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_general_purpose;
    }

    @Override
    protected void initEventAndData() {
        xbase_topbar.setxBaseTopBarImp(this);
        version_tv.setText("v" + AppUtils.getAppVersionName());

        if (!mPresenter.isLogin()) exit_btn.setVisibility(View.GONE);

    }

    @Override
    public void responeError(String errorMsg) {

    }

    @OnClick({R.id.version_rl, R.id.clear_cache_rl, R.id.ideas_rl, R.id.about_us_rl, R.id.enter_us_rl, R.id.exit_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.version_rl:
                break;
            case R.id.clear_cache_rl:
                break;
            case R.id.ideas_rl:
                startActivity(FeedBackActivity.newInstance(this));
                break;
            case R.id.about_us_rl:
                startActivity(AboutUsActivity.newInstance(this));
                break;
            case R.id.enter_us_rl:
                startActivity(EnterUsActivity.newInstance(this));
                break;
            case R.id.exit_btn:
                    WindowManager windowManager = (WindowManager)this
                            .getSystemService(Context.WINDOW_SERVICE);
                    Display display = windowManager.getDefaultDisplay();

                    DialogPlus logoutDialog = DialogPlus.newDialog(this)
                            .setGravity(Gravity.CENTER)
                            .setContentHolder(new ViewHolder(R.layout.dialog_logout))
//                        .setContentBackgroundResource(R.drawable.dialog_write_corner_bg)
                            .setContentWidth((int) (display
                                    .getWidth() * 0.8))
                            .setContentHeight(LinearLayout.LayoutParams.WRAP_CONTENT)
                            .setCancelable(true)//设置不可取消   可以取消
                            .setOnClickListener((dialog, view1) -> {
                                switch (view1.getId()) {
                                    case R.id.btn_neg:
                                        dialog.dismiss();
                                        break;
                                    case R.id.btn_pos:
                                        mPresenter.clearLoginInfo();
                                        ToastUtils.showShort("已退出登录");
                                        startActivity(LoginActivity.newInstance(this));
                                        AppManagers.getInstance().killClassActivity(MainActivity.class);
                                        finish();
                                        break;
                                }
                            }).create();
                    logoutDialog.show();
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