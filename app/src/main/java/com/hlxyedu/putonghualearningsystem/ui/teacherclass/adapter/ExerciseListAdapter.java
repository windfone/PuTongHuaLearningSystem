package com.hlxyedu.putonghualearningsystem.ui.teacherclass.adapter;

import android.support.annotation.Nullable;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hlxyedu.putonghualearningsystem.model.bean.VideoVO;
import com.hlxyedu.putonghualearningsystem.ui.teacherclass.activity.ExerciseDetailActivity;

import java.util.List;

public class ExerciseListAdapter extends BaseQuickAdapter<VideoVO, BaseViewHolder> {

    private List<VideoVO> lists;

    public ExerciseListAdapter(int layoutResId, @Nullable List<VideoVO> data) {
        super(layoutResId, data);
        this.lists = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoVO item) {
        /*String position = (helper.getLayoutPosition()+1)> 9 ?
                (helper.getLayoutPosition()+1) +"" : "0" + (helper.getLayoutPosition()+1);*/
//        helper.setText(R.id.name_tv,item.getName())
//              .setText(R.id.title_type_tv,item.getTitleType());

        RelativeLayout relativeLayout = (RelativeLayout) helper.itemView;
        relativeLayout.setOnClickListener(v -> {
            mContext.startActivity(ExerciseDetailActivity.newInstance(mContext));
        });
    }

}
