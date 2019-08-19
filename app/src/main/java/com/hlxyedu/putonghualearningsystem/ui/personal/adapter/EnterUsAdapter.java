package com.hlxyedu.putonghualearningsystem.ui.personal.adapter;

import android.support.annotation.Nullable;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.model.bean.EnterUsVO;
import com.hlxyedu.putonghualearningsystem.model.bean.PinYinBean;

import java.util.List;

public class EnterUsAdapter extends BaseQuickAdapter<EnterUsVO, BaseViewHolder> {

    private List<EnterUsVO> lists;

    public EnterUsAdapter(int layoutResId, @Nullable List<EnterUsVO> data) {
        super(layoutResId, data);
        this.lists = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, EnterUsVO item) {
        /*String position = (helper.getLayoutPosition()+1)> 9 ?
                (helper.getLayoutPosition()+1) +"" : "0" + (helper.getLayoutPosition()+1);*/
        helper.setText(R.id.name_tv,item.getName())
              .setText(R.id.title_type_tv,item.getTitleType());

       /* RelativeLayout relativeLayout = (RelativeLayout) helper.itemView;
        relativeLayout.setOnClickListener(v -> {
//            mContext.startActivity(EssayDetailsActivity.newInstance(mContext, helper.getLayoutPosition(),lists));
        });*/
    }

}
