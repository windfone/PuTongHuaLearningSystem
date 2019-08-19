package com.hlxyedu.putonghualearningsystem.ui.teacherclass.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hlxyedu.putonghualearningsystem.model.bean.CommentVO;

import java.util.List;

public class CommentAdapter extends BaseQuickAdapter<CommentVO, BaseViewHolder> {

    public CommentAdapter(int layoutResId, @Nullable List<CommentVO> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentVO item) {

    }
}
