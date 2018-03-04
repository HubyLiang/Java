package com.liang.thread;

/**
 * 八锁问题
 *  1. 标准访问: 先打印IOS,后打印Android
 *  **注意: 从 2锁 以后,IOS线程都Thread.sleep(2000)**
 *  2. IOS 线程sleep(2000) :先打印IOS,后打印Android
 *          - 一个对象中,如果有多个synchronized 同步方法,某一个时刻内,只要有一个线程去调用synchronized同步方法了,
 *          其他的线程都只能等待, 即:某一时刻内,只能有唯一的线程去访问同一对象实例的synchronized同步方法,锁的对象
 *          是this.
 *  3. 新增非同步一个方法,是先打印 IOS 还是 Hello ? : Hello  IOS
 *          - 普通非同步方法与线程同步锁无关.
 *  4. 有两个Phone , 是先打印IOS 还是 Android?  : Android  IOS
 *          - 线程的非静态同步锁 锁的是实例对象本身.
 *          - 一个实例对象的非静态同步方法获取锁后,该对象的其他非静态同步方法必须等待获取锁的方法释放锁之后才能获取锁;
 *          - 但若是别的实例对象的非静态同步方法则无需等待锁的释放,因为不同实力对象之间使用的是不同的锁.
 *  5. 有两个静态同步方法,同一部手机,先打印IOS 还是 Android?  : IOS  Android
 *          - 所有静态同步方法锁 锁的是类对象本身.
 *  6. 两个静态同步方法,两部手机,是先打印IOS还是Android?  : IOS  Android
 *          - 一个静态同步方法获取到锁后,其他的静态同步方法都必须等待获得到锁的静态方法释放锁之后才可以获取锁,无论是否为
 *            同一个实力对象,只要是同一个类的实力对象都需要等待锁.
 *  7. 一个静态同步方法, 一个普通同步方法,一部手机,是先打印IOS 还是Android?  Android  IOS
 *          - 注意:静态同步锁和非静态同步锁是两个不同的对象,所以,静态同步方法和非静态同步方法之间不会有竞争关系.
 *  8. 一个静态同步方法,一个普通同步方法,两部手机, ...? Android IOS
 *
 */
public class EightLocks {

    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(new Runnable() {
            @Override
            public void run() {
                phone.getIOS();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
//                phone2.getAndroid();
                phone.hello();
            }
        }).start();

    }
}


class Phone {
    public static synchronized void getIOS() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("IOS");
    }

    public  synchronized void getAndroid() {
        System.out.println("Android");
    }

    public void hello(){
        System.out.println("Hello");
    }
}
