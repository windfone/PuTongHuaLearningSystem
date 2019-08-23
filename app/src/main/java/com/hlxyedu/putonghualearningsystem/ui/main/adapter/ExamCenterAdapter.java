package com.hlxyedu.putonghualearningsystem.ui.main.adapter;

import android.support.annotation.Nullable;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.model.bean.ExamCenterVO;
import com.hlxyedu.putonghualearningsystem.ui.exam.activity.ExamDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class ExamCenterAdapter extends BaseQuickAdapter<ExamCenterVO, BaseViewHolder> {

    private List<ExamCenterVO> lists;

    public ExamCenterAdapter(int layoutResId, @Nullable List<ExamCenterVO> data) {
        super(layoutResId, data);
        this.lists = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, ExamCenterVO item) {
        String position = (helper.getLayoutPosition() + 1) > 9 ?
                (helper.getLayoutPosition() + 1) + "" : "0" + (helper.getLayoutPosition() + 1);
        helper.setText(R.id.title, "【" + position + "】  " + item.getPaperTitle());

        RelativeLayout relativeLayout = (RelativeLayout) helper.itemView;
        relativeLayout.setOnClickListener(v -> {
            mContext.startActivity(ExamDetailActivity.newInstance(mContext, helper.getLayoutPosition(), (ArrayList<ExamCenterVO>) lists));
        });
    }

}
