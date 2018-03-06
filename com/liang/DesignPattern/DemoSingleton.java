package com.liang.DesignPattern;

/**
 * 常见的单例模式
 * 懒汉 + 饿汉
 */
public class DemoSingleton {

    static Singleton_Lazy s1 = null;
    static Singleton_Lazy s2 = null;

    static Singleton s3 = null;
    static Singleton s4 = null;

    static Singleton_DCL s5 = null;
    static Singleton_DCL s6 = null;

    public static void main(String[] args) {

        //懒汉单例模式测试
        new Thread(new Runnable() {
            @Override
            public void run() {
                s1 = Singleton_Lazy.getInstance();
                System.out.println(Thread.currentThread().getName()+" : "+s1.toString());
            }
        },"AA").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                s2 = Singleton_Lazy.getInstance();
                System.out.println(Thread.currentThread().getName()+" : "+s2.toString());
            }
        },"BB").start();

        //饿汉单例模式测试
        new Thread(new Runnable() {
            @Override
            public void run() {
                s3 = Singleton.getInstance();
                System.out.println(Thread.currentThread().getName()+" : "+s3.toString());
            }
        },"CC").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                s4 = Singleton.getInstance();
                System.out.println(Thread.currentThread().getName()+" : "+s4.toString());
            }
        },"DD").start();

        //DCL模式测试
        new Thread(new Runnable() {
            @Override
            public void run() {
                s5 = Singleton_DCL.getInstance();
                System.out.println(Thread.currentThread().getName()+" : "+s5.toString());
            }
        },"EE").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                s6 = Singleton_DCL.getInstance();
                System.out.println(Thread.currentThread().getName()+" : "+s6.toString());
            }
        },"FF").start();


    }
}

/**
 * 懒汉式,线程安全,但效率低下
 * 优点:第一次调用时才初始化,避免内存浪费
 * 缺点:必须加同步锁才能保证单例,但加锁影响效率
 */
class Singleton_Lazy{
    private static Singleton_Lazy instance;

    private Singleton_Lazy(){};

    public static synchronized Singleton_Lazy getInstance(){
        if (instance == null){
            instance = new Singleton_Lazy();
        }
        return instance;
    }
}

/**
 * 饿汉式,线程安全
 * 优点:没有加锁,执行效率快
 * 缺点:类加载时就初始化,浪费内存
 */
class Singleton{
    private static Singleton instance = new Singleton();
    private Singleton(){};
    public static Singleton getInstance(){
        return instance;
    }
}

/**
 * 懒汉 + 双重校验锁
 * 采用双锁机制,安全并且在多线程下保持高性能
 */
class Singleton_DCL{
    private volatile static Singleton_DCL instance;

    private Singleton_DCL(){};

    public static Singleton_DCL getInstance(){
        if (instance == null){
            synchronized (Singleton.class){
                if (instance == null){
                    instance = new Singleton_DCL();
                }
            }
        }
        return instance;
    }
}

