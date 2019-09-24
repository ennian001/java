package interview.VolatileDemo;

import java.util.concurrent.TimeUnit;

class MyData{
//    int number = 0 ;
    volatile int  number = 0 ;
    public void add260(){
        this.number = 60;
    }
    //请注意，此时number前面是加了volatile关键字修饰的
    public  void addPlusPlus(){
        number++;
    }
}
/**
 *  1、验证volatile的可见性
 *  先假如 int number = 0 ； number变量之间没有volatile关键字修饰
 *  2、验证volatile不保证原子性
 *   2.1 原子性值的是什么？
 *         不可分割，完整性，也即某个线程正在做某个具体业务时，中间不可以被加塞或者被分割。需要整体完整
 *         要么同时成功，失败
 *
 */
public class VolatileDemo {
    public static void main(String[] args) {
        MyData myData = new MyData();
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                     myData.addPlusPlus();
                }
            },String.valueOf(i)).start();
        }
        //需要等待20个线程全都计算完成后，再用main线程取的结果值
        //默认有两个线程  main 与 gc
        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(myData.number);
    }
    //volatile可以保证可见性，及时通知其他线程，主物理内存的值已经被修改
    private static void seeOk() {
        MyData myData = new MyData();//资源类
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t"+"come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.add260();

            System.out.println(Thread.currentThread().getName()+"\t"+"update value"+myData.number);
        },"A").start();
        //第二个线程就是我们的main线程
        while (myData.number == 0){
            //main线程就一直再这里循环等待
        }
        System.out.println("获取number，任务结束");
    }
}
