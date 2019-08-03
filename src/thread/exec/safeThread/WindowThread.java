package thread.exec.safeThread;

/**
 *  Thread 类线程不安全 举例
 */
public class WindowThread extends Thread {
    // 静态变量中  三个线程共享的
    private static int ticket = 100;
    //注意此处为静态对象
    static  Object object = new Object();
    @Override
    public void run() {
        while (true){
            //正确
//            synchronized (object){
            //Window2.class只会加载一次
            synchronized (WindowThread.class) {
                //错误的方式：this代表着t1,t2,t3三个对象
//              synchronized (this){
                if (ticket > 0) {
                    System.out.println(getName() + "：卖票，票号为：" + ticket);
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }
}
class WindowTest2{
    public static void main(String[] args) {
        WindowThread t1 = new WindowThread();
        WindowThread t2 = new WindowThread();
        WindowThread t3 = new WindowThread();
        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");
        t1.start();
        t2.start();
        t3.start();
    }
}
