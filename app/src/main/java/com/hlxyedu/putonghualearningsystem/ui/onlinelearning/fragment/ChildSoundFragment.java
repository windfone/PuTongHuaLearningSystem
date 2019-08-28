package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootFragment;
import com.hlxyedu.putonghualearningsystem.model.bean.DataVO;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.adapter.HanZiContentAdapter;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.adapter.HanZiHeaderAdapter;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.contract.ChildSoundContract;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.presenter.ChildSoundPresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zhangguihua
 * 儿化音
 */
public class ChildSoundFragment extends RootFragment<ChildSoundPresenter> implements ChildSoundContract.View {

    @BindView(R.id.top_header_rcy)
    RecyclerView top_header_rcy;
    @BindView(R.id.list_rcy)
    RecyclerView list_rcy;

    // 顶部字母
    private List<Boolean> selectList = new ArrayList<>();
    private int lastSelect = 0; // 默认 选中全部
    private HanZiHeaderAdapter mHeaderAdapter;
    private String[] strs = {"全部", "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "W", "X", "Y", "Z"};
    private List<String> datas = Arrays.asList(strs);

    //列表相关
    private HanZiContentAdapter mContentAdapter;

    private int typeId;

    public static ChildSoundFragment newInstance(String mTitles, int typeId) {
        Bundle args = new Bundle();
        args.putString("title", mTitles);
        args.putInt("typeId", typeId);

        ChildSoundFragment fragment = new ChildSoundFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_child_sound;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        initTopRcy();
        stateLoading();

        typeId = getArguments().getInt("typeId");

        mPresenter.getWordList(typeId,"");
    }

    @Override
    public void onSuccess(List<DataVO> lists) {
        stateMain();
        if (lists == null || lists.isEmpty()) {
            stateEmpty("暂无内容");
        } else {
            mContentAdapter = new HanZiContentAdapter(R.layout.item_hanzi_comtent, lists, getArguments().getString("title"));
            list_rcy.setLayoutManager(new GridLayoutManager(mActivity, 3));
            list_rcy.setAdapter(mContentAdapter);
        }
    }

    private void initTopRcy() {
        for (int i = 0; i < 25; i++) {
            if (i == 0) {
                selectList.add(true);
            } else {
                selectList.add(false);
            }
        }

        mHeaderAdapter = new HanZiHeaderAdapter(R.layout.item_header, datas, selectList);
        top_header_rcy.setLayoutManager(new GridLayoutManager(mActivity, 8));
        mHeaderAdapter.bindToRecyclerView(top_header_rcy);

        mHeaderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position == lastSelect) {
                    return;
                }
                selectList.set(position, true);
                selectList.set(lastSelect, false);
                lastSelect = position;
                mHeaderAdapter.notifyDataSetChanged();
                // 根据选中字母查询相关
                if (position == 0) {
                    // 全部
                    mPresenter.getWordList(typeId,"");
                } else {
                    mPresenter.getWordList(typeId,strs[position]);
                }
            }
        });
    }

    @Override
    public void responeError(String errorMsg) {
        stateError();
    }

}