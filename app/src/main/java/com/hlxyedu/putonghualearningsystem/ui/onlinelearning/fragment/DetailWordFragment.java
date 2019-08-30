package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootFragment;
import com.hlxyedu.putonghualearningsystem.base.RxBus;
import com.hlxyedu.putonghualearningsystem.model.bean.DetailVO;
import com.hlxyedu.putonghualearningsystem.model.bean.PinYinBean;
import com.hlxyedu.putonghualearningsystem.model.event.ActionEvent;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.adapter.WordFollwDetailAdapter;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.contract.DetailWordContract;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.presenter.DetailWordPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zhangguihua
 * 单词跟读 详情
 */
public class DetailWordFragment extends RootFragment<DetailWordPresenter> implements DetailWordContract.View {

    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.rcy)
    RecyclerView rcy;
    private WordFollwDetailAdapter mAdapter;

    private List<Boolean> selectList = new ArrayList<>();
    private int lastSelect = 0; // 默认 第一行是选中状态

    public static DetailWordFragment newInstance() {
        Bundle args = new Bundle();

        DetailWordFragment fragment = new DetailWordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_word;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void setDatas(List<DetailVO> detailVOS) {
        title_tv.setText(detailVOS.get(0).getConTitle());
        for (int i = 0; i < detailVOS.size(); i++) {
            if (i == 0){
                selectList.add(true);
            }else {
                selectList.add(false);
            }
        }
        mAdapter = new WordFollwDetailAdapter(R.layout.item_word_follow_detail, detailVOS,selectList);
        rcy.setLayoutManager(new LinearLayoutManager(mActivity));
//        rcy.setAdapter(mAdapter);
        // 貌似在 这里用 item 的点击事件得用这种绑定方法，在adapter则直接setAdapter即可
        mAdapter.bindToRecyclerView(rcy);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                RxBus.getDefault().post(new ActionEvent(ActionEvent.PLAYWORDAUDIO,detailVOS.get(position)));
                if (position == lastSelect) {
                    return;
                }
                selectList.set(position,true);
                selectList.set(lastSelect,false);
                lastSelect = position;
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void responeError(String errorMsg) {

    }

}