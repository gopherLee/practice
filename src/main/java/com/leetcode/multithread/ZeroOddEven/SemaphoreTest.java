package com.leetcode.multithread.ZeroOddEven;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class SemaphoreTest {
    //Semaphore是一个计数信号量。
    //从概念上将，Semaphore包含一组许可证。
    //如果有需要的话，每个acquire()方法都会阻塞，直到获取一个可用的许可证。
    //每个release()方法都会释放持有许可证的线程，并且归还Semaphore一个可用的许可证。
    //然而，实际上并没有真实的许可证对象供线程使用，Semaphore只是对可用的数量进行管理维护
    //总结：如果线程要访问一个资源就必须先获得信号量。
    // 如果信号量内部计数器大于0，信号量减1，然后允许共享这个资源；
    // 否则，如果信号量的计数器等于0，信号量将会把线程置入休眠直至计数器大于0.
    // 当信号量使用完时，必须释放

    private int n;
    private Semaphore zeroSema = new Semaphore(1);
    private Semaphore oddSema=new Semaphore(0);
    private Semaphore evenSema=new Semaphore(0);
    public SemaphoreTest(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for(int i=1;i<=n;i++){
            zeroSema.acquire();
            printNumber.accept(0);
            if ((i&1)==1){
                oddSema.release();
            }else{
                evenSema.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for(int i=2;i<=n;i=i+2){
            evenSema.acquire();
            printNumber.accept(i);
            zeroSema.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for(int i=1;i<=n;i=i+2){
            oddSema.acquire();
            printNumber.accept(i);
            zeroSema.release();
        }
    }
}