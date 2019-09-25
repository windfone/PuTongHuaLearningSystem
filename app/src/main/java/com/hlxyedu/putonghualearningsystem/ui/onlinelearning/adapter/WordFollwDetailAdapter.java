package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.model.bean.DetailVO;

import java.util.ArrayList;
import java.util.List;

public class WordFollwDetailAdapter extends BaseQuickAdapter<DetailVO, BaseViewHolder> {

    private List<Boolean> selectList;

    public WordFollwDetailAdapter(int layoutResId, @Nullable List<DetailVO> data,List<Boolean> selects) {
        super(layoutResId, data);
        this.selectList = selects;
    }

    @Override
    protected void convert(BaseViewHolder helper, DetailVO item) {
        String[] pinyin = item.getPinyin().split("\\|");
        String[] cn = item.getConDetail().split("\\|");
        /*helper.setText(R.id.left_pinyin_tv,pinyin[0])
                        .setText(R.id.right_pinyin_tv,pinyin[1])
                        .setText(R.id.left_cn_tv,cn[0])
                        .setText(R.id.right_cn_tv,cn[1]);*/
        LinearLayout linearLayout = helper.itemView.findViewById(R.id.container_ll);
        // remove view 是防止 item的点击事件之后为了改变item 背景 notify adapter 导致数据错乱
        linearLayout.removeAllViews();
        for (int i = 0; i < cn.length; i++) {
            addView(linearLayout,cn[i],pinyin[i]);
        }

        if (selectList.get(helper.getLayoutPosition())){
            helper.itemView.setBackground(ContextCompat.getDrawable(mContext,R.drawable.icon_word_detail_select_bg));
        }else {
            helper.itemView.setBackgroundColor(ContextCompat.getColor(mContext,R.color.whitefff));
        }
    }

    /**
     * for循环，向容器中添加TextViewbg_ll
     * @param mContainer
     */
    public void addView(LinearLayout mContainer,String cn,String pinyin) {
        LinearLayout views = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.item_word_follow_view, null);
        TextView cnView = views.findViewById(R.id.cn_tv);
        TextView pinyinView = views.findViewById(R.id.pinyin_tv);
        cnView.setText(cn);
        pinyinView.setText(pinyin);
        mContainer.setGravity(Gravity.CENTER);
        /*TextView child = new TextView(mContext.getApplicationContext());
        child.setWidth(140);
        child.setHeight(140);
        child.setGravity(Gravity.CENTER);
        child.setTextSize(TypedValue.COMPLEX_UNIT_SP,30);
        child.setTextColor(mContext.getResources().getColor(R.color.black353));
        child.setBackground(ContextCompat.getDrawable(mContext,R.drawable.icon_word_bg));
        child.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
        child.setText(cn);*/
        // 调用一个参数的addView方法
        mContainer.addView(views);
    }

}