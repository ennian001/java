package Test;

import com.sun.deploy.pings.Pings;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 并发测试工具
 */
public class CurrentUtil {

    public static final int requestTotal = 1000;

    // 同一时刻最大的并发线程的个数
    public static final int concurrentThreadNum = 20;

    /**
     * //1000个人，每次最多并发处理20个
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(requestTotal);
        //允许同时并发运行线程的次数
        Semaphore semaphore = new Semaphore(concurrentThreadNum);
        for (int i = 0; i< requestTotal; i++) {
            executorService.execute(()->{
                try {
//                    从信号量中获取一个许可
                    semaphore.acquire();
                    String result = testRequestUri();
                    System.out.println(result);
//                    释放一个许可并返回给信号量
                    semaphore.release();
                } catch (InterruptedException e) {
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
    }

    private static String testRequestUri() {
        byte[] bytes = Pings.JAVAFX_CACHE_JNLP_URL.getBytes();
        return bytes.toString();
    }
    @Test
    public void test2() {
        int a=2;
        int b=3;
        a=a^b;
        b=a^b;
        a=a^b;
        System.out.println("a="+a+",b="+b);
    }
}
