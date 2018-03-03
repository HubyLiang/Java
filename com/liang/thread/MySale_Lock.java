package com.liang.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * java.util.concurrent.locks.Lock 取代 synchronized关键字
 * 线程 调用 资源类
 * 高内聚 + 低耦合
 * 如何编写多线程程序的工程代码的编辑方式
 * 线程操纵资源类, 则需要这个资源类把所有synchronized同步方法全部自己提供,对外暴露给线程调用.
 */
public class MySale_Lock {

    public static void main(String[] args){

        final Stamp stamp = new Stamp();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++){
                    stamp.sale();
                }
            }
        },"AA").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40;i++){
                    stamp.sale();
                }
            }
        },"BB").start();

        new Thread(new Runnable(){
            @Override
            public void run(){
                for (int i = 0; i < 40;i++){
                    stamp.sale();
                }
            }
        },"CC").start();


    }
}



class Stamp{

    private static int number = 30;  //实例变量
    private Lock lock = new ReentrantLock();

    public void sale(){  //实例方法
        lock.lock();
        try {
            if (number > 0){
                System.out.println(Thread.currentThread() + " 卖出第:\t"+(number--)+"\t还剩下: "+number);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock(); // 加锁后一定要记得解锁,否则会报异常
        }
    }

}
