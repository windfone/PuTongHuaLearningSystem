package com.fifedu.record.media.record;

public interface AudioRecordInterface {
    public void onVolume(int vol);
    public void onFinish(long audioLength, String audioPath);
}
