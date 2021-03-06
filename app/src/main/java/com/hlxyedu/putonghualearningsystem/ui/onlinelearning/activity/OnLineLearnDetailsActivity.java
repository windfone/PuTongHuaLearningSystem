package com.hlxyedu.putonghualearningsystem.ui.onlinelearning.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.fifedu.record.media.record.RecordSettingAdapter;
import com.fifedu.record.recinbox.bl.dal.AacFileWriter;
import com.fifedu.record.recinbox.bl.dal.SpeexFileWriter;
import com.fifedu.record.recinbox.bl.record.IRecorderListener;
import com.fifedu.record.recinbox.bl.record.RecordParams;
import com.fifedu.record.recinbox.bl.record.RecorderManager;
import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootFragmentActivity;
import com.hlxyedu.putonghualearningsystem.base.RxBus;
import com.hlxyedu.putonghualearningsystem.model.bean.DataVO;
import com.hlxyedu.putonghualearningsystem.model.bean.DetailVO;
import com.hlxyedu.putonghualearningsystem.model.event.ActionEvent;
import com.hlxyedu.putonghualearningsystem.model.event.EssayTxtEvent;
import com.hlxyedu.putonghualearningsystem.model.event.HanZiEvent;
import com.hlxyedu.putonghualearningsystem.model.event.WordFollowEvent;
import com.hlxyedu.putonghualearningsystem.model.http.api.ApiConstants;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.contract.OnLineLearnDetailsContract;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment.DetailEssayFragment;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment.DetailHanZiFragment;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment.DetailWordFragment;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.presenter.OnLineLearnDetailsPresenter;
import com.hlxyedu.putonghualearningsystem.utils.PermissionRequestUtil;
import com.hlxyedu.putonghualearningsystem.utils.TUtils;
import com.hlxyedu.putonghualearningsystem.utils.TimeUtil;
import com.hlxyedu.putonghualearningsystem.utils.WeakReferenceHandler;
import com.hlxyedu.putonghualearningsystem.weight.actionbar.XBaseTopBar;
import com.hlxyedu.putonghualearningsystem.weight.actionbar.XBaseTopBarImp;
import com.lzx.starrysky.manager.MediaSessionConnection;
import com.lzx.starrysky.manager.MusicManager;
import com.lzx.starrysky.manager.OnPlayerEventListener;
import com.lzx.starrysky.model.SongInfo;
import com.lzx.starrysky.utils.TimerTaskManager;
import com.skyworth.rxqwelibrary.app.AppConstants;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhangguihua
 * 播放音频、录音界面
 */
public class OnLineLearnDetailsActivity extends RootFragmentActivity<OnLineLearnDetailsPresenter> implements OnLineLearnDetailsContract.View
        , OnPlayerEventListener, IRecorderListener, XBaseTopBarImp{

    private static final int MSG_START_RECORD = 10;
    private static final int MSG_STOP_RECORD = 11;
    private static final int MSG_VOLUME = 12;

    private static final int MSG_START_OK = 13;
    private static final int MSG_START_ERROR = 14;
    private static final int MSG_FINISH = 15;

    private static final String TAG = OnLineLearnDetailsActivity.class.getSimpleName();

    @BindView(R.id.xbase_topbar)
    XBaseTopBar xbase_topbar;
    @BindView(R.id.time_progress_tv)
    TextView time_progress_tv;
    @BindView(R.id.time_total_tv)
    TextView time_total_tv;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.top_play_iv)
    ImageView top_play_iv;
    @BindView(R.id.pre_iv)
    ImageView pre_iv;
    @BindView(R.id.next_iv)
    ImageView next_iv;
    @BindView(R.id.record_iv)
    ImageView record_iv;
    @BindView(R.id.record_tv)
    TextView record_tv;
    @BindView(R.id.record_ll)
    RelativeLayout record_ll;
    @BindView(R.id.record_play_tv)
    TextView record_play_tv;

    // 录音控件
    private RecorderManager mRecorder;
    private Handler mRecordHandler;
    // aac音频文件
    private AacFileWriter mAacFile;
    // speex 音频文件
    private SpeexFileWriter mSpeexFile;
    //用户文件夹
    private String recordPath = "";

    private int pos; // 当前展示的是列表的第几行
    private String mTitles; // 当前展示的是列表的第几行
    private String itemStr; // 点击的列表item 的内容，到详情需要显示出来
    private ArrayList<DataVO> lists = new ArrayList<>();
    private String audioUrl;// 请求到的音频 URL ，写为全局的

    private TimerTaskManager timerTaskManager;
    private boolean isPlayRecord;
    private int audioLength;
    private long position;
    private long progress;// 播放到哪里了

    /**
     * 打开新Activity
     *
     * @param context
     * @return
     */
    public static <T> Intent newInstance(Context context, int pos, ArrayList<T> dataVO, String title, String itemStr) {
        Intent intent = new Intent(context, OnLineLearnDetailsActivity.class);

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("dataVO", (ArrayList<? extends Parcelable>) dataVO);
        bundle.putSerializable("pos", pos);
        bundle.putSerializable("title", title);
        bundle.putSerializable("itemStr", itemStr);
        intent.putExtras(bundle);
        return intent;
    }

    private void getIntentData() {
        lists = getIntent().getParcelableArrayListExtra("dataVO");
        pos = getIntent().getIntExtra("pos", 0);
        mTitles = getIntent().getStringExtra("title");
        itemStr = getIntent().getStringExtra("itemStr");
        xbase_topbar.setMiddleText(mTitles);
    }

    private void initTitleAndFragment() {
        switch (mTitles) {
            case "短文跟读":
            case "拼音学习":
                loadRootFragment(R.id.content_container, DetailEssayFragment.newInstance(itemStr));
                break;
            case "单词跟读":
                loadRootFragment(R.id.content_container, DetailWordFragment.newInstance());
                break;
            case "汉字学习":
            case "轻声字":
            case "儿化音":
                loadRootFragment(R.id.content_container, DetailHanZiFragment.newInstance());
                break;
        }
    }

    /* 退出时直接退出Activity，防止只退出 fragment*/
    @Override
    public void onBackPressedSupport() {
        MusicManager.getInstance().stopMusic();
        super.onBackPressedSupport();
        finish();
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_on_line_learn_details;
    }

    @Override
    protected void initEventAndData() {
        xbase_topbar.setxBaseTopBarImp(this);
        getIntentData();
        initTitleAndFragment();

        // 音频服务连接
        MediaSessionConnection.getInstance().connect();

        // 获取录音组件实例
        mRecorder = RecorderManager.getInstance();
        mRecordHandler = new RecordHandler(this);

        //1.需要创建录音文件夹
        LogUtils.d(TAG, "创建录音文件夹");
        FileUtils.createOrExistsDir(AppConstants.RECORD_PRACTICE_PATH);

        timerTaskManager = new TimerTaskManager();
        timerTaskManager.setUpdateProgressTask(new Runnable() {
            @Override
            public void run() {
                if (!isPlayRecord) {
                    position = MusicManager.getInstance().getPlayingPosition();
                    try {
                        progressBar.setProgress((int) (100 * (position / 1000) / audioLength));
                        time_progress_tv.setText(TUtils.formatMusicTime(position));
                        progress = position;
                    }catch (ArithmeticException exception){
//                        ToastUtils.showShort("获取音频时长错误");
                    }
                }
            }
        });
        MusicManager.getInstance().addPlayerEventListener(this);

        getNetDatas();
    }

    private void getNetDatas() {
        DataVO dataVO = lists.get(pos);
        switch (mTitles) {
            case "拼音学习":
                mPresenter.getPinYinDetails(dataVO.getConId(),dataVO.getPinYinOrder());
                break;
            case "单词跟读":
                mPresenter.getWordFollowDetails(dataVO.getConId(),dataVO.getPinYinOrder());
                break;
            case "短文跟读":
                mPresenter.getShortEssayDetails(dataVO.getConId(),dataVO.getPinYinOrder());
                break;
            case "汉字学习":
            case "轻声字":
            case "儿化音":
                mPresenter.getHanZiDetails(dataVO.getConId(),dataVO.getPinyin(),dataVO.getPinYinOrder());
                break;
        }
    }

    // 拼音学习
    @Override
    public void onPinYinDetailsSuccess(DetailVO detailVO) {
        top_play_iv.setEnabled(true);
        pre_iv.setEnabled(true);
        next_iv.setEnabled(true);
        String title = lists.get(pos).getConTitle();
        RxBus.getDefault().post(new EssayTxtEvent(detailVO.getConDetail(), title));
        audioUrl = detailVO.getAudioUrl();
        audioLength = detailVO.getAudioLength();
        time_total_tv.setText(TimeUtil.getTimeString(audioLength));
    }

    // 短文跟读
    @Override
    public void onShortEssayDetailsSuccess(DetailVO detailVO) {
        top_play_iv.setEnabled(true);
        pre_iv.setEnabled(true);
        next_iv.setEnabled(true);
        /*String essayTxt = "";
        for (int i = 0; i < detailVO.getTxtData().length; i++) {
            essayTxt += "        " + detailVO.getTxtData()[i] + "\n";
        }*/
        String title = lists.get(pos).getConTitle();
        /*RxBus.getDefault().post(new EssayTxtEvent(detailVO.getConDetail(),
                "《" + title.substring(0, title.lastIndexOf(".")) + "》示范朗读"));*/
        RxBus.getDefault().post(new EssayTxtEvent(detailVO.getConDetail(), title + "示范朗读"));
        audioUrl = detailVO.getAudioUrl();
        audioLength = detailVO.getAudioLength();
        time_total_tv.setText(TimeUtil.getTimeString(audioLength));
    }

    @Override
    public void onWordFollowDetailsSuccess(List<DetailVO> detailVOS) {
        top_play_iv.setEnabled(true);
        pre_iv.setEnabled(true);
        next_iv.setEnabled(true);
        String title = lists.get(pos).getConTitle();
        /*RxBus.getDefault().post(new EssayTxtEvent(detailVO.getConDetail(),
                "《" + title.substring(0, title.lastIndexOf(".")) + "》示范朗读"));*/
        RxBus.getDefault().post(new WordFollowEvent(detailVOS));
        // 由于详情是多条数据，这里设置默认点击播放第一条选中的
        audioUrl = detailVOS.get(0).getAudioUrl();
        audioLength = detailVOS.get(0).getAudioLength();
        time_total_tv.setText(TimeUtil.getTimeString(audioLength));
    }

    // 汉字 轻声字 儿化音
    @Override
    public void onHanZiDetailsSuccess(DetailVO detailVO) {
        top_play_iv.setEnabled(true);
        pre_iv.setEnabled(true);
        next_iv.setEnabled(true);
//        RxBus.getDefault().post(new HanZiEvent(detailVO.getPinyin(), detailVO.getPinYinCN(),detailVO.getVideoUrl()));
        RxBus.getDefault().post(new HanZiEvent(detailVO.getPinyin(), detailVO.getWordImg(),detailVO.getVideoUrl()));
        audioUrl = detailVO.getAudioUrl();
        audioLength = detailVO.getAudioLength();
        time_total_tv.setText(TimeUtil.getTimeString(audioLength));
    }

    @Override
    public void responeError(String errorMsg) {

    }

    @OnClick({R.id.top_play_iv, R.id.pre_iv, R.id.next_iv, R.id.record_ll, R.id.record_play_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.top_play_iv:
                if (!mRecorder.isRecording()) {
                    if (MusicManager.getInstance().isPlaying()) {
                        // 如果正在播放录音，还点击了上边的 接口请求的拼音播放按钮，则 提示暂停播放录音或者 等待 录音播放完成再播放
                        if (isPlayRecord){
                            record_play_tv.setText("播放");
                            playAudio();
                            top_play_iv.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_play));
                            isPlayRecord = false;
                        }else {
                            MusicManager.getInstance().pauseMusic();
                            onPlayerPause();
                            top_play_iv.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_pause));
                        }
                    } else {
                        playAudio();
                        top_play_iv.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_play));
                        isPlayRecord = false;
                    }
                } else {
                    ToastUtils.showShort("正在录音，不能播放");
                }
                break;
            case R.id.pre_iv:
                if (!mRecorder.isRecording()) {
                    if (pos == 0) {
                        ToastUtils.showShort("已经是第一个了");
                    } else {
                        pre_iv.setEnabled(false);
                        MusicManager.getInstance().pauseMusic();
                        onPlayerPause();
                        pos--;
                        time_progress_tv.setText(getResources().getString(R.string.time_zero));
                        top_play_iv.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_pause));

                        recordPath = "";
                        record_play_tv.setTextColor(getResources().getColor(R.color.graya9a));
                        record_play_tv.setBackground(getResources().getDrawable(R.drawable.shape_bg_gray_4dp));

//                        mPresenter.getShortEssayDetails(lists.get(pos).getName());
                        getNetDatas();
                        RxBus.getDefault().post(new ActionEvent(ActionEvent.INITVIEW));
                    }
                } else {
                    ToastUtils.showShort("请先停止录音");
                }
                break;
            case R.id.next_iv:
                if (!mRecorder.isRecording()) {
                    if (pos == lists.size() - 1) {
                        ToastUtils.showShort("已经是最后一个了");
                    } else {
                        next_iv.setEnabled(false);
                        MusicManager.getInstance().pauseMusic();
                        onPlayerPause();
                        pos++;
                        time_progress_tv.setText(getResources().getString(R.string.time_zero));
                        top_play_iv.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_pause));

                        recordPath = "";
                        record_play_tv.setTextColor(getResources().getColor(R.color.graya9a));
                        record_play_tv.setBackground(getResources().getDrawable(R.drawable.shape_bg_gray_4dp));

//                        mPresenter.getShortEssayDetails(lists.get(pos).getName());
                        getNetDatas();
                        RxBus.getDefault().post(new ActionEvent(ActionEvent.INITVIEW));
                    }
                } else {
                    ToastUtils.showShort("请先停止录音");
                }
                break;
            case R.id.record_ll:
                if (MusicManager.getInstance().isPlaying()) {
                    ToastUtils.showShort("请先停止播放当前音频");
                } else {
                    if (mRecorder.isRecording()) {
                        mRecordHandler.sendEmptyMessage(MSG_STOP_RECORD);
                    } else {
//                        checkPermissions();
                        PermissionRequestUtil.getInstance().checkPermissions(this);
                    }
                }
                break;
            case R.id.record_play_tv:
                if (!mRecorder.isRecording()) {
                    if (StringUtils.isEmpty(recordPath)) {
                        ToastUtils.showShort("您还没有录制音频");
                    } else {
                        if (MusicManager.getInstance().isPlaying()) {
                            if (isPlayRecord){
                                MusicManager.getInstance().pauseMusic();
                                record_play_tv.setText("播放");
                            }else {
                                top_play_iv.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_pause));

                                playRecordAudio();
                                record_play_tv.setText("暂停");
                                isPlayRecord = true;
                            }
                        } else {
                            playRecordAudio();
                            record_play_tv.setText("暂停");
                            isPlayRecord = true;
                        }
                    }
                } else {
                    ToastUtils.showShort("正在录音，不能播放");
                }

                break;
        }
    }

    @Override
    public void playAudio() {
        SongInfo songInfo = new SongInfo();
//        songInfo.setSongId(lists.get(pos).getId());
        songInfo.setSongId(String.valueOf(UUID.randomUUID()));
        songInfo.setSongUrl(ApiConstants.HOST + audioUrl);

        try {
            MusicManager.getInstance().playMusicByInfo(songInfo);
        }catch (IllegalArgumentException e){
            ToastUtils.showShort("音频播放错误");
        }
        if (progress != 0){
            MusicManager.getInstance().seekTo(progress);
        }
    }

    @Override
    public void playWordAudio(DetailVO detailVO) {
        if (!mRecorder.isRecording()) {
            audioUrl = detailVO.getAudioUrl();
            audioLength = detailVO.getAudioLength();
            time_total_tv.setText(TimeUtil.getTimeString(audioLength));
            playAudio();
            top_play_iv.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_play));
            progressBar.setProgress(0);
        } else {
            ToastUtils.showShort("正在录音，不能播放");
        }
    }

    private void playRecordAudio() {
        SongInfo songInfo = new SongInfo();
        songInfo.setSongId(String.valueOf(UUID.randomUUID()));
        songInfo.setSongUrl(recordPath);
//        songInfo.setSongUrl(audioUrl);
        MusicManager.getInstance().playMusicByInfo(songInfo);
    }

    @Override
    public void onMusicSwitch(SongInfo songInfo) {

    }

    @Override
    public void onPlayerStart() {
        timerTaskManager.startToUpdateProgress();
    }

    @Override
    public void onPlayerPause() {
        timerTaskManager.stopToUpdateProgress();
    }

    @Override
    public void onPlayerStop() {
        timerTaskManager.stopToUpdateProgress();
    }

    @Override
    public void onPlayCompletion(SongInfo songInfo) {
        // 判断是播放的录音还是 请求的音频，便于设置 图标及 文字
        if (isPlayRecord) {
            MusicManager.getInstance().pauseMusic();
            record_play_tv.setText("播放");
            isPlayRecord = false;
        } else {
            timerTaskManager.stopToUpdateProgress();
            top_play_iv.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_pause));
        }
    }

    @Override
    public void onBuffering() {

    }

    @Override
    public void onError(int errorCode, String errorMsg) {
        timerTaskManager.stopToUpdateProgress();
        ToastUtils.showShort("播放失败");
    }

    @Override
    protected void onDestroy() {
        mRecordHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
        timerTaskManager.removeUpdateProgressTask();
        MusicManager.getInstance().removePlayerEventListener(this);

        MediaSessionConnection.getInstance().disconnect();
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

            record_play_tv.setTextColor(getResources().getColor(R.color.whitefff));
            record_play_tv.setBackground(getResources().getDrawable(R.drawable.shape_bg_blue_4dp));
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
//            recordPath = AppConstants.RECORD_PRACTICE_PATH + "audio_" + lists.get(pos).getName().substring(0, lists.get(pos).getName().lastIndexOf("."))
//                    + AppConstants.AUDIO_FILE_SUFFIX;
            // 这里音频只是练习用，不用保存，所以录音文件可以覆盖
            // 每次录音前先将上次录的音频删除
            FileUtils.delete(AppConstants.RECORD_PRACTICE_PATH + "audio_" + "learning" + AppConstants.AUDIO_FILE_SUFFIX);
            recordPath = AppConstants.RECORD_PRACTICE_PATH + "audio_" + "learning"
                    + AppConstants.AUDIO_FILE_SUFFIX;
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
    }

    // ********************** 录音部分 ************************** //
    @Override
    public void left() {
        MusicManager.getInstance().stopMusic();
        finish();
    }

    @Override
    public void right() {

    }

    public static class RecordHandler extends WeakReferenceHandler<OnLineLearnDetailsActivity> {
//        private WeakReference<OnLineLearnDetailsActivity> reference;

        public RecordHandler(OnLineLearnDetailsActivity activity) { super(activity); }

       /* public RecordHandler(OnLineLearnDetailsActivity activity) {
            mForm = activity;
        } */

        /*public RecordHandler(OnLineLearnDetailsActivity activity) {
            reference = new WeakReference<>(activity);
        }*/

        @Override
        public void dispatchMessage(Message msg) {
            OnLineLearnDetailsActivity mForm;
//            mForm = reference.get();
            mForm = getRef();
            if (mForm != null){
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
}