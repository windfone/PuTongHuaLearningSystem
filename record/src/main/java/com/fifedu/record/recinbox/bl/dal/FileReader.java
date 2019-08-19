package com.fifedu.record.recinbox.bl.dal;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author wcsu
 * @date 2015-1-16 增加异常判断
 * 
 */
public abstract class FileReader {
    protected static final String TAG = "Record_FileReader";
    private RandomAccessFile mFile;
    private byte[] mBuffer;

    public void open(String fileName) throws IOException {
        if (null == fileName) {
            return;
        }
        mFile = new RandomAccessFile(fileName, "r");
    }

    public void close() {
        try {
            if (null != mFile) {
                mFile.close();
                mFile = null;
                mBuffer = null;
            }
        } catch (IOException e) {
//            DebugLog.d(TAG, "", e);
        }
    }

    public void seekTo(long offset) throws IOException {
        if (null == mFile) {
            throw new IOException();
        }
        mFile.seek(offset);
    }

    public long fileLenth() throws IOException {
        if (null == mFile) {
            throw new IOException();
        }
        return mFile.length();
    }

    protected byte readerByte() throws IOException {
        if (null == mFile) {
            throw new IOException();
        }
        return mFile.readByte();
    }

    protected short readShort() throws IOException {
        if (null == mFile) {
            throw new IOException();
        }
        byte[] bytes = new byte[2];
        mFile.read(bytes, 0, 2);
        return readShort(bytes, 0);
    }

    protected int readInt() throws IOException {
        if (null == mFile) {
            throw new IOException();
        }
        byte[] bytes = new byte[4];
        mFile.read(bytes, 0, 4);
        return readInt(bytes, 0);
    }

    protected byte[] readByteBlock(int Bocksize) throws IOException {
        if (null == mFile) {
            throw new IOException();
        }
        if (null == mBuffer || mBuffer.length != Bocksize) {
            mBuffer = new byte[Bocksize];
        }
        int result = mFile.read(mBuffer, 0, Bocksize);
        if (result <= 0) {
            return null;
        }

        if (result < mBuffer.length) {
            // 把后面的数据清空;
            for (int i = result; i < mBuffer.length; i++) {
                mBuffer[i] = 0;
            }
        }
        return mBuffer;
    }

    private int readInt(final byte[] data, final int offset) {

        return (data[offset] & 0xff) | ((data[offset + 1] & 0xff) << 8)
                | ((data[offset + 2] & 0xff) << 16) | (data[offset + 3] << 24);
    }

    protected short readShort(final byte[] data, final int offset) {

        int result = (data[offset] & 0xff) | (data[offset + 1] << 8);
        return (short) result;
    }
}
