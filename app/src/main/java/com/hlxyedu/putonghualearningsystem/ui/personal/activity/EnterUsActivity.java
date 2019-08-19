package com.hlxyedu.putonghualearningsystem.ui.personal.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootActivity;
import com.hlxyedu.putonghualearningsystem.model.bean.EnterUsVO;
import com.hlxyedu.putonghualearningsystem.ui.personal.adapter.EnterUsAdapter;
import com.hlxyedu.putonghualearningsystem.ui.personal.contract.EnterUsContract;
import com.hlxyedu.putonghualearningsystem.ui.personal.presenter.EnterUsPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhangguihua
 */
public class EnterUsActivity extends RootActivity<EnterUsPresenter> implements EnterUsContract.View {

    @BindView(R.id.back_iv)
    ImageView backIv;
    @BindView(R.id.rcy)
    RecyclerView rcy;

    private EnterUsAdapter mAdapter;

    /**
     * 打开新Activity
     *
     * @param context
     * @return
     */
    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, EnterUsActivity.class);
        return intent;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_enter_us;
    }

    @Override
    protected void initEventAndData() {
        List<EnterUsVO> lists = new ArrayList<>();

        EnterUsVO enterUsVO1 = new EnterUsVO();
        enterUsVO1.setName("张小盒");
        enterUsVO1.setTitleType("广告合作");
        lists.add(enterUsVO1);

        EnterUsVO enterUsVO2 = new EnterUsVO();
        enterUsVO2.setName("张小碗");
        enterUsVO2.setTitleType("资源合作");
        lists.add(enterUsVO2);

        EnterUsVO enterUsVO3 = new EnterUsVO();
        enterUsVO3.setName("张小瓢");
        enterUsVO3.setTitleType("题库合作");
        lists.add(enterUsVO3);

        mAdapter = new EnterUsAdapter(R.layout.item_enter_us, lists);
        rcy.setLayoutManager(new LinearLayoutManager(this));
        rcy.setAdapter(mAdapter);

    }

    @Override
    public void responeError(String errorMsg) {

    }

    @OnClick(R.id.back_iv)
    public void onViewClicked() {
        finish();
    }

}