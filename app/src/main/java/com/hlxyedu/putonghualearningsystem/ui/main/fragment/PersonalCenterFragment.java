package com.hlxyedu.putonghualearningsystem.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootFragment;
import com.hlxyedu.putonghualearningsystem.ui.main.contract.PersonalCenterContract;
import com.hlxyedu.putonghualearningsystem.ui.main.presenter.PersonalCenterPresenter;
import com.hlxyedu.putonghualearningsystem.ui.personal.activity.GeneralPurposeActivity;
import com.hlxyedu.putonghualearningsystem.ui.personal.activity.PersonalInfoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by zhangguihua
 */
public class PersonalCenterFragment extends RootFragment<PersonalCenterPresenter> implements PersonalCenterContract.View {


    @BindView(R.id.scan_iv)
    ImageView scan_iv;
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

    }

    @Override
    public void responeError(String errorMsg) {

    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
    }

    @OnClick({R.id.scan_iv, R.id.headportrait_iv, R.id.personal_tongji_rl, R.id.learning_progress_rl, R.id.general_purpose_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.scan_iv:
                break;
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
}