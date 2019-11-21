package com.hlxyedu.putonghualearningsystem.ui.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootFragment;
import com.hlxyedu.putonghualearningsystem.model.bean.HomeSortVO;
import com.hlxyedu.putonghualearningsystem.ui.main.adapter.HomeSortAdapter;
import com.hlxyedu.putonghualearningsystem.ui.main.contract.HomeContract;
import com.hlxyedu.putonghualearningsystem.ui.main.presenter.HomePresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by zhangguihua
 */
public class HomeFragment extends RootFragment<HomePresenter> implements HomeContract.View {

    @BindView(R.id.banner_guide_content)
    BGABanner banner_guide_content;
    @BindView(R.id.rcy)
    RecyclerView rcy;
    @BindView(R.id.ll_more)
    LinearLayout ll_more;
    @BindView(R.id.cover_iv)
    ImageView cover_iv;
    @BindView(R.id.play_iv)
    ImageView play_iv;

    private HomeSortAdapter mAdapter;
    private Map<String,Object> map;
    private List<Map<String,Object>> listItems = new ArrayList<>();

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
        initTopSortView();

        imgLists.clear();
        titleLists.clear();

        /*for (BannerVO bannerVO : bannerVOList) {
            imgLists.add(ApiConstants.HOST + "user-info/pic/" + bannerVO.getBannerPath());
        }*/

        imgLists.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574223020120&di=4e56271616b7679a8185fef8f9d227a1&imgtype=0&src=http%3A%2F%2Fg.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fc2cec3fdfc03924590b2a9b58d94a4c27d1e2500.jpg");
        imgLists.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574223047092&di=de3a838acbb41b5ceca2bae340105450&imgtype=0&src=http%3A%2F%2Fb.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F908fa0ec08fa513db777cf78376d55fbb3fbd9b3.jpg");

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

    private void initTopSortView() {
        map = new HashMap<>();
        map.put("pic",R.drawable.icon_exam_registration);
        map.put("text","考试报名");
        listItems.add(map);
        map = new HashMap<>();
        map.put("pic",R.drawable.icon_registration_inquiry);
        map.put("text","报名查询");
        listItems.add(map);
        map = new HashMap<>();
        map.put("pic",R.drawable.icon_admission_ticket);
        map.put("text","准考证");
        listItems.add(map);
        map = new HashMap<>();
        map.put("pic",R.drawable.icon_result_inquiry);
        map.put("text","成绩查询");
        listItems.add(map);
        map = new HashMap<>();
        map.put("pic",R.drawable.icon_e_learning);
        map.put("text","在线学习");
        listItems.add(map);
        map = new HashMap<>();
        map.put("pic",R.drawable.icon_model_test_center);
        map.put("text","模考中心");
        listItems.add(map);
        map = new HashMap<>();
        map.put("pic",R.drawable.icon_teacher_class);
        map.put("text","名师课堂");
        listItems.add(map);
        map = new HashMap<>();
        map.put("pic",R.drawable.icon_certificate_verification);
        map.put("text","证书验证");
        listItems.add(map);
        mAdapter = new HomeSortAdapter(R.layout.item_home_sort,listItems);
        rcy.setLayoutManager(new GridLayoutManager(mActivity,4));
        rcy.setAdapter(mAdapter);
    }

    @Override
    public void responeError(String errorMsg) {

    }

    @OnClick(R.id.ll_more)
    public void onViewClicked() {
    }
}