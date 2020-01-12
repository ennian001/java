package JUC;

import java.util.concurrent.TimeUnit;

/**
 *
 一个对象里面如果有多个synchronized方法，某一个时刻内，只要一个线程去调用其中的一个synchronized方法了，
 其它的线程都只能等待，换句话说，某一个时刻内，只能有唯一一个线程去访问这些synchronized方法

 锁的是当前对象this，被锁定后，其它的线程都不能进入到当前对象的其它的synchronized方法

 加个普通方法后发现和同步锁无关

 锁的是当前的Class

 所有的非静态同步方法用的都是同一把锁——实例对象本身
 所有的静态同步方法用的也是同一把锁——类对象本身
 */
class Flower  {
    public static synchronized void getMudanFlower() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("国花是牡丹");
    }
    public synchronized static void getJiucaiFlower(){
        System.out.println("国flower是韭菜");
    }
    public void Hello(){
        System.out.println("hello");
    }

}

/**
 *  多线程8锁
 *
 *  1、标准访问 先打印哪个？  先牡丹，后韭菜
 *  2、暂停4S 先打印哪个？  先牡丹，后韭菜
 *  3、新增Hello方法，先打印哪个？ 先hello 后牡丹，再韭菜
 *  4、两个Flower对象，先 韭菜后 牡丹 ，因为 锁的是对象 两个对象互不关联
 *  5、两个静态同步方法，同一个花实例对象
 *  6、两个静态同步方法。两朵花对象
 *  7、一个普通方法，一个静态同步方法，一个对象 实际两把锁
 *  8、一个普通方法，一个静态同步方法，两个对象
 */
public class ThreadEghitLock {
    public static void main(String[] args) {
        Flower flower = new Flower();
        Flower flower1 = new Flower();
        new Thread(()->{
            try {
                flower.getMudanFlower();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();
        new Thread(()->{
            flower1.Hello();
//            flower1.getJiucaiFlower();
        },"B").start();
    }
}
