package CASdecrri;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ABA问题的解决，AtomicStampedRefrence
 */
public class ABADemoStudy {
    //ABA问题
    public static void main(String[] args) {
        //--------------------------ABA问题的产生-------------------//
        System.out.println("---------------ABA----------------");
        AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
        /**
         * 初始值设为100.，版本号为1
         */
        AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(100,1);
        new Thread(()->{
            atomicReference.compareAndSet(100,101);
            atomicReference.compareAndSet(101,100);
        },"t1").start();

        new Thread(()->{
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(atomicReference.compareAndSet(100, 2019)+"\t"+atomicReference.get());
        },"AbaDemo").start();
        //ABA问题的解决
        System.out.println("-------------------------ABA问题解决-------------------------------");

        new Thread(()->{
            int stamp = stampedReference.getStamp();
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
            stampedReference.compareAndSet(100,101,stampedReference.getStamp(),stampedReference.getStamp()+1);
            stampedReference.compareAndSet(101,100,stampedReference.getStamp(),stampedReference.getStamp()+1);
            System.out.println("第一次版本号"+stampedReference.getStamp()+"\t"+"交换后的值为："+stampedReference.getReference());
        },"11").start();
        new Thread(()->{
            int stamp = stampedReference.getStamp();
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
            boolean b = stampedReference.compareAndSet(100, 2019, stamp, stamp + 1);
            System.out.println("第二次版本号"+stampedReference.getStamp()+"\t交换是否成功"+b);
        },"ss").start();


    }
}
