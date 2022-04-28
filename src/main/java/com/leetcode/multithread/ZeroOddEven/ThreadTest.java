package com.leetcode.multithread.ZeroOddEven;

import java.util.function.IntConsumer;

public class ThreadTest {
    //Thread.yield():使当前线程从执行状态（运行状态）变为可执行态（就绪状态）。
    // cpu会从众多的可执行态里选择，
    // 也就是说，当前也就是刚刚的那个线程还是有可能会被再次执行到的，
    // 并不是说一定会执行其他线程而该线程在下一次中不会执行到了。
    private int n;
    private ThreadTest(int n){
        this.n=n;
    }

    private volatile int state;
    private volatile boolean control=true;

    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (state != 0) {
                Thread.yield();
            }
            printNumber.accept(0);
            if (control) {
                state = 1;
            } else {
                state = 2;
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            while (state != 2) {//当state不为2的时候，为就绪状态
                Thread.yield();
            }
            printNumber.accept(i);
            control = true;
            state = 0;
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            while (state != 1) {
                Thread.yield();
            }
            printNumber.accept(i);
            control = false;
            state = 0;
        }
    }
}
