package com.hlxyedu.putonghualearningsystem.ui.essay.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.fifedu.record.media.record.RecordSettingAdapter;
import com.fifedu.record.recinbox.bl.dal.AacFileWriter;
import com.fifedu.record.recinbox.bl.dal.SpeexFileWriter;
import com.fifedu.record.recinbox.bl.record.IRecorderListener;
import com.fifedu.record.recinbox.bl.record.RecordParams;
import com.fifedu.record.recinbox.bl.record.RecorderManager;
import com.hlxyedu.putonghualearningsystem.R;
import com.hlxyedu.putonghualearningsystem.base.RootActivity;
import com.hlxyedu.putonghualearningsystem.model.bean.DataVO;
import com.hlxyedu.putonghualearningsystem.model.bean.DetailVO;
import com.hlxyedu.putonghualearningsystem.model.http.api.ApiConstants;
import com.hlxyedu.putonghualearningsystem.ui.essay.contract.EssayDetailsContract;
import com.hlxyedu.putonghualearningsystem.ui.essay.presenter.EssayDetailsPresenter;
import com.hlxyedu.putonghualearningsystem.utils.PermissionRequestUtil;
import com.hlxyedu.putonghualearningsystem.utils.PermissionSettingUtil;
import com.hlxyedu.putonghualearningsystem.utils.TUtils;
import com.hlxyedu.putonghualearningsystem.utils.TimeUtil;
import com.hlxyedu.putonghualearningsystem.weight.actionbar.MainTopBar;
import com.hlxyedu.putonghualearningsystem.weight.actionbar.XBaseTopBarImp;
import com.lzx.starrysky.manager.MediaSessionConnection;
import com.lzx.starrysky.manager.MusicManager;
import com.lzx.starrysky.manager.OnPlayerEventListener;
import com.lzx.starrysky.model.SongInfo;
import com.lzx.starrysky.utils.TimerTaskManager;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.skyworth.rxqwelibrary.app.AppConstants;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.UUID;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by zhangguihua
 */
public class EssayDetailsActivity extends RootActivity<EssayDetailsPresenter> implements EssayDetailsContract.View,
        XBaseTopBarImp, OnPlayerEventListener, IRecorderListener {

    private static final int MSG_START_RECORD = 10;
    private static final int MSG_STOP_RECORD = 11;
    private static final int MSG_VOLUME = 12;

    private static final int MSG_START_OK = 13;
    private static final int MSG_START_ERROR = 14;
    private static final int MSG_FINISH = 15;

    private static final String TAG = EssayDetailsActivity.class.getSimpleName();

    @BindView(R.id.main_topbar)
    MainTopBar main_topbar;
    @BindView(R.id.pre_iv)
    ImageView pre_iv;
    @BindView(R.id.essay_name_tv)
    TextView essay_name_tv;
    @BindView(R.id.next_iv)
    ImageView next_iv;
    @BindView(R.id.play_iv)
    ImageView play_iv;
    @BindView(R.id.play_length_tv)
    TextView play_length_tv;
    @BindView(R.id.total_time_tv)
    TextView total_time_tv;
    @BindView(R.id.seek_bar_volume)
    SeekBar seek_bar_volume;
    @BindView(R.id.essay_tv)
    TextView essay_tv;
    @BindView(R.id.record_ll)
    RelativeLayout record_ll;
    @BindView(R.id.record_tv)
    TextView record_tv;
    @BindView(R.id.record_play_tv)
    TextView record_play_tv;
    @BindView(R.id.record_play_ll)
    RelativeLayout record_play_ll;
    @BindView(R.id.record_play_iv)
    ImageView record_play_iv;

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

    private TimerTaskManager timerTaskManager;

    private String audioUrl; // 请求到的音频 URL ，写为全局的
    private ArrayList<DataVO> lists = new ArrayList<>();

    private boolean isPlayRecord;

    /**
     * 打开新Activity
     *
     * @param context
     * @return
     */
    public static Intent newInstance(Context context, int pos, ArrayList<DataVO> dataVOS) {
        Intent intent = new Intent(context, EssayDetailsActivity.class);

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("dataVOS", dataVOS);
        bundle.putSerializable("pos", pos);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_essay_details;
    }

    @Override
    protected void initEventAndData() {
        main_topbar.setxBaseTopBarImp(this);
        // 音频服务连接
        MediaSessionConnection.getInstance().connect();

        // 获取录音组件实例
        mRecorder = RecorderManager.getInstance();
        mRecordHandler = new RecordHandler(this);

        //1.需要创建录音文件夹
        LogUtils.d(TAG, "创建录音文件夹");
        FileUtils.createOrExistsDir(AppConstants.RECORD_PATH);

        getIntentData();

        //初始化音频管理器
        AudioManager mAudioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        //获取系统最大音量
        int maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        // 获取设备当前音量
        int currentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        // 设置seekbar的最大值
        seek_bar_volume.setMax(maxVolume);
        // 显示音量
        seek_bar_volume.setProgress(currentVolume);
        seek_bar_volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                MusicManager.getInstance().setVolume(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        timerTaskManager = new TimerTaskManager();
        timerTaskManager.setUpdateProgressTask(new Runnable() {
            @Override
            public void run() {
                if (!isPlayRecord) {
                    long position = MusicManager.getInstance().getPlayingPosition();
                    play_length_tv.setText(TUtils.formatMusicTime(position));
                }
            }
        });
        MusicManager.getInstance().addPlayerEventListener(this);

        mPresenter.getEssayDetails(lists.get(pos).getName());
    }

    private void getIntentData() {
        play_iv.setEnabled(false);
        lists = getIntent().getParcelableArrayListExtra("dataVOS");

        pos = getIntent().getIntExtra("pos", 0);
        essay_name_tv.setText("《" + lists.get(pos).getName().substring(0, lists.get(pos).getName().lastIndexOf("."))
                + "》" + "示范朗读");
    }

    @Override
    public void responeError(String errorMsg) {

    }

    @Override
    public void onDetailsSuccess(DetailVO detailVO) {
        play_iv.setEnabled(true);
        pre_iv.setEnabled(true);
        next_iv.setEnabled(true);
        String essayTxt = "";
        for (int i = 0; i < detailVO.getTxtData().length; i++) {
            essayTxt += "        " + detailVO.getTxtData()[i] + "\n";
        }
        essay_tv.setText(essayTxt);
        audioUrl = detailVO.getAudioUrl();
        total_time_tv.setText("/" + TimeUtil.getTimeString(detailVO.getAudioLength()));
    }

    // ********************** 录音部分 ************************** //
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
            record_tv.setText("结束");
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
            record_tv.setText("录音");
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
            recordPath = AppConstants.RECORD_PATH + "audio_" + lists.get(pos).getName().substring(0, lists.get(pos).getName().lastIndexOf("."))
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

    @OnClick({R.id.pre_iv, R.id.next_iv, R.id.play_iv, R.id.record_ll, R.id.record_play_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pre_iv:
                if (!mRecorder.isRecording()) {
                    if (pos == 0) {
                        ToastUtils.showShort("已经是第一个了");
                    } else {
                        pre_iv.setEnabled(false);
                        MusicManager.getInstance().pauseMusic();
                        onPlayerPause();
                        pos--;
                        essay_name_tv.setText("《" + lists.get(pos).getName().substring(0, lists.get(pos).getName().lastIndexOf("."))
                                + "》" + "示范朗读");
                        play_length_tv.setText("00:00");
                        play_iv.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_pause));

                        mPresenter.getEssayDetails(lists.get(pos).getName());
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
                        essay_name_tv.setText("《" + lists.get(pos).getName().substring(0, lists.get(pos).getName().lastIndexOf("."))
                                + "》" + "示范朗读");
                        play_length_tv.setText("00:00");
                        play_iv.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_pause));

                        mPresenter.getEssayDetails(lists.get(pos).getName());
                    }
                } else {
                    ToastUtils.showShort("请先停止录音");
                }
                break;
            case R.id.play_iv:
                if (!mRecorder.isRecording()) {
                    if (MusicManager.getInstance().isPlaying()) {
                        MusicManager.getInstance().pauseMusic();
                        onPlayerPause();
                        play_iv.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_pause));
                    } else {
                        playAudio();
                        play_iv.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_pause));
                    }
                } else {
                    ToastUtils.showShort("正在录音，不能播放");
                }
                break;
            case R.id.record_ll:
                if (MusicManager.getInstance().isPlaying()) {
                    ToastUtils.showShort("请先停止播放朗读");
                } else {
                    if (mRecorder.isRecording()) {
                        mRecordHandler.sendEmptyMessage(MSG_STOP_RECORD);
                    } else {
//                        checkPermissions();
                        PermissionRequestUtil.getInstance().checkPermissions(this);
                    }
                }
                break;
            case R.id.record_play_ll:
                if (!mRecorder.isRecording()) {
                    if (StringUtils.isEmpty(recordPath)) {
                        ToastUtils.showShort("您还没有录制音频");
                    } else {
                        if (isPlayRecord) {
                            MusicManager.getInstance().pauseMusic();
                            record_play_iv.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_record_play));
                            record_play_tv.setText("播放");
                        } else {
                            playRecordAudio();
                            record_play_iv.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_record_pause));
                            record_play_tv.setText("暂停");
                        }
                        isPlayRecord = !isPlayRecord;
                    }
                } else {
                    ToastUtils.showShort("正在录音，不能播放");
                }
                break;
        }
    }
    // ********************** 录音部分 ************************** //

    private void playAudio() {
        SongInfo songInfo = new SongInfo();
        songInfo.setSongId(lists.get(pos).getId());
        songInfo.setSongUrl(ApiConstants.HOST + audioUrl);
        MusicManager.getInstance().playMusicByInfo(songInfo);
    }

    private void playRecordAudio() {
        SongInfo songInfo = new SongInfo();
        songInfo.setSongId(String.valueOf(UUID.randomUUID()));
        songInfo.setSongUrl(recordPath);
//        songInfo.setSongUrl(audioUrl);
        MusicManager.getInstance().playMusicByInfo(songInfo);
    }

    @Override
    public void left() {
        MusicManager.getInstance().stopMusic();
        finish();
    }

    @Override
    public void right() {

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
        // 判断是播放的录音还是 示范朗读，便于设置 图标及 文字
        if (isPlayRecord) {
            MusicManager.getInstance().pauseMusic();
            record_play_iv.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_record_play));
            record_play_tv.setText("播放");
            isPlayRecord = !isPlayRecord;
        } else {
            timerTaskManager.stopToUpdateProgress();
            play_iv.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_pause));
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
    public void onBackPressed() {
        MusicManager.getInstance().stopMusic();
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timerTaskManager.removeUpdateProgressTask();
        MusicManager.getInstance().removePlayerEventListener(this);

        MediaSessionConnection.getInstance().disconnect();
    }

    @SuppressLint("CheckResult")
    private void checkPermissions() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.setLogging(true);
        rxPermissions
                .requestEach(Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.RECORD_AUDIO)
                .subscribe(permission -> { // will emit 2 Permission objects
                    if (permission.granted) {
                        // 权限同意
                        recordSecond();
                        ToastUtils.showShort("开始录音");

                    } else if (permission.shouldShowRequestPermissionRationale) {
                        // Denied permission without ask never again
                        // 禁止，但没有选择“以后不再询问”，以后申请权限，会继续弹出提示
                        showRequestReason();
                    } else {
                        // Denied permission with ask never again
                        // Need to go to the settings
                        // 禁止，但选择“以后不再询问”，以后申请权限，不会继续弹出提示
                        // 需要到 设置里面 手动打开
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("权限设置");
                        builder.setMessage("需要同意录音、读取手机存储信息、获取手机状态信息权限才能使用录音功能");
                        builder.setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                PermissionSettingUtil.gotoPermission(EssayDetailsActivity.this);
                            }
                        });
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.show();
                    }
                });
    }

    /**
     * 拒绝权限后显示请求权限原因并再次申请
     */
    private void showRequestReason() {
        WindowManager windowManager = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();

        DialogPlus logoutDialog = DialogPlus.newDialog(this)
                .setGravity(Gravity.CENTER)
                .setContentHolder(new ViewHolder(R.layout.dialog_logout))
//                        .setContentBackgroundResource(R.drawable.dialog_write_corner_bg)
                .setContentWidth((int) (display
                        .getWidth() * 0.8))
                .setContentHeight(LinearLayout.LayoutParams.WRAP_CONTENT)
                .setCancelable(true)//设置不可取消   可以取消
                .setOnClickListener((dialog, view1) -> {
                    switch (view1.getId()) {
                        case R.id.btn_neg:
                            dialog.dismiss();
                            break;
                        case R.id.btn_pos:
//                            checkPermissions()
                            PermissionRequestUtil.getInstance().checkPermissions(this);
                            ;
                            dialog.dismiss();
                            break;
                    }
                }).create();
        TextView textView = (TextView) logoutDialog.findViewById(R.id.txt_msg);
        textView.setText(getResources().getString(R.string.permission));
        logoutDialog.show();
    }

    public static class RecordHandler extends Handler {
        private EssayDetailsActivity mForm;

        public RecordHandler(EssayDetailsActivity activity) {
            mForm = activity;
        }

        @Override
        public void dispatchMessage(Message msg) {
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