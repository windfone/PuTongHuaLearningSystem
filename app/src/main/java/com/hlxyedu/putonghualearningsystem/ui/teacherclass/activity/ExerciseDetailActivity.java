package com.hlxyedu.putonghualearningsystem.ui.teacherclass.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootActivity;
import com.hlxyedu.putonghualearningsystem.model.bean.CommentVO;
import com.hlxyedu.putonghualearningsystem.model.bean.DataVO;
import com.hlxyedu.putonghualearningsystem.model.bean.VideoVO;
import com.hlxyedu.putonghualearningsystem.model.http.api.ApiConstants;
import com.hlxyedu.putonghualearningsystem.ui.teacherclass.adapter.CommentAdapter;
import com.hlxyedu.putonghualearningsystem.ui.teacherclass.contract.ExerciseDetailContract;
import com.hlxyedu.putonghualearningsystem.ui.teacherclass.presenter.ExerciseDetailPresenter;
import com.hlxyedu.putonghualearningsystem.weight.jzvd.JZMediaExo;
import com.hlxyedu.putonghualearningsystem.weight.jzvd.JzvdStd;
import com.hlxyedu.putonghualearningsystem.weight.actionbar.XBaseTopBar;
import com.hlxyedu.putonghualearningsystem.weight.actionbar.XBaseTopBarImp;
import com.hlxyedu.putonghualearningsystem.weight.dialog.InputTextMsgDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.Jzvd;

/**
 * Created by zhangguihua
 */
public class ExerciseDetailActivity extends RootActivity<ExerciseDetailPresenter> implements ExerciseDetailContract.View, XBaseTopBarImp {

    @BindView(R.id.xbase_topbar)
    XBaseTopBar xbase_topbar;
   /* @BindView(R.id.play_iv)
    ImageView play_iv;*/
    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.no_comment_tv)
    TextView no_comment_tv;
    @BindView(R.id.headportrait_iv)
    ImageView headportrait_iv;
    @BindView(R.id.teacher_name_tv)
    TextView teacher_name_tv;
    @BindView(R.id.company_tv)
    TextView company_tv;
    @BindView(R.id.read_num_tv)
    TextView read_num_tv;
    @BindView(R.id.rcy)
    RecyclerView rcy;
    @BindView(R.id.comment_tv)
    TextView comment_tv;
    @BindView(R.id.jz_video)
    JzvdStd jz_video;

    private CommentAdapter mAdapter;

    private List<CommentVO> commentVOList = new ArrayList<>();
    private int pageSize = 20;
    private int count = 1; // 当前页数;

    /**
     * 打开新Activity
     *
     * @param context
     * @return
     */
    public static Intent newInstance(Context context, String title, VideoVO videoVO) {
        Intent intent = new Intent(context, ExerciseDetailActivity.class);
        intent.putExtra("title",title);
        intent.putExtra("item",videoVO);
        return intent;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_exercise_detail;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initEventAndData() {
        xbase_topbar.setxBaseTopBarImp(this);
        xbase_topbar.setMiddleText(getIntent().getStringExtra("title"));
        xbase_topbar.setRightIv(ContextCompat.getDrawable(this,R.drawable.share));
        xbase_topbar.setRightListener(() -> {
            ToastUtils.showShort("点击分享");
        });
        VideoVO videoVO = getIntent().getParcelableExtra("item");
        title_tv.setText(videoVO.getTeaTitle());
        read_num_tv.setText("阅读量：" + videoVO.getBrowseNum());

        mAdapter = new CommentAdapter(R.layout.item_comment,commentVOList);
        rcy.setLayoutManager(new LinearLayoutManager(this));
        rcy.setAdapter(mAdapter);

        count = 1;
        if (!commentVOList.isEmpty()) {
            commentVOList.clear();
        }
        mPresenter.getCommentList(videoVO.getTeaId(),count,pageSize);

        mAdapter.setPreLoadNumber(1);
        mAdapter.setOnLoadMoreListener(() -> {
            count++;
            mPresenter.getCommentList(videoVO.getTeaId(),count,pageSize);
        }, rcy);

//        jz_video.setUp(ApiConstants.HOST + videoVO.getTeaVideoUrl(), "");
        jz_video.setUp(ApiConstants.HOST + videoVO.getTeaVideoUrl(),"",JzvdStd.SCREEN_NORMAL, JZMediaExo.class);
        // 只是一种描述，也可以使用 glide picasso等加载封面图，根据项目自己需求
//        Glide.with(this).load(Uri.parse("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640")).into(jz_video.thumbImageView);
//        jz_video.thumbImageView.setImageURI(Uri.parse("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640"));
        // 设置充满全屏
        jz_video.setVideoImageDisplayType(JzvdStd.VIDEO_IMAGE_DISPLAY_TYPE_FILL_SCROP);
    }

    @Override
    public void responeError(String errorMsg) {

    }

    @Override
    public void onCommentSuccess(List<CommentVO> commentVOS) {
        if (!commentVOS.isEmpty()) {
            no_comment_tv.setVisibility(View.GONE);

            commentVOList.addAll(commentVOS);
            mAdapter.setNewData(commentVOList);
            if (commentVOS.size() < pageSize) {
                mAdapter.loadMoreEnd();
            } else {
                mAdapter.loadMoreComplete();
            }
        } else {
            if (count == 1) {
                no_comment_tv.setVisibility(View.VISIBLE);
            } else {
                mAdapter.loadMoreEnd();
            }
        }
    }

    @OnClick({R.id.comment_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            case R.id.play_iv:
//                break;
            case R.id.comment_tv:
                InputTextMsgDialog msgDialog = new InputTextMsgDialog(this,R.style.dialog_center);
                msgDialog.setmOnTextSendListener(new InputTextMsgDialog.OnTextSendListener() {
                    @Override
                    public void onTextSend(String msg) {
                        //点击发送按钮后，回调此方法，msg为输入的值

                    }
                });
                msgDialog.show();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
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
//        Jzvd.releaseAllVideos();
        //home back
        Jzvd.goOnPlayOnPause();
    }

    @Override
    public void left() {
        finish();
    }

    @Override
    public void right() {

    }
}