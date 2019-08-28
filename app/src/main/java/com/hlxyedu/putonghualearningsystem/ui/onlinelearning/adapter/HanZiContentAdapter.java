package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.adapter;

import android.support.annotation.Nullable;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RxBus;
import com.hlxyedu.putonghualearningsystem.model.bean.DataVO;
import com.hlxyedu.putonghualearningsystem.model.bean.HanZiContentVO;
import com.hlxyedu.putonghualearningsystem.model.event.LoginEvent;

import java.util.ArrayList;
import java.util.List;

public class HanZiContentAdapter extends BaseQuickAdapter<DataVO, BaseViewHolder> {

    private String title;
    private List<DataVO> lists;

    public HanZiContentAdapter(int layoutResId, @Nullable List<DataVO> data, String mTitles) {
        super(layoutResId, data);
        this.lists = data;
        this.title = mTitles;
    }

    @Override
    protected void convert(BaseViewHolder helper, DataVO item) {
        helper.setText(R.id.pinyin_tv, item.getPinyin())
                .setText(R.id.cn_tv, item.getPinYinCN());

        LinearLayout relativeLayout = (LinearLayout) helper.itemView;
        relativeLayout.setOnClickListener(v -> {
            RxBus.getDefault().post(new LoginEvent(LoginEvent.LOGIN,helper.getLayoutPosition(), (ArrayList<DataVO>) lists, title, ""));
//            mContext.startActivity(OnLineLearnDetailsActivity.newInstance(mContext, helper.getLayoutPosition(), (ArrayList<DataVO>) lists, title, item.getConTitle()));
        });
    }

}
