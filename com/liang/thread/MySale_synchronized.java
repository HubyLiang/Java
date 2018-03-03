package com.liang.thread;

/**
 * 题目:三个售票员卖票
 * synchronized: 锁提供了对资源的独占访问,一次只能有一个线程获得锁,对共享资源的所有访问都需要首先获得锁.
 * 线程 操作 资源类
 * 高内聚 + 低耦合
 */
public class MySale_synchronized{

    public static void main(String[] args) throws InterruptedException {

        Ticket ticket = new Ticket();

        Thread t1 = new Thread(ticket,"t0");
        t1.start();


        Thread t2 = new Thread(ticket,"t1");
        t2.start();

        Thread t3 = new Thread(ticket,"t2");
        t3.start();

    }

}

class Ticket implements Runnable {
    private int number = 300; //实例变量

    public void sale() {  //实例方法
        number--;

        System.out.println(Thread.currentThread().getName() + "  剩余票数 : " + number);
    }

    @Override
    public void run() {
        synchronized (Runtime.getRuntime()) {
            while (number > 0) {
                sale();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}