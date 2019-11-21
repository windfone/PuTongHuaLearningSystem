package com.hlxyedu.putonghualearningsystem.ui.main.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.model.bean.ExamCenterVO;
import com.hlxyedu.putonghualearningsystem.model.bean.HomeSortVO;
import com.hlxyedu.putonghualearningsystem.model.http.api.ApiConstants;
import com.hlxyedu.putonghualearningsystem.ui.exam.activity.ExamDetailActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HomeSortAdapter extends BaseQuickAdapter<Map<String,Object>, BaseViewHolder> {

//    private List<Map<String,Object>> listItems;

    public HomeSortAdapter(int layoutResId, @Nullable List<Map<String,Object>> data) {
        super(layoutResId, data);
//        this.listItems = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, Map<String,Object> item) {
        helper.setText(R.id.tv_type,String.valueOf(item.get("text")));
        ImageView imageView = helper.getView(R.id.iv_type);
        Glide.with(mContext).load(item.get("pic")).into(imageView);
        LinearLayout linearLayout = (LinearLayout) helper.itemView;
        linearLayout.setOnClickListener(v -> {

        });
    }

}
