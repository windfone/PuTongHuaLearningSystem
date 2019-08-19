package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.adapter;

import android.support.annotation.Nullable;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.model.bean.PinYinBean;

import java.util.List;

public class SoftWordAdapter extends BaseQuickAdapter<PinYinBean, BaseViewHolder> {

    private String title;
    private List<PinYinBean> lists;

    public SoftWordAdapter(int layoutResId, @Nullable List<PinYinBean> data,String mTitles) {
        super(layoutResId, data);
        this.lists = data;
        this.title = mTitles;
    }

    @Override
    protected void convert(BaseViewHolder helper, PinYinBean item) {
        String position = (helper.getLayoutPosition()+1)> 9 ?
                (helper.getLayoutPosition()+1) +"" : "0" + (helper.getLayoutPosition()+1);
        helper.setText(R.id.title,"【"+ position + "】  " + item.getTitle());

        RelativeLayout relativeLayout = (RelativeLayout) helper.itemView;
        relativeLayout.setOnClickListener(v -> {
//            mContext.startActivity(EssayDetailsActivity.newInstance(mContext, helper.getLayoutPosition(),lists));
        });
    }

}
