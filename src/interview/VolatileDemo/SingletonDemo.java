package interview.VolatileDemo;

public class SingletonDemo {
    //保证多线程语义一直性
    private static volatile SingletonDemo instance = null;
    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName());
    }
    public static SingletonDemo getInstance(){
        if (instance == null) {
            synchronized (SingletonDemo.class) {
                if (instance==null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }
//Double check lock 双端检锁机制
    public static void main(String[] args) {
        /**
         * DCL单例模式
         */
        //单线程（main线程的操作动作........）
//        System.out.println(SingletonDemo.getInstance()==SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance()==SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance()==SingletonDemo.getInstance());
        //并发多线程后
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                System.out.println(SingletonDemo.getInstance());
            },String.valueOf(i)).start();
        }
    }
}
