package interview.VolatileDemo;

import java.util.concurrent.CountDownLatch;

public class CountDownLanchExample {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i <6 ; i++)
        {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t"+"上完晚自习");
                //其他线程调用CountDownLatch
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        //main线程阻塞
        countDownLatch.await();
        System.out.println("最后一个走关门");
    }
}
