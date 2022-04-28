package com.leetcode.multithread.ZeroOddEven;

import java.util.concurrent.CountDownLatch;
import java.util.function.IntConsumer;

public class CountDownLatchTest {
    private int n;
    //CountDownLatch这个类使一个线程等待其他线程各自执行完毕后再执行。
    //
    //是通过一个计数器来实现的，计数器的初始值是线程的数量。
    // 每当一个线程执行完毕后，计数器的值就-1，
    // 当计数器的值为0时，表示所有线程都执行完毕，
    // 然后在闭锁上等待的线程就可以恢复工作了
    public CountDownLatchTest(int n) {
        this.n = n;
    }
    private CountDownLatch zeroLatch = new CountDownLatch(0);
    private CountDownLatch oddLatch = new CountDownLatch(1);
    private CountDownLatch evenLatch = new CountDownLatch(1);
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            zeroLatch.await();
            printNumber.accept(0);
            zeroLatch = new CountDownLatch(1);
            if ((i & 1) == 1) oddLatch.countDown();
            else evenLatch.countDown();
        }
    }
    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <=n; i=i+2) {
            oddLatch.await();
            printNumber.accept(i);
            oddLatch=new CountDownLatch(1);
            zeroLatch.countDown();
        }
    }
    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <=n; i=i+2) {
            evenLatch.await();
            printNumber.accept(i);
            evenLatch=new CountDownLatch(1);
            zeroLatch.countDown();
        }
    }
}
