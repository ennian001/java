package JUC.threadpool;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class MyThreadPoolDemo {


    public static void main(String[] args) {
        // 一池5个处理线程
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        //一池1个线程
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        //一池N个线程
//        ExecutorService threadPool = Executors.newCachedThreadPool();

        try {
            for (int i = 0; i < 10000; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t"+"线程进行处理");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

    }



}
