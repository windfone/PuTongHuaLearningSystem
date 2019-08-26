package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.adapter;

import android.support.annotation.Nullable;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.model.bean.DataVO;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.activity.OnLineLearnDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class WordFollowAdapter extends BaseQuickAdapter<DataVO, BaseViewHolder> {

    private String title;
    private List<DataVO> lists;

    public WordFollowAdapter(int layoutResId, @Nullable List<DataVO> data, String mTitles) {
        super(layoutResId, data);
        this.lists = data;
        this.title = mTitles;
    }

    @Override
    protected void convert(BaseViewHolder helper, DataVO item) {
        String position = (helper.getLayoutPosition() + 1) > 9 ?
                (helper.getLayoutPosition() + 1) + "" : "0" + (helper.getLayoutPosition() + 1);
        helper.setText(R.id.title, "【" + position + "】  " + item.getConTitle());

        RelativeLayout relativeLayout = (RelativeLayout) helper.itemView;
        relativeLayout.setOnClickListener(v -> {
            mContext.startActivity(OnLineLearnDetailsActivity.newInstance(mContext, helper.getLayoutPosition(), (ArrayList<DataVO>) lists, title, item.getConTitle()));
        });
    }

}
