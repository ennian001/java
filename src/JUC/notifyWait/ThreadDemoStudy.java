package JUC.notifyWait;

/**
 *  现在两个线程，可以操作同一变量，实现一个线程对该变量加1，
 *  一个线程对该变量减1
 *  实现交替，来5轮，变量初始值为零。
 *  1	线程 	操作	资源（类）
 *  2	高内聚	低耦合
 */
public class ThreadDemoStudy {

    public static void main(String[] args) {

        ShareData shareData = new ShareData();

        new Thread(()->{

            for (int i = 0; i < 10; i++) {
                try {
                    shareData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    shareData.dcrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();



    }



}
/*
* wait() 与 与 notify() 和 和 notifyAll()
wait()：令当前线程挂起并放弃CPU、同步资源并等待，使别的线程可访问并修改共享资源，而当
前线程排队等候其他线程调用notify()或notifyAll()方法唤醒，唤醒后等待重新获得对监视器的所有
权后才能继续执行。
notify()：唤醒正在排队等待同步资源的线程中优先级最高者结束等待
notifyAll ()：唤醒正在排队等待资源的所有线程结束等待.
 这三个方法只有在synchronized方法或synchronized代码块中才能使用，否则会报
java.lang.IllegalMonitorStateException异常。
 因为这三个方法必须有锁对象调用，而任意对象都可以作为synchronized的同步锁，
因此这三个方法只能在Object类中声明。*/
class ShareData{

    private int num = 0;

    public synchronized void increment() throws InterruptedException {
        // 1、判断
        if (num!=0){
            this.wait();
        }
        //2、干活
        ++num;
        System.out.println(Thread.currentThread().getName()+"\t"+num);
        //3、唤醒
        this.notifyAll();
    }

    public synchronized void dcrement() throws InterruptedException {
        //1、判断
        if (num == 0){
            this.wait();
        }
        //2、执行--的逻辑
        --num;
        System.out.println(Thread.currentThread().getName()+"\t"+num);

        this.notifyAll();
    }




}