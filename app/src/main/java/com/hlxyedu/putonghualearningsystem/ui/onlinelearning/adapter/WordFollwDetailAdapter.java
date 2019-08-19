package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.model.bean.PinYinBean;

import java.util.ArrayList;
import java.util.List;

public class WordFollwDetailAdapter extends BaseQuickAdapter<PinYinBean, BaseViewHolder> {

    private ArrayList<PinYinBean> lists;
    private List<Boolean> selectList;
    private String title;

    public WordFollwDetailAdapter(int layoutResId, @Nullable List<PinYinBean> data, String title,List<Boolean> selects) {
        super(layoutResId, data);
        this.lists = (ArrayList<PinYinBean>) data;
        this.title = title;
        this.selectList = selects;
    }

    @Override
    protected void convert(BaseViewHolder helper, PinYinBean item) {
        String position = (helper.getLayoutPosition()+1)> 9 ?
                (helper.getLayoutPosition()+1) +"" : "0" + (helper.getLayoutPosition()+1);
        helper.setImageResource(R.id.word_iv,R.mipmap.ic_launcher);
        if (selectList.get(helper.getLayoutPosition())){
            helper.itemView.setBackground(ContextCompat.getDrawable(mContext,R.drawable.icon_word_detail_select_bg));
        }else {
            helper.itemView.setBackgroundColor(ContextCompat.getColor(mContext,R.color.whitefff));
        }
    }

}