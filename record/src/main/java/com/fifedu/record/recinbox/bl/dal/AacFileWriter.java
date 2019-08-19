package com.fifedu.record.recinbox.bl.dal;

import com.fifedu.record.media.record.PcmRecorder;
import com.iflytek.base.audio.AacEncoder;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 使用AAC保存音频的示例类
 * 
 * @author zhangyun
 * @date 2015-3-11
 * 
 */
public class AacFileWriter extends FileWriter {
    private byte[] mOutBuffer;
    private static final int OUT_BUFFER_SIZE = 64000; // 输出缓存

    @Override
    public void open(String filename) throws FileNotFoundException {
        super.open(filename);

    }

    public void init(int sampleRate) throws IOException {
        int bitRate = AacEncoder.BIT_RATE_32000;
        if (PcmRecorder.SAMPLE_RATE_32K == sampleRate) {
            bitRate = AacEncoder.BIT_RATE_64000;
        }
        AacEncoder.init(AacEncoder.CHANNLE_ONE, sampleRate, bitRate);
//        DebugLog.d("Aac", "init " + sampleRate);
        mOutBuffer = new byte[OUT_BUFFER_SIZE];

    }

    public void appendPcmData(byte[] data, int length) {
        int ret = AacEncoder.encodeData(data, length, mOutBuffer,
                OUT_BUFFER_SIZE);
        if (ret > 0) {
            try {
                writeByteBlock(mOutBuffer, 0, ret);
            } catch (IOException e) {
//                DebugLog.d("", "writeData error", e);
            }
        }
    }

    @Override
    public void close() throws IOException {
        super.close();
        AacEncoder.finish();
    }

}
