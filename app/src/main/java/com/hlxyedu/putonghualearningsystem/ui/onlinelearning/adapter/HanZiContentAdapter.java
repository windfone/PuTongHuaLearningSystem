package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.model.bean.HanZiContentVO;

import java.util.List;

public class HanZiContentAdapter extends BaseQuickAdapter<HanZiContentVO, BaseViewHolder> {

    public HanZiContentAdapter(int layoutResId, @Nullable List<HanZiContentVO> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HanZiContentVO item) {
        helper.setText(R.id.pinyin_tv, item.getPinyin())
                .setText(R.id.cn_tv, item.getCn());
    }

}
