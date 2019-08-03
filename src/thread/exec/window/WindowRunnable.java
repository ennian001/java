package thread.exec.window;

/**
 *  实现Runnable接口展示线程不安全问题
 *  三个窗口卖票100张
 */
public class WindowRunnable implements Runnable {
    private int ticket = 100;
    @Override
    public void run() {
        while(true){
            if(ticket > 0){
                System.out.println(Thread.currentThread().getName() + ":卖票，票号为：" + ticket);
                ticket--;
            }else{
                break;
            }
        }
    }
}
 class WindowTest1{
     public static void main(String[] args) {
         WindowRunnable w = new WindowRunnable();
         Thread t1 = new Thread(w);
         Thread t2 = new Thread(w);
         Thread t3 = new Thread(w);
         t1.setName("窗口1");
         t2.setName("窗口2");
         t3.setName("窗口3");

         t1.start();
         t2.start();
         t3.start();
     }
 }
