package com.liang.thread;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁: 1个线程写,100个线程读
 * synchronized 是独占排它锁,类似于mysql数据库的表锁
 * ReentrantReadWriteLock 在控制上更为精细
 */
public class ThreadDemo6 {
    public static void main(String[] args) {
        final MyQueue myQueue = new MyQueue();

        //写线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                myQueue.write(new Random().nextInt(100));
            }
        }, "AA").start();

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //读线程
        for (int i = 0; i < 100; i++) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    myQueue.read();
                }
            }, String.valueOf(i)).start();
        }

    }
}


class MyQueue {
    private Object object;
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();


    public void read() {
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t" + object);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public void write(Object object) {
        rwLock.writeLock().lock();

        try {
            this.object = object;
            System.out.println(Thread.currentThread().getName() + "\t" + "write over");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
        }

    }
}
