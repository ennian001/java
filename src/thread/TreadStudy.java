package thread;

/**
 * 多线程的创建
 * 方式一：集成Thread类
 *  ① 创建一个继承与Thread的子类
 *  ② 重写Thread类的run()  将此线程执行的操作声明在run方法中
 *  ③ 重写Thread类的子类的对象
 *  ④ 通过此对象调用start()
 */

//1、创建一个继承与Tread类的子类
class MyThread extends Thread{
    //2、重写run方法
    @Override
    public void run() {
        for (int i = 0; i <100 ; i++) {
            if (i % 2 == 0){
                System.out.println(Thread.currentThread().getName()+i);
            }
        }
    }
}

public class TreadStudy {

    public static void main(String[] args) {
        //3、创建子类的对象
        MyThread t1 = new MyThread();
        //4、通过此对象调用start()方法,启动当前线程，当前线程的run()方法
        t1.start();
//        不能直接调用run()的方式启动线程，因为没有调用run方法，没有新开一个线程，相当于还是在main线程中
//        t1.run();
//        再启动一个线程,不可以让已经开始的线程再去执行。会报IllegalThreadStateException

        for (int i = 0; i <100 ; i++) {
            if (i % 2 == 0){
                System.out.println(Thread.currentThread().getName()+"*****************");
            }
        }
        System.out.println("hello");
    }



}
