package com.hlxyedu.putonghualearningsystem.ui.home.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootActivity;
import com.hlxyedu.putonghualearningsystem.ui.home.contract.ExamRegistrationContract;
import com.hlxyedu.putonghualearningsystem.ui.home.presenter.ExamRegistrationPresenter;
import com.hlxyedu.putonghualearningsystem.weight.actionbar.XBaseTopBar;
import com.hlxyedu.putonghualearningsystem.weight.actionbar.XBaseTopBarImp;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhangguihua
 */
public class ExamRegistrationActivity extends RootActivity<ExamRegistrationPresenter> implements ExamRegistrationContract.View, XBaseTopBarImp {

    @BindView(R.id.xbase_topbar)
    XBaseTopBar xbase_topbar;
    @BindView(R.id.input_name_edit)
    EditText input_name_edit;
    @BindView(R.id.select_sex_tv)
    TextView select_sex_tv;
    @BindView(R.id.select_national_tv)
    TextView select_national_tv;
    @BindView(R.id.input_papers_edit)
    EditText input_papers_edit;
    @BindView(R.id.select_job_tv)
    TextView select_job_tv;
    @BindView(R.id.input_unit_edit)
    EditText input_unit_edit;
    @BindView(R.id.select_exam_level_tv)
    TextView select_exam_grade_tv;
    @BindView(R.id.upload_photo)
    TextView upload_photo;
    @BindView(R.id.take_pic_iv)
    ImageView take_pic_iv;
    @BindView(R.id.show_pic_iv)
    ImageView show_pic_iv;
    @BindView(R.id.query_btn)
    Button query_btn;

    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;

    private int checkedIndexSex = -1;
    private int checkedIndexNation = -1;
    private int checkedIndexJob = -1;
    private int checkedIndexExamLevel = -1;

    /**
     * 打开新Activity
     *
     * @param context
     * @return
     */
    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, ExamRegistrationActivity.class);
        return intent;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_exam_registration;
    }

    @Override
    protected void initEventAndData() {
        xbase_topbar.setxBaseTopBarImp(this);
        xbase_topbar.setRightIv(getResources().getDrawable(R.drawable.icon_explain));
        xbase_topbar.setRightListener(() -> {
            startActivity(RegistrationIntroductionActivity.newInstance(this));
        });
    }

    @Override
    public void responeError(String errorMsg) {

    }

    @Override
    public void left() {
        finish();
    }

    @Override
    public void right() {

    }

    @OnClick({R.id.select_sex_tv, R.id.select_national_tv, R.id.select_job_tv, R.id.select_exam_level_tv, R.id.take_pic_iv, R.id.query_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.select_sex_tv:
                showSexDialog();
                break;
            case R.id.select_national_tv:
                showNationDialog();
                break;
            case R.id.select_job_tv:
                showJobDialog();
                break;
            case R.id.select_exam_level_tv:
                showExamLevelDialog();
                break;
            case R.id.take_pic_iv:

                openCamera();

                break;
            case R.id.query_btn:
                startActivity(ExamRegistrationNextActivity.newInstance(this));
                break;
        }
    }

    public void openCamera() {
        PictureSelector.create(ExamRegistrationActivity.this)
                .openGallery(PictureMimeType.ofImage())
                .selectionMode(PictureConfig.SINGLE) //单选
                .previewImage(true)// 是否可预览图片
                .isCamera(true)// 是否显示拍照按钮
                .compress(false)// 是否压缩
//                .cropCompressQuality(90)// 输出图片质量
                .enableCrop(true)
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
//                .withAspectRatio(9, 16)
                .withAspectRatio(100, 145)
                .cropWH(100, 145)
                .isDragFrame(true)
                .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                .showCropGrid(true)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
//                .minimumCompressSize(500) // 小于500kb 的不压缩
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    private List<LocalMedia> selectList = new ArrayList<>();
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的

                    RequestOptions options = new RequestOptions()
                            .centerCrop()
                            .placeholder(R.color.gray7F7)
                            .diskCacheStrategy(DiskCacheStrategy.ALL);

                    Glide.with(ExamRegistrationActivity.this)
                            .load(selectList.get(0).getCutPath())
                            .apply(options)
                            .into(show_pic_iv);
                    take_pic_iv.setVisibility(View.GONE);
                    show_pic_iv.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

    private void showSexDialog() {
        final String[] items = new String[]{"男", "女"};
        new QMUIDialog.CheckableDialogBuilder(this)
                .setCheckedIndex(checkedIndexSex)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        select_sex_tv.setText(items[which]);
                        checkedIndexSex = which;
                        dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    private void showNationDialog() {
        final String[] items = new String[]{"汉", "维吾尔","哈萨克","柯尔克孜","回","回","回","回","回","回","回","回","回","回","回","回"};
        new QMUIDialog.CheckableDialogBuilder(this)
                .setCheckedIndex(checkedIndexNation)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        select_national_tv.setText(items[which] + "族");
                        checkedIndexNation = which;
                        dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    private void showJobDialog() {
        final String[] items = new String[]{"老师", "学生"};
        new QMUIDialog.CheckableDialogBuilder(this)
                .setCheckedIndex(checkedIndexJob)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        select_job_tv.setText(items[which]);
                        checkedIndexJob = which;
                        dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    private void showExamLevelDialog() {
        final String[] items = new String[]{"一级", "二级"};
        new QMUIDialog.CheckableDialogBuilder(this)
                .setCheckedIndex(checkedIndexExamLevel)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        select_exam_grade_tv.setText(items[which]);
                        checkedIndexExamLevel = which;
                        dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

}