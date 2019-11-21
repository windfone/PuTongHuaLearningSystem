package com.hlxyedu.putonghualearningsystem.ui.publics.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootFragment;
import com.hlxyedu.putonghualearningsystem.base.RxBus;
import com.hlxyedu.putonghualearningsystem.model.bean.TopTitleVO;
import com.hlxyedu.putonghualearningsystem.model.event.ActionEvent;
import com.hlxyedu.putonghualearningsystem.ui.publics.adapter.PagerFragmentAdapter;
import com.hlxyedu.putonghualearningsystem.ui.publics.contract.ViewPagerContract;
import com.hlxyedu.putonghualearningsystem.ui.publics.presenter.ViewPagerPresenter;
import com.hlxyedu.putonghualearningsystem.utils.ColorFlipPagerTitleView;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.popup.QMUIListPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhangguihua
 */
public class ViewPagerFragment extends RootFragment<ViewPagerPresenter> implements ViewPagerContract.View {

    @BindView(R.id.top_indicator)
    MagicIndicator top_indicator;
    @BindView(R.id.view_pager)
    ViewPager view_pager;
    @BindView(R.id.sort_iv)
    ImageView sort_iv;
    @BindView(R.id.sort_ll)
    LinearLayout sort_ll;
    @BindView(R.id.top_bar_rl)
    LinearLayout top_bar_rl;

    private ArrayList<String> mTitleDataList;
    private List<TopTitleVO> lists;

    // from == 1 ,在线学习； from == 2，名师课堂
    public static ViewPagerFragment newInstance(int from, ArrayList<String> titles,List<TopTitleVO> lists) {
        Bundle args = new Bundle();
        args.putInt("from", from);
        args.putStringArrayList("titles", titles);
        args.putParcelableArrayList("titleVO", (ArrayList<? extends Parcelable>) lists);
        ViewPagerFragment fragment = new ViewPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_view_pager;
    }

    @Override
    protected void initEventAndData() {
        int from = -1;
        if (getArguments() != null) {
            from = getArguments().getInt("from");
            mTitleDataList = getArguments().getStringArrayList("titles");
            lists = getArguments().getParcelableArrayList("titleVO");
            if (from == 1) {
                sort_ll.setVisibility(View.GONE);
            } else if (from == 2) {
                sort_ll.setVisibility(View.VISIBLE);
//                top_bar_rl.setVisibility(View.GONE);
            }
        }
        initIndicator();
        view_pager.setAdapter(new PagerFragmentAdapter(getChildFragmentManager(), from, mTitleDataList,lists));
    }

    private void initIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(mActivity);
        commonNavigator.setScrollPivotX(0.65f);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mTitleDataList == null ? 0 : mTitleDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
                simplePagerTitleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
                simplePagerTitleView.setText(mTitleDataList.get(index));
                simplePagerTitleView.setNormalColor(ContextCompat.getColor(mActivity, R.color.gray9B9));
                simplePagerTitleView.setSelectedColor(ContextCompat.getColor(mActivity, R.color.whitefff));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        view_pager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 5));
                indicator.setLineWidth(UIUtil.dip2px(context, 10));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(ContextCompat.getColor(mActivity, R.color.blue5FC));
                return indicator;
            }
        });
        top_indicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(top_indicator, view_pager);
    }

    @Override
    public void responeError(String errorMsg) {

    }


    private QMUIListPopup mListPopup;

    @OnClick(R.id.sort_ll)
    public void onViewClicked() {
        initListPopupIfNeed();
        mListPopup.setAnimStyle(QMUIPopup.ANIM_AUTO);
        mListPopup.setPreferredDirection(QMUIPopup.DIRECTION_BOTTOM);
        mListPopup.show(sort_ll);
    }

    private void initListPopupIfNeed() {
        if (mListPopup == null) {

            String[] listItems = new String[]{
                    "阅读量排序",
                    "上传日期排序",
            };
            List<String> data = new ArrayList<>();

            Collections.addAll(data, listItems);

            ArrayAdapter adapter = new ArrayAdapter<>(getActivity(), R.layout.qmui_pop_list_item, data);

            mListPopup = new QMUIListPopup(getContext(), QMUIPopup.DIRECTION_NONE, adapter);
            mListPopup.create(QMUIDisplayHelper.dp2px(getContext(), 100), ViewGroup.LayoutParams.WRAP_CONTENT, new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    switch (i){
                        // 阅读量排序
                        case 0:
                            RxBus.getDefault().post(new ActionEvent(ActionEvent.SORT,0));
                            break;
                        // 上传日期排序
                        case 1:
                            RxBus.getDefault().post(new ActionEvent(ActionEvent.SORT,1));
                            break;
                    }
                    mListPopup.dismiss();
                }
            });
        }
    }
}