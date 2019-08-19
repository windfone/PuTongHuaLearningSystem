package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hlxyedu.putonghualearningsystem.R;

import java.util.List;

public class HanZiHeaderAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private List<Boolean> selectList;

    public HanZiHeaderAdapter(int layoutResId, @Nullable List<String> data,List<Boolean> selectList) {
        super(layoutResId, data);
        this.selectList = selectList;
    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {
        if (selectList.get(helper.getLayoutPosition())){
            helper.itemView.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_blue_2dp));
            helper.setTextColor(R.id.header_tv,mContext.getResources().getColor(R.color.blue3f1));
        }else {
            helper.itemView.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_gray_2dp));
            helper.setTextColor(R.id.header_tv,mContext.getResources().getColor(R.color.blackB5B));
        }
        helper.setText(R.id.header_tv,item);

    }
}
