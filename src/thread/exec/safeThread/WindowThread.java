package thread.exec.safeThread;

/**
 *  Thread 类线程不安全 举例
 */
public class WindowThread extends Thread {
    // 静态变量中  三个线程共享的
    private static int ticket = 100;
    @Override
    public void run() {
        while (true){
            if (ticket>0){
                System.out.println(getName()+"：卖票，票号为："+ticket);
                ticket --;
            }else{
                break;
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
