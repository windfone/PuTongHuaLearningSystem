package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.model.bean.DetailVO;
import com.hlxyedu.putonghualearningsystem.model.bean.PinYinBean;

import java.util.ArrayList;
import java.util.List;

public class WordFollwDetailAdapter extends BaseQuickAdapter<DetailVO, BaseViewHolder> {

    private List<Boolean> selectList;

    public WordFollwDetailAdapter(int layoutResId, @Nullable List<DetailVO> data,List<Boolean> selects) {
        super(layoutResId, data);
        this.selectList = selects;
    }

    @Override
    protected void convert(BaseViewHolder helper, DetailVO item) {
        String[] pinyin = item.getPinyin().split("\\|");
        String[] cn = item.getConDetail().split("\\|");
        helper.setText(R.id.left_pinyin_tv,pinyin[0])
                        .setText(R.id.right_pinyin_tv,pinyin[1])
                        .setText(R.id.left_cn_tv,cn[0])
                        .setText(R.id.right_cn_tv,cn[1]);

        if (selectList.get(helper.getLayoutPosition())){
            helper.itemView.setBackground(ContextCompat.getDrawable(mContext,R.drawable.icon_word_detail_select_bg));
        }else {
            helper.itemView.setBackgroundColor(ContextCompat.getColor(mContext,R.color.whitefff));
        }
    }

}