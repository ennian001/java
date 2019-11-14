package jvm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JConsoleUtil {
    //线程死循环
    public static void createBusyThread(){
        new Thread(
                ()->{
                    while (true){
                        System.out.println("111" +
                                "");
                    }
                }
        ,"testBasyThread").start();
    }
    /**
     *  线程锁
     */
    public static void createLockThread(final Object lock){
        new Thread(()->{
            synchronized (lock){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"testLockThread").start();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader( new InputStreamReader(System. in));
        br.readLine();
        createBusyThread();
        br.readLine();
        Object object = new Object();
        createLockThread(object);

    }

}
