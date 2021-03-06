package com.hlxyedu.putonghualearningsystem.ui.exam.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.fifedu.record.media.record.RecordSettingAdapter;
import com.fifedu.record.recinbox.bl.dal.AacFileWriter;
import com.fifedu.record.recinbox.bl.dal.SpeexFileWriter;
import com.fifedu.record.recinbox.bl.record.IRecorderListener;
import com.fifedu.record.recinbox.bl.record.RecordParams;
import com.fifedu.record.recinbox.bl.record.RecorderManager;
import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootActivity;
import com.hlxyedu.putonghualearningsystem.base.RootFragmentActivity;
import com.hlxyedu.putonghualearningsystem.model.bean.ExamCenterVO;
import com.hlxyedu.putonghualearningsystem.model.bean.PaperContentVO;
import com.hlxyedu.putonghualearningsystem.ui.exam.contract.ExamDetailContract;
import com.hlxyedu.putonghualearningsystem.ui.exam.fragment.EChineseCharacterFragment;
import com.hlxyedu.putonghualearningsystem.ui.exam.fragment.EScoreFragment;
import com.hlxyedu.putonghualearningsystem.ui.exam.fragment.EShortEssayFragment;
import com.hlxyedu.putonghualearningsystem.ui.exam.fragment.EWordFragment;
import com.hlxyedu.putonghualearningsystem.ui.exam.fragment.EZuoWenFragment;
import com.hlxyedu.putonghualearningsystem.ui.exam.presenter.ExamDetailPresenter;
import com.hlxyedu.putonghualearningsystem.utils.MyFragmentPagerAdapter;
import com.hlxyedu.putonghualearningsystem.utils.PermissionRequestUtil;
import com.hlxyedu.putonghualearningsystem.utils.WeakReferenceHandler;
import com.hlxyedu.putonghualearningsystem.weight.actionbar.XBaseTopBar;
import com.hlxyedu.putonghualearningsystem.weight.actionbar.XBaseTopBarImp;
import com.hlxyedu.putonghualearningsystem.weight.topbar.XTopBar;
import com.hlxyedu.putonghualearningsystem.weight.viewpager.NoTouchViewPager;
import com.skyworth.rxqwelibrary.app.AppConstants;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhangguihua
 */
public class ExamDetailActivity extends RootFragmentActivity<ExamDetailPresenter> implements ExamDetailContract.View, XBaseTopBarImp, IRecorderListener {

    private static final int MSG_START_RECORD = 10;
    private static final int MSG_STOP_RECORD = 11;
    private static final int MSG_VOLUME = 12;

    private static final int MSG_START_OK = 13;
    private static final int MSG_START_ERROR = 14;
    private static final int MSG_FINISH = 15;

    private static final String TAG = ExamDetailActivity.class.getSimpleName();

    @BindView(R.id.xbase_topbar)
    XBaseTopBar xbase_topbar;
    @BindView(R.id.x_topbar)
    XTopBar x_topbar;
    @BindView(R.id.notouch_viewpager)
    NoTouchViewPager notouch_viewpager;
    @BindView(R.id.record_iv)
    ImageView record_iv;
    @BindView(R.id.record_tv)
    TextView record_tv;
    @BindView(R.id.exam_top_rl)
    RelativeLayout exam_top_rl;
    @BindView(R.id.record_ll)
    RelativeLayout record_ll;
    @BindView(R.id.next_question_tv)
    TextView next_question_tv;
    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.score_tv)
    TextView score_tv;
    @BindView(R.id.time_progress_tv)
    TextView time_progress_tv;
    @BindView(R.id.exam_number_tv)
    TextView exam_number_tv;
    @BindView(R.id.next_set_of_papers_tv)
    TextView next_set_of_papers_tv;

    // 录音控件
    private RecorderManager mRecorder;
    private Handler mRecordHandler;
    // aac音频文件
    private AacFileWriter mAacFile;
    // speex 音频文件
    private SpeexFileWriter mSpeexFile;
    // 用户文件夹
    private String recordPath = "";
    // 录音文件夹
    private String userDir = "";

    private int pos;
    private ArrayList<ExamCenterVO> examCenterVOS;
    //fragment 数组
    private ArrayList<Fragment> fragments;

    /**
     * 打开新Activity
     *
     * @param context
     * @return
     */
    public static Intent newInstance(Context context, int pos, ArrayList<ExamCenterVO> examCenterVOS) {
        Intent intent = new Intent(context, ExamDetailActivity.class);
        intent.putExtra("pos", pos);
        intent.putParcelableArrayListExtra("examCenterVOS", examCenterVOS);
        return intent;
    }

    private void getIntentData() {
        Intent intent = getIntent();
        pos = intent.getIntExtra("pos", 0);
        examCenterVOS = intent.getParcelableArrayListExtra("examCenterVOS");
        title_tv.setText(examCenterVOS.get(pos).getPaperTitle());
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_exam_detail;
    }

    @Override
    protected void initEventAndData() {
        xbase_topbar.setxBaseTopBarImp(this);
        getIntentData();

        // 获取录音组件实例
        mRecorder = RecorderManager.getInstance();
        mRecordHandler = new RecordHandler(this);
        //1.需要创建这套题的答题包录音文件夹
        userDir = AppConstants.RECORD_EXAM_PATH
                + TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd"))
                + "&"
                + examCenterVOS.get(pos).getPaperNo();
        LogUtils.d(TAG, "创建录音文件夹");
        FileUtils.createOrExistsDir(userDir);

        fragments = new ArrayList<Fragment>();
//        ExamCenterVO examCenterVO = examCenterVOS.get(pos);
        ExamCenterVO examCenterVO = new ExamCenterVO();
        examCenterVO.setPaperTitle("第一套题");
        examCenterVO.setPaperNo("01");

        List<PaperContentVO> lists = new ArrayList<>();
        PaperContentVO contentVO = new PaperContentVO();
        contentVO.setSontitle("汉字练习");
        contentVO.setType(1);
        contentVO.setAnswerTime(60);
        List<String> strings = Arrays.asList("哈","蛤","蛤","蛤","蛤","蛤","蛤","蛤","蛤","蛤","蛤","蛤","蛤","蛤","蛤","蛤","蛤","蛤","蛤","蛤","蛤","蛤","蛤","蛤","蛤","蛤","蛤","蛤","蛤","蛤","蛤");
        contentVO.setSonContent(strings);
        lists.add(contentVO);
        examCenterVO.setPaperContent(lists);
        for (int i = 0; i < examCenterVO.getPaperContent().size(); i++) {
            PaperContentVO paperContent = examCenterVO.getPaperContent().get(i);
            switch (paperContent.getType()) {
                case 0:
                    fragments.add(EChineseCharacterFragment.newInstance(paperContent.getSonContent(), paperContent.getSontitle(),paperContent.getAnswerTime()));
                    break;
                case 1:
                    fragments.add(EWordFragment.newInstance());
                    break;
                case 2:
                    fragments.add(EShortEssayFragment.newInstance());
                    break;
                case 3:
                    fragments.add(EZuoWenFragment.newInstance());
                    break;
            }
        }
        fragments.add(EScoreFragment.newInstance());

        //初始化
        notouch_viewpager.setNoScroll(true);
        notouch_viewpager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments));

    }

    @Override
    public void nextPage() {
        if (notouch_viewpager.getCurrentItem() != fragments.size() - 1) {
            int step = notouch_viewpager.getCurrentItem() + 1;
            //最后一页不能 下一步
            notouch_viewpager.setCurrentItem(step);
        }

        if (notouch_viewpager.getCurrentItem() == fragments.size() - 2){
            next_question_tv.setText("交卷");
        }
        if (notouch_viewpager.getCurrentItem() == fragments.size() - 1){
            exam_top_rl.setVisibility(View.GONE);
            score_tv.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void xTopBarSecond(int second,String problemTitle) {
        exam_number_tv.setText(problemTitle);
        x_topbar.init();
        x_topbar.setTotal(second);
        x_topbar.start();
    }

    @Override
    public void responeError(String errorMsg) {

    }

    @OnClick({R.id.record_ll, R.id.next_question_tv, R.id.next_set_of_papers_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.record_ll:
                if (mRecorder.isRecording()) {
                    mRecordHandler.sendEmptyMessage(MSG_STOP_RECORD);
                } else {
                    PermissionRequestUtil.getInstance().checkPermissions(this);
                }
                break;
            case R.id.next_question_tv:
                pos++;

                break;
            case R.id.next_set_of_papers_tv:
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

    // ********************** 录音部分 ************************** //
    @Override
    public void recordSecond() {
        //开启录音
        mRecordHandler.sendEmptyMessage(MSG_START_RECORD);
    }

    /**
     * 录音开启
     */
    private void onStartRecord() {
        // 创建Recorder
        if (!mRecorder.isRecording()) {
            RecordParams params = new RecordParams();
            mRecorder.startRecord(this, params, RecordSettingAdapter.getInstance());
            record_tv.setVisibility(View.GONE);
            record_iv.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 停止录音
     *
     * @param msg
     * @param isClick
     */
    private void onStopRecord(String msg, boolean isClick) {
        if (isClick) {
            mRecorder.stopRecord(this);
            record_iv.setVisibility(View.GONE);
            record_tv.setVisibility(View.VISIBLE);
            ToastUtils.showShort("录音完成");
        } else {
            mRecorder.stopRecord(null);
        }
    }

    /**
     * 录音结束
     */
    public void onMsgFinish() {
//        record_view.setVisibility(View.GONE);
//        waveview.clear();
        ToastUtils.showShort("录音完成");
    }

    public void onMsgError(int arg1) {
        LogUtils.d(TAG, "onMsgError", "---->onMsgError");
        onStopRecord("开始录音出错", false);
        ToastUtils.showShort("录音发生错误");
    }

    public void onMsgStart(RecordParams params) {
//        mBeginTime = System.currentTimeMillis();
    }

    @Override
    public boolean onStart(RecordParams params) {
        Message msg = mRecordHandler.obtainMessage(MSG_START_OK);
        try {
            if (null != mAacFile) {
                mAacFile.close();
            }
            mAacFile = new AacFileWriter();
//            String file = getAudioFile();
            String problemNo = (pos + 1) > 9 ? (pos + 1) + "" : ("0" + (pos + 1));
            recordPath = userDir + File.separator + problemNo + AppConstants.AUDIO_FILE_SUFFIX;
            mAacFile.open(recordPath);
            mAacFile.init(params.getSampleRate());

            if (null != mSpeexFile) {
                mSpeexFile.close();
            }

            params.setFileId(recordPath);
            msg.obj = params;
            mRecordHandler.sendMessage(msg);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @Override
    public void onError(RecordParams params, int errorCode) {
        if (null != mAacFile) {
            try {
                mAacFile.close();
            } catch (IOException e) {
                LogUtils.d("", "", e.toString());
            }
            mAacFile = null;
        }

        if (null != mSpeexFile) {
            try {
                mSpeexFile.close();
            } catch (IOException e) {
                LogUtils.d("", "", e.toString());
            }
            mSpeexFile = null;
        }
        Message msg = mRecordHandler.obtainMessage(MSG_START_ERROR);
        msg.arg1 = errorCode;
        mRecordHandler.sendMessage(msg);
    }

    @Override
    public int onRecordData(byte[] data) {
        if (null != mAacFile) {
            mAacFile.appendPcmData(data, data.length);
        }
        if (null != mSpeexFile) {
            mSpeexFile.appendPcmData(data, data.length, false);
        }

        //add weidingqiang 将byte[] 转成 short[]  类型
        short[] shorts = new short[data.length / 2];
        ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(shorts);
        //进行显示
//        waveformView.setSamples(shorts);

        return data.length;
    }

    @Override
    public void onRecordInterrupt() {

    }

    @Override
    public void onFinished(RecordParams params) {
        if (null != mAacFile) {
            try {
                mAacFile.close();
            } catch (IOException e) {
                LogUtils.d("", "", e.toString());
            }
            mAacFile = null;
        }
        if (null != mSpeexFile) {
            try {
                // 结束录音
                byte[] data = {};
                mSpeexFile.appendPcmData(data, 0, true);
                mSpeexFile.close();
            } catch (IOException e) {
                LogUtils.d("", "", e.toString());
            }
            mSpeexFile = null;
        }
        Message msg = mRecordHandler.obtainMessage(MSG_FINISH);
        mRecordHandler.sendMessage(msg);
        /* 关于屏幕分辨率计算问题
        1.比如分辨率为320 × 480，则长宽比为1:1.5
        2.比如屏幕尺寸为3.6”，则根据勾股定理有：
        高2 + 宽2 = 3.62，
        又因为，高 = 1.5 × 宽，代入上式，有：
        宽2 + 2.25 × 宽2 = 12.96，
        得出，宽 = (12.96/3.25)1/2 = 1.9969
        3.宽为320px，分布在1.9969”上，因此密度为320 / 1.9969 = 160.2467
        4.因此此密度约为mdpi的密度

        屏幕密度对应关系是:
            mdpi    480*320     120dpi ~ 160dpi
            hdpi    800*400     160dpi ~ 240dpi
            xhdpi   1280*720    240dpi ~ 320dpi
            xxhdpi  1920*1280   320dpi ~ 480dpi
            xxxhdpi 2560*1440   480dpi ~ 640dpi
            所以图片的放置需要先计算出设计图 给的dpi，选择对应的图片放入对应drawable 文件夹

        现在大多数手机屏幕密度是xxhdpi，所以drawable-xxhdpi文件夹下的图片就是最适合的图片。
        当你引用这张图时，如果drawable-xxhdpi文件夹下有这张图就会优先被使用，在这种情况下，图片是不会被缩放的。但是，
        如果drawable-xxhdpi文件夹下没有这张图时， 系统就会自动去其它文件夹下找这张图了，优先会去更高密度的文件夹下找这张图片，
        比如你放到了drawable-xxxhdpi文件夹，假如3x文件夹里也没有这张图，接下来会尝试再找更高密度的文件夹，发现没有更高密度的了，
        这个时候会去drawable-nodpi文件夹找这张图，发现也没有，那么就会去更低密度的文件夹下面找，
        依次是drawable-xhdpi -> drawable-hdpi -> drawable-mdpi -> drawable-ldpi。
        大概匹配规则就是这吧，比如说现在在drawable-xxxhdpi文件夹下面找到这张图了，它会认为这张图是为更高密度的设备所设计的，
        如果直接将这张图在当前xxhdpi的设备上使用就会出现像素过高的情况，会自动做一个缩小的操作
        如果你把这个图放到了mdpi里面，系统会认为这张图是专门为低密度的设备所设计的，如果直接将这张图在当前的高密度设备上使用就有
        可能会出现像素过低的情况，就会自动做一个放大操作。
        */

    }

    // ********************** 录音部分 ************************** //

    public static class RecordHandler extends WeakReferenceHandler<ExamDetailActivity> {

        public RecordHandler(ExamDetailActivity activity) { super(activity); }
        /*private ExamDetailActivity mForm;

        public RecordHandler(ExamDetailActivity activity) {
            mForm = activity;
        }*/

        @Override
        public void dispatchMessage(Message msg) {
            ExamDetailActivity mForm;
            mForm = getRef();
            switch (msg.what) {
                case MSG_START_RECORD:
                    mForm.onStartRecord();
                    break;
                case MSG_STOP_RECORD:
                    mForm.onStopRecord("录音停止", true);
                    break;
                case MSG_VOLUME:
//                    mForm.onVolumeUi(msg.arg1);
                    break;
                case MSG_FINISH:
                    mForm.onMsgFinish();
                    break;
                case MSG_START_ERROR:
                    mForm.onMsgError(msg.arg1);
                    break;
                case MSG_START_OK:
                    mForm.onMsgStart((RecordParams) msg.obj);
                    break;
            }
        }
    }

}