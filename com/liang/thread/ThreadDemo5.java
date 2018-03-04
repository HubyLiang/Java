package com.liang.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个线程,要求顺序访问进行协作配合
 * A打印5次-----> B打印10次-----> C打印15次
 * A打印5次-----> B打印10次-----> C打印15次
 * A打印5次-----> B打印10次-----> C打印15次
 * ...
 * 共计20轮,模拟上述情况.
 * <p>
 * 多线程编程要点:
 * 线程  操作  资源类
 * 高内聚  +  低耦合
 * 多线程判断用 while
 * 注意判断标志位
 */
public class ThreadDemo5 {

    public static void main(String[] args) {
        final ShareDemo5 shareDemo5 = new ShareDemo5();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 20; i++) {
                    shareDemo5.printA(i);
                }
            }
        },"AA").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1;i<=20;i++){
                    shareDemo5.printB(i);
                }
            }
        },"BB").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1;i<=20;i++){
                    shareDemo5.printC(i);
                    System.out.println();
                }
            }
        },"CC").start();
    }
}


class ShareDemo5 {

    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    public void printA(int totalCount) {
        lock.lock();
        try {
            // 1.判断 该谁干
            while (number != 1) {
                conditionA.await();
            }
            // 2. 干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t 总循环:" + totalCount);
            }
            // 3. 修改标志 + 通知唤醒
            number = 2;
            conditionB.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB(int totalCount) {
        lock.lock();
        try {
            // 1.判断 该谁干
            while (number != 2) {
                conditionB.await();
            }
            // 2. 干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t 总循环:" + totalCount);
            }
            // 3. 修改标志 + 通知唤醒
            number = 3;
            conditionC.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void printC(int totalCount) {
        lock.lock();
        try {
            // 1.判断 该谁干
            while (number != 3) {
                conditionC.await();
            }
            // 2. 干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t 总循环:" + totalCount);
            }
            // 3. 修改标志 + 通知唤醒
            number = 1;
            conditionA.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
