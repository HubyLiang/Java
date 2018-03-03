package com.liang.jvm;

public class ShowJVMMember {

    /**
     * 测试 JVM 虚拟机基本内存调优参数
     * 堆设置
     *   -Xms:初始堆大小
     *   -Xmx:最大堆大小
     *   -XX:NewRatio=n:设置年轻代和年老代的比值。如:为3，表示年轻代与年老代比值为1：3，年轻代占整个年轻代年老代和的1/4
     *   -XX:SurvivorRatio=n:年轻代中Eden区与两个Survivor区的比值。注意Survivor区有两个。如：3，表示Eden：Survivor=3：2，一个Survivor区占整个年轻代的1/5
     * 垃圾回收统计信息
     *   -XX:+PrintGC
     *   -XX:+PrintGCDetails
     * @param args
     */
    public static void  main(String[] args){

        //最大JVM堆内存容量, 默认物理内存的 1/4
        long maxMemory = Runtime.getRuntime().maxMemory(); //返回Java虚拟机使用的最大内存量

        //Java虚拟机的堆内存总量, 初始的JVM堆内存大小为物理内存的 1/64
        long totalMemory = Runtime.getRuntime().totalMemory();

        System.out.println("Max_Memory: "+maxMemory+" 字节 / " + (maxMemory / (double)1024/1024)+"MB");

        System.out.println("Total_Memory: "+totalMemory+" 字节 / " + (totalMemory/ (double)1024/1024)+"MB");

    }
}
