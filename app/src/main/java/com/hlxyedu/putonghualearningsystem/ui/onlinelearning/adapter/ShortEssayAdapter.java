package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.adapter;

import android.support.annotation.Nullable;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RxBus;
import com.hlxyedu.putonghualearningsystem.model.bean.DataVO;
import com.hlxyedu.putonghualearningsystem.model.event.LoginEvent;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.activity.OnLineLearnDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class ShortEssayAdapter extends BaseQuickAdapter<DataVO, BaseViewHolder> {

    private String title;
    private ArrayList<DataVO> datas;

    public ShortEssayAdapter(int layoutResId,
                             @Nullable List<DataVO> data, String mTitles) {
        super(layoutResId, data);
        this.datas = (ArrayList<DataVO>) data;
        this.title = mTitles;
    }

    @Override
    protected void convert(BaseViewHolder helper, DataVO item) {
        String position = (helper.getLayoutPosition() + 1) > 9 ?
                (helper.getLayoutPosition() + 1) + "" : "0" + (helper.getLayoutPosition() + 1);

//        String name = item.getName().substring(0, item.getName().lastIndexOf("."));
//        String txtStr = "【" + position + "】  " + "《" + name + "》示范朗读";
//        helper.setText(R.id.title, txtStr);
        helper.setText(R.id.title, item.getConTitle());

        RelativeLayout relativeLayout = (RelativeLayout) helper.itemView;
        relativeLayout.setOnClickListener(view ->
                RxBus.getDefault().post(new LoginEvent(LoginEvent.LOGIN,helper.getLayoutPosition(), (ArrayList<DataVO>) datas, title, item.getConTitle())));
//                mContext.startActivity(OnLineLearnDetailsActivity.newInstance(mContext, helper.getLayoutPosition(), (ArrayList<DataVO>) datas, title, item.getConTitle())));
    }
}
