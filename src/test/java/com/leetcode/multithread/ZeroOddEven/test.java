package com.leetcode.multithread.ZeroOddEven;

import java.util.function.IntConsumer;

public class test {

    public static void main(String[] args) {
        CountDownLatchTest zeo = new CountDownLatchTest(6);
        new Thread(() -> {
            try {
                zeo.zero(new IntConsumer() {
                    public void accept(int value) {
                        System.out.print(value);
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                zeo.odd(new IntConsumer() {
                    public void accept(int value) {
                        System.out.print(value);
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                zeo.even(value -> System.out.print(value));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}