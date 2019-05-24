package JUC;
/**
 * 面试题思考：
 *     在很多情况下，可能有多个线程需要访问数目很少的资源。假想在服务器上运行着若干个回答客户端请求的线程。这些线程需要连接到同一数据库，但任一时刻
 *     只能获得一定数目的数据库连接。你要怎样才能够有效地将这些固定数目的数据库连接分配给大量的线程？
 *
 * 答：1.给方法加同步锁，保证同一时刻只能有一个人去调用此方法，其他所有线程排队等待，但是此种情况下即使你的数据库链接有10个，也始终只有一个处于使
 *         用状态。这样将会大大的浪费系统资源，而且系统的运行效率非常的低下。
 *     2.另外一种方法当然是使用信号量，通过信号量许可与数据库可用连接数相同的数目，将大大的提高效率和性能。
 */
/**
 *  java中提供了Semaphore信号量的支持
 *  Semaphore类是一个计数信号量，必须获取它的线程释放
 *  通常用于限制可以访问某些资源（物理或逻辑）线程数目
 *
 * 信号量的构造函数
 * 非公平：
 * public Semaphore(int permits);//permits就是允许同时运行的线程数目
 * 公平（获得锁的顺序与线程启动顺序有关）：
 * public Semaphore(int permits,boolean fair);//permits就是允许同时运行的线程数目
 从信号量中获取一个许可
 semaphore.acquire();
 释放一个许可(在释放许可之前，必须先获获得许可。)
 semaphore.release();
 尝试获取一个许可，若获取成功返回true，若获取失败返回false
 semaphore.tryAcquire();
 // 创建具有给定的许可数和非公平的公平设置的 Semaphore。
 Semaphore(int permits)
 // 创建具有给定的许可数和给定的公平设置的 Semaphore。
 Semaphore(int permits, boolean fair)

 // 从此信号量获取一个许可，在提供一个许可前一直将线程阻塞，否则线程被中断。
 void acquire()
 // 从此信号量获取给定数目的许可，在提供这些许可前一直将线程阻塞，或者线程已被中断，直到提供了permits许可。。
 void acquire(int permits)
 // 从此信号量中获取许可，在有可用的许可前将其阻塞。
 void acquireUninterruptibly()
 // 从此信号量获取给定数目的许可，在提供这些许可前一直将线程阻塞，直到提供了permits许可。
 void acquireUninterruptibly(int permits)
 // 返回此信号量中当前可用的许可数。
 int availablePermits()
 // 获取并返回立即可用的所有许可。
 int drainPermits()
 // 返回一个 collection，包含可能等待获取的线程。
 protected Collection<Thread> getQueuedThreads()
 // 返回正在等待获取的线程的估计数目。
 int getQueueLength()
 // 查询是否有线程正在等待获取。
 boolean hasQueuedThreads()
 // 如果此信号量的公平设置为 true，则返回 true。
 boolean isFair()
 // 根据指定的缩减量减小可用许可的数目。
 protected void reducePermits(int reduction)
 // 释放一个许可，将其返回给信号量。
 void release()
 // 释放给定数目的许可，将其返回到信号量。
 void release(int permits)
 // 返回标识此信号量的字符串，以及信号量的状态。
 String toString()
 // 仅在调用时此信号量存在一个可用许可，才从信号量获取许可。
 boolean tryAcquire()
 // 仅在调用时此信号量中有给定数目的许可时，才从此信号量中获取这些许可。
 boolean tryAcquire(int permits)
 // 如果在给定的等待时间内此信号量有可用的所有许可，并且当前线程未被中断，则从此信号量获取给定数目的许可。
 boolean tryAcquire(int permits, long timeout, TimeUnit unit)
 // 如果在给定的等待时间内，此信号量有可用的许可并且当前线程未被中断，则从此信号量获取一个许可。
 boolean tryAcquire(long timeout, TimeUnit unit)

 * 一个信号量有且仅有3种操作，且他们全部是原子的：初始化、增加和减少
 * 增加可以为一个进程解除阻塞；
 * 减少可以让一个进程进入阻塞;
 * 信号量维护一个许可集，若有必要，会在获得许可之前阻塞每一个线程，
 * 从此信号量获取给定数目的许可，在提供这些许可前一直将线程阻塞 acquireUniterrupibly(int permits){}
 * 如何获得Semaphore对象？
 * public Semaphore(int permits,boolean fair); permits 初始化可用的许可条目数，fair若该信号量保证在征用时按FIFO的顺序授予许可，则为true，否则为false；
 *
 * 如何从信号量获得许可？
 *     public void acquire() throws InterruptedException
 * 如何释放一个许可，并返回信号量？
 *     public void release()
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 20个人去银行存款，但是该银行只有两个办公柜台，有空位则上去存钱，没有空位则只能去排队等待
 */
public class SemaphoreStudy {
    private int a = 0;
    /**
     *  银行存钱类
     */
    class Bank{
        private int account = 100;
        public int getAccount(){
            return account;
        }
        public void setAccount(int account) {
            this.account = account;
        }
    }
    /**
     * 线程类，每次存10块钱
     */
    class NewThread implements Runnable{

        private Bank bank;
        private Semaphore semaphore;

        public NewThread(Bank bank, Semaphore semaphore) {
            this.bank = bank;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            int b = a++;
            //如果有可用的许可，许可大于零
            if (semaphore.availablePermits()>0){
                System.out.println("线程"+b+"启动，进入银行，有位置立即去存钱");
            }else {
                System.out.println("线程"+b+"启动，进入银行，无位置去排队等待");
            }

            try {
                //获得一个可用的许可，如果没有线程阻塞，等待有一个可用的许可。
                semaphore.acquire();
                bank.setAccount(10);
                System.out.println(b+"账户余额为:"+bank.getAccount());
                Thread.sleep(1000);
                // 释放一个许可，将其返回给信号量。
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 建立线程，调用内部类，开始存钱
     */
    public void userThread(){
        Bank bank = new Bank();
        //定义2个信号量  semaphore 信号中只有两个许可，（最多两个人同时执行）
        Semaphore semaphore = new Semaphore(2);
        //建立一个缓冲线程池
        ExecutorService es = Executors.newCachedThreadPool();
        //建立20个线程
        for (int i = 0; i <10 ; i++){
            es.submit(new Thread(new NewThread(bank,semaphore)));
        }
        //关闭线程池
        es.shutdown();
        //从信号量获取两个许可，并且在获取许可之前，一直将线程阻塞
        semaphore.acquireUninterruptibly(2);
        System.out.println("到点了，工作人员要吃饭了");
        //释放两个许可，并将期返回给信号量
        semaphore.release(2);
    }

    public static void main(String[] args) {
        SemaphoreStudy semaphoreStudy = new SemaphoreStudy();
        semaphoreStudy.userThread();
    }
}
