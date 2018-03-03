package com.liang.jvm;

import java.util.Random;

/**
 * 测试OOM: java.lang.OutOfMemoryError: Java heap space
 */
public class ShowOOM_Heap_Space {

    public static void main(String[] args){

        long totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println("Total_Memory: "+totalMemory+" 字节 / " + (totalMemory/ (double)1024/1024)+"MB");

        String str = "Hello JVM";
        while (true){
            str += str + new Random().nextInt(1000000000)+ new Random().nextInt(999999999);
        }
    }

}
