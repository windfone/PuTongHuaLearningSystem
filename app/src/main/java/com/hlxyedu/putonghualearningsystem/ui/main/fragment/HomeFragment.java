package com.hlxyedu.putonghualearningsystem.ui.main.fragment;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootFragment;
import com.hlxyedu.putonghualearningsystem.model.bean.BannerVO;
import com.hlxyedu.putonghualearningsystem.model.http.api.ApiConstants;
import com.hlxyedu.putonghualearningsystem.ui.main.contract.HomeContract;
import com.hlxyedu.putonghualearningsystem.ui.main.presenter.HomePresenter;

import java.util.ArrayList;

import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by zhangguihua
 */
public class HomeFragment extends RootFragment<HomePresenter> implements HomeContract.View {

    @BindView(R.id.banner_guide_content)
    BGABanner banner_guide_content;

    private ArrayList<String> imgLists = new ArrayList<>();
    private ArrayList<String> titleLists = new ArrayList<>();

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initEventAndData() {

        imgLists.clear();
        titleLists.clear();

        /*for (BannerVO bannerVO : bannerVOList) {
            imgLists.add(ApiConstants.HOST + "user-info/pic/" + bannerVO.getBannerPath());
        }*/

        banner_guide_content.setAdapter((BGABanner.Adapter<ImageView, String>)
                (banner, itemView, model, position) -> Glide.with(mActivity)
                        .load(model)
                        .into(itemView));

        banner_guide_content.setDelegate((banner, itemView, model, position) -> {
//            startActivity(AActivity.class
//                    .newInstance(getContext(), bannerVOList.get(position).getId()));
        });
        banner_guide_content.setData(imgLists, titleLists);
        if (imgLists.size() < 2) {
            banner_guide_content.setAutoPlayAble(false);
        } else {
            banner_guide_content.setAutoPlayAble(true);
        }


    }

    @Override
    public void responeError(String errorMsg) {

    }

}