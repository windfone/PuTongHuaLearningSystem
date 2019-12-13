package com.hlxyedu.putonghualearningsystem.ui.main.fragment;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootFragment;
import com.hlxyedu.putonghualearningsystem.model.bean.UserVO;
import com.hlxyedu.putonghualearningsystem.model.http.api.ApiConstants;
import com.hlxyedu.putonghualearningsystem.ui.main.contract.PersonalCenterContract;
import com.hlxyedu.putonghualearningsystem.ui.main.presenter.PersonalCenterPresenter;
import com.hlxyedu.putonghualearningsystem.ui.personal.activity.GeneralPurposeActivity;
import com.hlxyedu.putonghualearningsystem.ui.personal.activity.PersonalInfoActivity;
import com.hlxyedu.putonghualearningsystem.weight.actionbar.XBaseTopBar;
import com.hlxyedu.putonghualearningsystem.weight.actionbar.XBaseTopBarImp;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhangguihua
 */
public class PersonalCenterFragment extends RootFragment<PersonalCenterPresenter> implements PersonalCenterContract.View, XBaseTopBarImp {


    @BindView(R.id.xbase_topbar)
    XBaseTopBar xbase_topbar;
    @BindView(R.id.headportrait_iv)
    ImageView headportrait_iv;
    @BindView(R.id.name_tv)
    TextView name_tv;
    @BindView(R.id.personal_tongji_rl)
    RelativeLayout personal_tongji_rl;
    @BindView(R.id.learning_progress_rl)
    RelativeLayout learning_progress_rl;
    @BindView(R.id.general_purpose_rl)
    RelativeLayout general_purpose_rl;

    public static PersonalCenterFragment newInstance() {
        Bundle args = new Bundle();

        PersonalCenterFragment fragment = new PersonalCenterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personal_center;
    }

    @Override
    protected void initEventAndData() {
        xbase_topbar.setxBaseTopBarImp(this);
        xbase_topbar.setLeftImg(ContextCompat.getDrawable(mActivity,R.drawable.icon_camera_white));

        mPresenter.getUserInfo(2);
    }

    @Override
    public void responeError(String errorMsg) {

    }

    @Override
    public void onSuccess(UserVO userVO) {
        Glide.with(mActivity).load(ApiConstants.HOST + "user-info/pic/" + userVO.getHeadImgUrl())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()).placeholder(R.drawable.icon_headportrait)).into(headportrait_iv);
        name_tv.setText(userVO.getNickName());
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
    }

    @OnClick({R.id.headportrait_iv, R.id.personal_tongji_rl, R.id.learning_progress_rl, R.id.general_purpose_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.headportrait_iv:
                startActivity(PersonalInfoActivity.newInstance(mActivity));
                break;
            case R.id.personal_tongji_rl:
                break;
            case R.id.learning_progress_rl:
                break;
            case R.id.general_purpose_rl:
                startActivity(GeneralPurposeActivity.newInstance(mActivity));
                break;
        }
    }

    @Override
    public void left() {

    }

    @Override
    public void right() {

    }

}