package com.fifedu.record.app;


import android.util.Log;

/**
 * 由灵犀工具类同步过来的线程基类
 * 
 * @author zhangyun
 * @date 2014-12-30
 */
public abstract class BaseThread extends Thread {

    protected boolean running = false;

    @Override
    public final void start() {
        running = true;

        super.start();
    }

    public final void stop(int nMillSecond) {
        if (!running) {
            return;
        }
        running = false;

        super.interrupt();

    }

    public boolean isRunning() {
        if (running && isAlive()) {
            return true;
        } else {
            return false;
        }
    }

    boolean initInstance() {
        return true;
    }

    int exitInstance() {
        return 0;
    }

    protected abstract void threadProc();

    static public final void sleep(int nMillSecond) {
        int nCount = nMillSecond / 20 + 1;
        for (int i = 0; i < nCount; i++) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                Log.d("", e.toString());
            }
        }
    }

    public final void Sleep(int nMillSecond) {
        int nCount = nMillSecond / 20 + 1;
        for (int i = 0; i < nCount && running; i++) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                Log.d("", e.toString());
            }
        }
    }

    @Override
    public void run() {
        if (initInstance()) {
            threadProc();
        }
        int ret = exitInstance();
        Log.d(getClass().getSimpleName(), "thread " + getName()
                + " Exit with code:" + ret);
    }
}
