package JUC.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *  读写锁
 *  一个线程写，100个线程读
 */
public class ReentReadWriteLock {
    public static void main(String[] args) {
        Queue que = new Queue();
        new Thread(()->{
            que.writeObj("write0922");
        },"writeThread").start();
        for (int i = 0;i<100;i++){
            new Thread(()->{que.readObj();},String.valueOf(i)).start();
        }
    }
}
class Queue{
    private Object obj;
    private ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
    public void writeObj(Object obj){
        rw.writeLock().lock();
        try {
            this.obj = obj;
            System.out.println(Thread.currentThread().getName()+"\t"+obj);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rw.writeLock().unlock();
        }
    }
    public void readObj(){
        rw.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t"+obj);
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            rw.readLock().unlock();
        }
    }
}