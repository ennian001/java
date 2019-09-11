package JUC.waitnotifynotifyall;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author sunennian
 * 题目：现在两个线程，可以操作初始值为零的一个变量，实现一个线程对该变量加1，一个线程对该变量减1，
 * 实现交替，来5轮，变量初始值为零。
 * 1	线程 	操作	资源（类）
 * 2	高内聚	低耦合
 */
public class ThreadDemo {
    public static void main(String[] args) {
        ShareData sd = new ShareData();
        new Thread(
                ()->{
                    for (int i = 1;i<=10;i++){
                        try {
                            Thread.sleep(200);
                            sd.increment();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ,"A").start();
        new Thread(
                ()->{
                    for (int i = 1;i<=10;i++){
                        try {
                            Thread.sleep(200);
                            sd.decrement();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                ,"B").start();
        new Thread(
                ()->{
                    for (int i = 1;i<=10;i++){
                        try {
                            Thread.sleep(200);
                            sd.increment();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                ,"C").start();
        new Thread(
                ()->{
                    for (int i = 1;i<=10;i++){
                        try {
                            Thread.sleep(200);
                            sd.decrement();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                ,"D").start();
    }
}

/**
 wait() 一旦执行此方法，当前线程就进入阻塞状态，并释放同步监视器锁。
 notify() 一旦执行此方法，就会唤醒wait的一个线程。如果有多个线程被wait，就会唤醒优先级高的那个。
 notifyAll() 一旦执行此方法，就会唤醒所有被wait的线程
 wait() notify() notifyAll() 三个方法必须使用在同步代码块或同步方法中。
 wait() notify() notifyAll() 三个方法使用者必须是同步代码块或同步方法中的同步监视器
 */

//资源类
class ShareData{
    private int number  = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    //使用lock的唤醒线程通讯
    public void increment() throws InterruptedException {
        lock.lock();
        try {
            while (number!=0){
                condition.await();//this.wait();
            }
            ++number;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            condition.signalAll();//notifyAll()
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            while (number==0){
                condition.await();//this.wait();
            }
            --number;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            condition.signalAll();//notifyAll()
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //wait notify/notifyall
   /* public synchronized void increment() throws InterruptedException {
        //判断，注意wait不能使用if判断，需要使用while,防止虚假唤醒，因为if只是判断一次
        while (number!=0){
            //wait，使得调用如下方法的线程进入阻塞状态
            this.wait();
        }
        //自增
        ++number;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        //唤醒
        this.notifyAll();
    }
    public synchronized void decrement() throws InterruptedException {
        //判断
        while (number == 0){
            this.wait();
        }
        -- number;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        //唤醒
        this.notifyAll();
    }*/
}