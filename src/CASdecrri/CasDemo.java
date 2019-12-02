package CASdecrri;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *  什么是CAS？
 *
 *  1、比较并交换
 */
public class CasDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        //expect 5  在内存对象中，真实值与期望值比较为true，可以修改
        //真实值与期望值比较相同，修改成功，否则不同，就不修改
        System.out.println(atomicInteger.compareAndSet(5, 2019)+"\t"+"CUrrent:"+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 1024)+"\t"+"CUrrent:"+atomicInteger.get());
    }


}
