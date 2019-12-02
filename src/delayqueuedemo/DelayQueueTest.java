package delayqueuedemo;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DelayQueueTest {

    public static void main(String[] args) {
        //创建延时队列
        DelayQueue<Message> queue = new DelayQueue<>();
        //添加延时消息，m1 延时3s
        Message m1 = new Message(1,"m1 send world",3000);
        //m2 延时10s
        Message m2 = new Message(2,"m2 send world",10000);
        //将延时消息放到延时队列中
        queue.offer(m1);
        queue.offer(m2);
        //启用消费线程 ， 消费添加到延时队列中的消息，前提是任务到了延期时间
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(new Consumer(queue));
        executorService.shutdown();
    }

}
