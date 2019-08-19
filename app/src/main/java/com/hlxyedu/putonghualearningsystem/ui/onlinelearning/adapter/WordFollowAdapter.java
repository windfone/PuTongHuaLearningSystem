package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.adapter;

import android.support.annotation.Nullable;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.model.bean.PinYinBean;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.activity.OnLineLearnDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class WordFollowAdapter extends BaseQuickAdapter<PinYinBean, BaseViewHolder> {

    private ArrayList<PinYinBean> lists;
    private String title;

    public WordFollowAdapter(int layoutResId, @Nullable List<PinYinBean> data,String title) {
        super(layoutResId, data);
        this.lists = (ArrayList<PinYinBean>) data;
        this.title = title;
    }

    @Override
    protected void convert(BaseViewHolder helper, PinYinBean item) {
        String position = (helper.getLayoutPosition()+1)> 9 ?
                (helper.getLayoutPosition()+1) +"" : "0" + (helper.getLayoutPosition()+1);
        helper.setText(R.id.title,"【"+ position + "】  " + item.getTitle());

        RelativeLayout relativeLayout = (RelativeLayout) helper.itemView;
        relativeLayout.setOnClickListener(v -> {
//            mContext.startActivity(OnLineLearnDetailsActivity.newInstance(mContext, helper.getLayoutPosition(),title));
//            mContext.startActivity(OnLineLearnDetailsActivity.newInstance(mContext, helper.getLayoutPosition(),lists,title));
        });
    }

}
