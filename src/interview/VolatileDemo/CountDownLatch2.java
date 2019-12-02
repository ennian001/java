package interview.VolatileDemo;

import java.util.concurrent.CountDownLatch;

public class CountDownLatch2 {
    public static void main(String[] args) {
        CountDownLatch count = new CountDownLatch(6);
        for (int i = 1; i <=6 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t"+"国统一");
                count.countDown();
            },CountryEnum.foreachCountryEnum(i).getRetMessage()).start();
        }
        try {
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("大秦统一完成");
    }
}
