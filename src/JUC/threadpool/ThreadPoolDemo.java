package JUC.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 手写线程池
 */
public class ThreadPoolDemo {


    public static void main(String[] args) {

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2, 5,1L
                , TimeUnit.SECONDS
                , new LinkedBlockingDeque<Runnable>(3)
                , Executors.defaultThreadFactory()
//                , new ThreadPoolExecutor.AbortPolicy()
//                , new ThreadPoolExecutor.CallerRunsPolicy()
//                , new ThreadPoolExecutor.DiscardOldestPolicy()
                , new ThreadPoolExecutor.DiscardPolicy()
        );
        try {
            for (int i = 0; i <12; i++) {
                threadPool.execute(()->{ System.out.println(Thread.currentThread().getName()+"\t"+"办理业务"); });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }



}
