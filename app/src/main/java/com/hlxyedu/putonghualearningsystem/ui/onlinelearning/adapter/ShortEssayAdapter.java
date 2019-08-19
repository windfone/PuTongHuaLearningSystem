package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.adapter;

import android.support.annotation.Nullable;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.model.bean.EssayVO;
import com.hlxyedu.putonghualearningsystem.ui.essay.activity.EssayDetailsActivity;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.activity.OnLineLearnDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class ShortEssayAdapter extends BaseQuickAdapter<EssayVO, BaseViewHolder> {

    private String title;
    private ArrayList<EssayVO> datas;

    public ShortEssayAdapter(int layoutResId,
                             @Nullable List<EssayVO> data,String mTitles) {
        super(layoutResId, data);
        this.datas = (ArrayList<EssayVO>) data;
        this.title = mTitles;
    }

    @Override
    protected void convert(BaseViewHolder helper, EssayVO item) {
        String position = (helper.getLayoutPosition()+1)> 9 ?
                (helper.getLayoutPosition()+1) +"" : "0" + (helper.getLayoutPosition()+1);

        String name = item.getName().substring(0,item.getName().lastIndexOf("."));
        helper.setText(R.id.title, "【"+ position + "】  " + "《" + name + "》示范朗读");

        RelativeLayout relativeLayout = (RelativeLayout) helper.itemView;
        relativeLayout.setOnClickListener(view ->
//                mContext.startActivity(OnLineLearnDetailsActivity.newInstance(mContext)));
                mContext.startActivity(OnLineLearnDetailsActivity.newInstance(mContext, helper.getLayoutPosition(),datas,title)));
    }
}
