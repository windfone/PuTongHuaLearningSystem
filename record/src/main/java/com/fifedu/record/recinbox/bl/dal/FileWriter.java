package com.fifedu.record.recinbox.bl.dal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author wcsu 写文件基类
 * 
 */
public abstract class FileWriter {
    private RandomAccessFile out;

    public void open(String filename) throws FileNotFoundException {
        out = new RandomAccessFile(filename, "rw");
    }

    public void seekTo(long pos) throws IOException {
        out.seek(pos);
    }

    public long getLength() throws IOException {
        return out.length();
    }

    /**
     * Writes a byte.
     * 
     * @param v
     *            - the value to write.
     * @exception IOException
     */
    protected void writeByte(byte v) throws IOException {
        out.write(v);
    }

    protected void writeShort(short v) throws IOException {
        out.write((0xff & v));
        out.write((0xff & (v >>> 8)));
    }

    protected void writeInt(int v) throws IOException {
        out.write(0xff & v);
        out.write(0xff & (v >>> 8));
        out.write(0xff & (v >>> 16));
        out.write(0xff & (v >>> 24));
    }

    protected void writeByteBlock(byte[] v, int offset, int length)
            throws IOException {
        out.write(v, offset, length);
    }

    public void close() throws IOException {
        out.close();
    }
}
