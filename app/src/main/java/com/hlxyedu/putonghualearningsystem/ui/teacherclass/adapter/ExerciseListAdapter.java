package com.hlxyedu.putonghualearningsystem.ui.teacherclass.adapter;

import android.support.annotation.Nullable;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.model.bean.VideoVO;
import com.hlxyedu.putonghualearningsystem.ui.teacherclass.activity.ExerciseDetailActivity;

import java.util.List;

public class ExerciseListAdapter extends BaseQuickAdapter<VideoVO, BaseViewHolder> {

    private List<VideoVO> lists;
    private String mTitles;

    public ExerciseListAdapter(int layoutResId, @Nullable List<VideoVO> data,String mTitles) {
        super(layoutResId, data);
        this.lists = data;
        this.mTitles = mTitles;
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoVO item) {
        String position = (helper.getLayoutPosition()+1)> 9 ?
                (helper.getLayoutPosition()+1) +"" : "0" + (helper.getLayoutPosition()+1);
        helper.setText(R.id.title_tv,item.getTeaTitle())
              .setText(R.id.time_tv,item.getCreateTime())
              .setText(R.id.read_num_tv,"阅读量："+item.getBrowseNum());

        RelativeLayout relativeLayout = (RelativeLayout) helper.itemView;
        relativeLayout.setOnClickListener(v -> {
            mContext.startActivity(ExerciseDetailActivity.newInstance(mContext,mTitles,item));
        });
    }

}
