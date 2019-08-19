package com.fifedu.record.recinbox.bl.dal;

import com.fifedu.record.audio.ProcessType;
import com.fifedu.record.media.record.PcmRecorder;
import com.iflytek.audio.AudioProcess;
import com.iflytek.base.audio.AacEncoder;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 直接将音频写成 Speex 格式 File文件
 * @author Hong Yu hongyu@iflytek.com
 *
 * 2015年10月13日
 */
public class SpeexFileWriter extends FileWriter {

	private byte[] mOutBuffer;
	private static final int OUT_BUFFER_SIZE = 64000; // 输出缓存
	private int handle;

	@Override
	public void open(String filename) throws FileNotFoundException {
		super.open(filename);
	}

	/**
	 * 初始化 speex 音频转码器
	 * @param sampleRate
	 * @return
	 * @throws IOException
	 */
	public int init(int sampleRate) throws IOException {
		int bitRate = AacEncoder.BIT_RATE_32000;
		
		if (PcmRecorder.SAMPLE_RATE_32K == sampleRate) {
			bitRate = AacEncoder.BIT_RATE_64000;
		}

//		long startTime =System.currentTimeMillis();

		handle = AudioProcess.createInstance(ProcessType.SPEEX_ENC);

		AudioProcess.setParam(handle, "bitRate", bitRate);
		AudioProcess.setParam(handle, "sampleRate", sampleRate);
		AudioProcess.setParam(handle, "channels", AacEncoder.CHANNLE_ONE);

		int ret = AudioProcess.init(handle);
		if (ret != 0) {
			return -1;
		}
//		Log.e("yhtest", " pcm2spx init consume time is " + (System.currentTimeMillis() - startTime));
		mOutBuffer = new byte[OUT_BUFFER_SIZE];
		return ret;
	}
	public void appendPcmData(byte[] data, int length, boolean eof) {
		
		int ret = AudioProcess.processStream(handle, data, length, mOutBuffer, length, eof);
		if (ret > 0) {
			try {
				writeByteBlock(mOutBuffer, 0, ret);
			} catch (IOException e) {
			}
		}
	}

	@Override
	public void close() throws IOException {
		super.close();
		AudioProcess.unInit(handle);
		AudioProcess.destroyInstance(handle);
	}

}
