package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootFragment;
import com.hlxyedu.putonghualearningsystem.base.RxBus;
import com.hlxyedu.putonghualearningsystem.model.event.ActionEvent;
import com.hlxyedu.putonghualearningsystem.model.http.api.ApiConstants;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.contract.DetailHanZiContract;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.presenter.DetailHanZiPresenter;
import com.hlxyedu.putonghualearningsystem.weight.jzvd.CustomUIJzvdStd;
import com.hlxyedu.putonghualearningsystem.weight.jzvd.JZMediaExo;
import com.hlxyedu.putonghualearningsystem.weight.jzvd.JzvdStd;
import com.hlxyedu.putonghualearningsystem.weight.jzvd.JzvdStdImp;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.Jzvd;

/**
 * Created by zhangguihau
 */
public class DetailHanZiFragment extends RootFragment<DetailHanZiPresenter> implements DetailHanZiContract.View, JzvdStdImp {


    @BindView(R.id.pinyin_tv)
    TextView pinyin_tv;
    /*@BindView(R.id.cn_tv)
    TextView cn_tv;*/
    @BindView(R.id.word_iv)
    ImageView word_iv;
    @BindView(R.id.jz_video)
    CustomUIJzvdStd jz_video;
    @BindView(R.id.video_ll)
    LinearLayout video_ll;
    @BindView(R.id.pinyin_btn)
    Button pinyin_btn;
    @BindView(R.id.bishun_btn)
    Button bishun_btn;

    private String videoUrl, wordImg;

    public static DetailHanZiFragment newInstance() {
        Bundle args = new Bundle();

        DetailHanZiFragment fragment = new DetailHanZiFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_hanzi;
    }

    @Override
    protected void initEventAndData() {
        jz_video.setJzvdStdImp(this);

    }

    @Override
    public void responeError(String errorMsg) {

    }

    @Override
    public void setContentText(String pinYin, String img,String url) {
        pinyin_tv.setText(pinYin);
//        cn_tv.setText(hanZi);
        Glide.with(this).load(ApiConstants.HOST + img).into(word_iv);
        String s = ApiConstants.HOST + wordImg;
        videoUrl = url;
        wordImg = img;
    }

    @Override
    public void switchInitView() {
        video_ll.setVisibility(View.GONE);
//        cn_tv.setVisibility(View.VISIBLE);
        word_iv.setVisibility(View.VISIBLE);
        Jzvd.releaseAllVideos();
    }

    @OnClick({R.id.pinyin_btn, R.id.bishun_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pinyin_btn:
                video_ll.setVisibility(View.GONE);
//                cn_tv.setVisibility(View.VISIBLE);
                word_iv.setVisibility(View.VISIBLE);
                Jzvd.releaseAllVideos();
                RxBus.getDefault().post(new ActionEvent(ActionEvent.PLAYAUDIO));
                break;
            case R.id.bishun_btn:
                video_ll.setVisibility(View.VISIBLE);
//                cn_tv.setVisibility(View.GONE);
                word_iv.setVisibility(View.GONE);
//                jz_video.setUp(ApiConstants.HOST + videoUrl,"",JzvdStd.SCREEN_NORMAL, JZMediaIjk.class);
                jz_video.setUp(ApiConstants.HOST + videoUrl,"", JzvdStd.SCREEN_NORMAL, JZMediaExo.class);
                jz_video.startVideo();
                // 只是一种描述，也可以使用 glide picasso等加载封面图，根据项目自己需求
                /*RequestOptions options = new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true);*/
//                Glide.with(this).load(ApiConstants.HOST + wordImg).into(jz_video.thumbImageView);
                Glide.with(this).load(ApiConstants.HOST + wordImg).into(jz_video.thumbImageView);
//                jz_video.thumbImageView.setImageURI(Uri.parse("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640"));
                // 设置充满全屏
//                jz_video.setVideoImageDisplayType(JzvdStd.VIDEO_IMAGE_DISPLAY_TYPE_FILL_SCROP);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //home back
        Jzvd.goOnPlayOnResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
//        home back
//        Jzvd.goOnPlayOnPause();
    }

    @Override
    public void videoComplete() {
        video_ll.setVisibility(View.GONE);
//                cn_tv.setVisibility(View.VISIBLE);
        word_iv.setVisibility(View.VISIBLE);
        Jzvd.releaseAllVideos();
    }

    @Override
    public void videoError() {

    }

    @Override
    public void videoBack() {

    }

    @Override
    public void videoPause() {

    }
}