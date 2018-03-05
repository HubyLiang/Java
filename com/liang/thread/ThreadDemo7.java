package com.liang.thread;

import java.util.Random;
import java.util.concurrent.*;

public class ThreadDemo7 {

    public static void main(String[] args){
        threadPoolTest();
        schedualThreadPoolTest();

    }

    /**
     * 每隔3秒创建一个线程
     */
    public static void schedualThreadPoolTest() {
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(5);
        ScheduledFuture<Integer> result = null;

        try {
            for (int i = 0;i<=20;i++){
               result =  threadPool.schedule(new Callable<Integer>() {

                    @Override
                    public Integer call() throws Exception {
                        System.out.print(Thread.currentThread().getName()+"  ");
                        return new Random().nextInt(8);
                    }
                },3, TimeUnit.SECONDS);

                System.out.println("*****:"+result.get());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }

    /**
     * 按照固定的数量创建线程池,并创建线程
     */
    public static void threadPoolTest() {
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        ExecutorService threadPool = Executors.newCachedThreadPool();
        Future<Integer> result = null;

        try {
            for (int i =0;i<=20;i++){
                result = threadPool.submit(new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        System.out.print(Thread.currentThread().getName()+"  ");
                        return new Random().nextInt(10);
                    }
                });
                System.out.println("*****:"+result.get());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }

}
