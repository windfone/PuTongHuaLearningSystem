package com.hlxyedu.putonghualearningsystem.ui.exam.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.model.bean.ExamCenterVO;
import com.hlxyedu.putonghualearningsystem.model.bean.PaperContentVO;

import java.util.List;

public class EChineseCharacterAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public EChineseCharacterAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.character_tv, item);
    }

}
