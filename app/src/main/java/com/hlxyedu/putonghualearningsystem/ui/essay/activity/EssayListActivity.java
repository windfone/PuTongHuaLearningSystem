package com.hlxyedu.putonghualearningsystem.ui.essay.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootActivity;
import com.hlxyedu.putonghualearningsystem.model.bean.DataVO;
import com.hlxyedu.putonghualearningsystem.ui.essay.contract.EssayListContract;
import com.hlxyedu.putonghualearningsystem.ui.essay.presenter.EssayListPresenter;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.adapter.ShortEssayAdapter;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;
import java.util.UUID;

import butterknife.BindView;

/**
 * Created by zhangguihua
 */
public class EssayListActivity extends RootActivity<EssayListPresenter> implements EssayListContract.View {

    @BindView(R.id.rcy)
    RecyclerView rcy;

    private ShortEssayAdapter mAdapter;

    /**
     * 打开新Activity
     *
     * @param context
     * @return
     */
    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, EssayListActivity.class);
        return intent;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_essaylist;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        stateLoading();
        mPresenter.getEssays();
        initPermissions();
    }

    @Override
    public void responeError(String errorMsg) {
        stateError();
    }

    @Override
    public void onSuccess(List<DataVO> essayVOS) {
        stateMain();
        for (int i = 0; i < essayVOS.size(); i++) {
            essayVOS.get(i).setId(UUID.randomUUID() + "");
        }
        if (essayVOS == null || essayVOS.isEmpty()) {
            stateEmpty("暂无内容");
        } else {
//            mAdapter = new ShortEssayAdapter(R.layout.item_essay,essayVOS);
            rcy.setLayoutManager(new LinearLayoutManager(this));
            rcy.setAdapter(mAdapter);
        }
    }

    /**
     * 拒绝权限后显示请求权限原因并再次申请
     */
    private void showRequestReason() {
        WindowManager windowManager = (WindowManager) this
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
                            initPermissions();
                            dialog.dismiss();
                            break;
                    }
                }).create();
        String[] reason = getResources().getStringArray(R.array.request_permission_reason);
        String str = "";
        for (int i = 0; i < reason.length; i++) {
            str += reason[i] + "\n" + "     ";
        }
        TextView textView = (TextView) logoutDialog.findViewById(R.id.txt_msg);
        textView.setText(str);
        logoutDialog.show();
    }

    @SuppressLint("CheckResult")
    private void initPermissions() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.setLogging(true);
        rxPermissions
                .requestEach(Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.RECORD_AUDIO)
                .subscribe(permission -> { // will emit 2 Permission objects
                    if (permission.granted) {
                        // 权限同意
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        // Denied permission without ask never again
                        // 禁止，但没有选择“以后不再询问”，以后申请权限，会继续弹出提示
                        showRequestReason();
                    } else {
                        // Denied permission with ask never again
                        // Need to go to the settings
                        // 禁止，但选择“以后不再询问”，以后申请权限，不会继续弹出提示
                        // 需要到 设置里面 手动打开
                    }
                });

        // 获取权限详细信息
        /*if (permission.name.equalsIgnoreCase(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            if (permission.granted) {//同意后调用
                LogUtils.error(TAG, "checkPermissionRequestEach--:" + "-READ_EXTERNAL_STORAGE-:" + true);
            } else if (permission.shouldShowRequestPermissionRationale){//禁止，但没有选择“以后不再询问”，以后申请权限，会继续弹出提示
                LogUtils.error(TAG, "checkPermissionRequestEach--:" + "-READ_EXTERNAL_STORAGE-shouldShowRequestPermissionRationale:" + false);
            }else {//禁止，但选择“以后不再询问”，以后申请权限，不会继续弹出提示
                LogUtils.error(TAG, "checkPermissionRequestEach--:" + "-READ_EXTERNAL_STORAGE-:" + false);
            }
        }
        if (permission.name.equalsIgnoreCase(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            if (permission.granted) {
                LogUtils.error(TAG, "checkPermissionRequestEach--:" + "-WRITE_EXTERNAL_STORAGE-:" + true);
            }else if (permission.shouldShowRequestPermissionRationale){
                LogUtils.error(TAG, "checkPermissionRequestEach--:" + "-READ_EXTERNAL_STORAGE-shouldShowRequestPermissionRationale:" + false);
            } else {
                LogUtils.error(TAG, "checkPermissionRequestEach--:" + "-WRITE_EXTERNAL_STORAGE-:" + false);
            }
        }
        if (permission.name.equalsIgnoreCase(Manifest.permission.READ_PHONE_STATE)) {
            if (permission.granted) {
                LogUtils.error(TAG, "checkPermissionRequestEach--:" + "-READ_CALENDAR-:" + true);
            }else if (permission.shouldShowRequestPermissionRationale){
                LogUtils.error(TAG, "checkPermissionRequestEach--:" + "-READ_EXTERNAL_STORAGE-shouldShowRequestPermissionRationale:" + false);
            } else {
                LogUtils.error(TAG, "checkPermissionRequestEach--:" + "-READ_CALENDAR-:" + false);
            }
        }*/

    }
}