package blockingQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        // List list = new ArrayList();
        // 超时队列
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a", 2l, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 2l, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 2l, TimeUnit.SECONDS));
        //超过两秒钟阻塞
        System.out.println(blockingQueue.offer("a", 2l, TimeUnit.SECONDS));
    }

    private static void blockingApi() throws InterruptedException {
        //阻塞 put/take
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.put("a");
        blockingQueue.put("a");
        blockingQueue.put("a");
        System.out.println("====================");
        //超过界限会阻塞
        //blockingQueue.put("over limit");
        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
        //超过界限会阻塞
        //blockingQueue.take();
    }

    private static void noExceptionJustTrueOrNull() {
        //不抛出异常
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.offer("a");
        blockingQueue.offer("b");
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("x"));
        //探测队顶
        System.out.println(blockingQueue.peek());
        //取元素
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }

    private static void exceptionApi() {
        // 抛出异常 API
        BlockingQueue<String> blockingQeque = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQeque.add("a"));
        System.out.println(blockingQeque.add("b"));
        System.out.println(blockingQeque.add("c"));
        //队列空不空，队首元素
        System.out.println(blockingQeque.element());

        System.out.println(blockingQeque.remove());
        System.out.println(blockingQeque.remove());
        System.out.println(blockingQeque.remove());
        System.out.println(blockingQeque.remove());
    }
}
