package JUC;
/**
 * 多线程编程的前提如下：
 *  1、线程操作资源类
 *  2、高内聚、低耦合
 * 结论：资源类自身以高内聚的方式，自身携带同步方法，对外暴露给多线程使用
 */
//3个售票员同时卖票  卖出30张票
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 *  多线程编程
 *  1、先创建资源类
 *  2、多线程操作的方法
 *  3、线程操作资源类
 */
class Ticket
{
    private Lock lock = new ReentrantLock();
    private int number = 30; //资源
    //多线程操作的方法
    public void sale(){
        lock.lock();
        try {
            if (number>0){

                System.out.println(Thread.currentThread().getName()+"\t卖出第："+(number --)+"\t还剩下："+number );

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }
}
public class ThreadDemo0 {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        //三个线程
        //匿名内部类-----new 接口
        //lambda 改进
        new Thread(()->{
            for (int i = 0; i < 30; i++) {
                ticket.sale();
            };},"AA").start();
        new Thread(()->{
            for (int i = 0; i < 30; i++) {
                ticket.sale();
            };},"BB").start();
        new Thread(()->{
            for (int i = 0; i < 30; i++) {
                ticket.sale();
            };},"CC").start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 30; i++) {
//                    ticket.sale();
//                }
//            }
//        },"AA").start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 30; i++) {
//                    ticket.sale();
//                }
//            }
//        },"BB").start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 30; i++) {
//                    ticket.sale();
//                }
//            }
//        },"CC").start();
    }
}
