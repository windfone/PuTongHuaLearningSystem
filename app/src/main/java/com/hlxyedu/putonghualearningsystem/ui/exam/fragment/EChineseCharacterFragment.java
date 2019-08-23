package com.hlxyedu.putonghualearningsystem.ui.exam.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootFragment;
import com.hlxyedu.putonghualearningsystem.base.RxBus;
import com.hlxyedu.putonghualearningsystem.model.event.ActionEvent;
import com.hlxyedu.putonghualearningsystem.model.event.RecordEvent;
import com.hlxyedu.putonghualearningsystem.ui.exam.adapter.EChineseCharacterAdapter;
import com.hlxyedu.putonghualearningsystem.ui.exam.contract.EChineseCharacterContract;
import com.hlxyedu.putonghualearningsystem.ui.exam.presenter.EChineseCharacterPresenter;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.adapter.HanZiContentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zhangguihua
 * 汉字
 */
public class EChineseCharacterFragment extends RootFragment<EChineseCharacterPresenter> implements EChineseCharacterContract.View {

    @BindView(R.id.rcy)
    RecyclerView rcy;

    private EChineseCharacterAdapter mContentAdapter;
    private List<String> datas;
    private int recordTimeLength;
    private String problemTitle;

    public static EChineseCharacterFragment newInstance(List<String> lists,String title,int answerTime) {
        Bundle args = new Bundle();
        args.putStringArrayList("data", (ArrayList<String>) lists);
        args.putString("problemTitle", title);
        args.putInt("recordTimeLength", answerTime);
        EChineseCharacterFragment fragment = new EChineseCharacterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_echinese_character;
    }

    @Override
    protected void initEventAndData() {

        datas = getArguments().getStringArrayList("data");
        problemTitle = getArguments().getString("problemTitle");
        recordTimeLength = getArguments().getInt("recordTimeLength");

        mContentAdapter = new EChineseCharacterAdapter(R.layout.item_e_chinesecharacter,datas);
        rcy.setLayoutManager(new GridLayoutManager(mActivity,10));
        rcy.setAdapter(mContentAdapter);

        RxBus.getDefault().post(new ActionEvent(ActionEvent.COUNTDOWN,problemTitle,recordTimeLength));
    }

    @Override
    public void responeError(String errorMsg) {

    }

}