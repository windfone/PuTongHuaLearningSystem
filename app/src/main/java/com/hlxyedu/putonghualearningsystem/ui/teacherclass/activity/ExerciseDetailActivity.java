package com.hlxyedu.putonghualearningsystem.ui.teacherclass.activity;

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
import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootActivity;
import com.hlxyedu.putonghualearningsystem.model.bean.CommentVO;
import com.hlxyedu.putonghualearningsystem.ui.teacherclass.adapter.CommentAdapter;
import com.hlxyedu.putonghualearningsystem.ui.teacherclass.contract.ExerciseDetailContract;
import com.hlxyedu.putonghualearningsystem.ui.teacherclass.presenter.ExerciseDetailPresenter;
import com.hlxyedu.putonghualearningsystem.utils.JzvdStd;
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
    @BindView(R.id.title_iv)
    TextView title_iv;
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
    /**
     * 打开新Activity
     *
     * @param context
     * @return
     */
    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, ExerciseDetailActivity.class);
        return intent;
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_exercise_detail;
    }

    @Override
    protected void initEventAndData() {
        xbase_topbar.setxBaseTopBarImp(this);
        xbase_topbar.setMiddleText("");
        xbase_topbar.setRightIv(ContextCompat.getDrawable(this,R.drawable.share));
        xbase_topbar.setRightListener(() -> {
            ToastUtils.showShort("点击分享");
        });

        List<CommentVO> lists = new ArrayList<>();
        lists.add(new CommentVO());
        lists.add(new CommentVO());
        lists.add(new CommentVO());
        lists.add(new CommentVO());
        mAdapter = new CommentAdapter(R.layout.item_comment,lists);
        rcy.setLayoutManager(new LinearLayoutManager(this));
        rcy.setAdapter(mAdapter);

        jz_video.setUp("http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4"
                , "饺子闭眼睛");
        // 只是一种描述，也可以使用 glide picasso等加载封面图，根据项目自己需求
        jz_video.thumbImageView.setImageURI(Uri.parse("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640"));
        // 设置充满全屏
        jz_video.setVideoImageDisplayType(JzvdStd.VIDEO_IMAGE_DISPLAY_TYPE_FILL_SCROP);
    }

    @Override
    public void responeError(String errorMsg) {

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
    public void left() {
        finish();
    }

    @Override
    public void right() {

    }
}