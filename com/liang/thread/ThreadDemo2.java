package com.liang.thread;

/**
 * 两个线程交替操作一个变量,该变量的初始值为0,要求一个加1, 一个减1, 共10轮.
 * <p>
 * 生产者/消费者模式
 * <p>
 * 1. 线程  操作  资源类
 * 2. 高内聚 + 低耦合
 */
public class ThreadDemo2 {

    public static void main(String[] args) {

        final ShareDemo shareDemo = new ShareDemo();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0;i<10;i++){
                    try {
                        shareDemo.increment();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"Increment").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10;i++){
                    try {
                        shareDemo.decrement();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"Decrement").start();
    }

}


class ShareDemo {
    private int number = 0;

    public synchronized void increment() throws InterruptedException {
        if (number != 0) {
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        if (number == 0) {
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        this.notifyAll();
    }

}
