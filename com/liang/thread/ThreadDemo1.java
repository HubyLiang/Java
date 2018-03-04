package com.liang.thread;

/**
 * 写出两个线程,一个线程打印1~52,另一个线程打印字母A-Z,打印顺序为12A34B56C...5152Z
 * 多线程要点: **线程  操作  资源类**
 * 高内聚  +  低耦合
 */
public class ThreadDemo1 {
    public static void main(String[] args) {

        ShareDemo shareDemo = new ShareDemo();

        //打印数字的线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 52; i++) {
                    try {
                        shareDemo.printNumbers();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        //打印字母的线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 26; i++) {
                    try {
                        shareDemo.printLetters();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}


/**
 * 资源类
 */
class ShareDemo {

    private int index = 1;
    int number = 1;
    char letter = 'A';

    public synchronized void printLetters() throws InterruptedException {

        while (index % 3 != 0) {
            this.wait();
        }
        index++;
        System.out.print(letter++);
        this.notifyAll();
    }

    public synchronized void printNumbers() throws InterruptedException {
        while (index % 3 == 0) {
            this.wait();
        }
        index++;
        System.out.print(number++);
        this.notifyAll();
    }

}
