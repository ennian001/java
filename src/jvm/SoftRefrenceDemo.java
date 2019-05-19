package jvm;

import java.lang.ref.SoftReference;

public class SoftRefrenceDemo {
    //内存够用，保留
    public static void softRefMemoryEnough(){
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;
        System.gc();
        System.out.println(o1);
        System.out.println(softReference.get());
    }
    //内存不够用,故意产生大对象，并配置小内存，让它内存不够用了导致OOM，看软引用的回收情况
    //-Xmx=5m -Xms=5m -XX:+PrintGCDetails
    public static void softRefMemoryNotEnough(){
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());
        o1 = null;
        System.gc();
        try {
            byte[] bytes = new byte[30*1024*1024];
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            System.out.println(o1);
            System.out.println(softReference.get());
        }
    }
    public static void main(String[] args) {
        //softRefMemoryEnough();
        softRefMemoryNotEnough();
    }

}
