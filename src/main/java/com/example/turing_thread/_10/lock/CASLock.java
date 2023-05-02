package com.example.turing_thread._10.lock;

import com.example.turing_thread._06.jucdemo.factory.UnsafeFactory;
import sun.misc.Unsafe;

/**
 * ＣＡＳ
 * @author Fox
 */
public class CASLock {

    //加锁标记
    private volatile int state;
    private static final Unsafe UNSAFE;
    private static final long OFFSET;

    static {
        try {
            UNSAFE = UnsafeFactory.getUnsafe();
            OFFSET = UnsafeFactory.getFieldOffset(
                    UNSAFE, CASLock.class, "state");
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public boolean cas() {
        return UNSAFE.compareAndSwapInt(this, OFFSET, 0, 1);
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

}