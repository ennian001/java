package JUC;
import java.util.concurrent.Callable;
/**
 *  Callable 接口 多线程的实现
 */
public class CallableStudy {

}
/**
 *  callable  与 Thread 区别
 *  1、实现各自方法  Thread实现run方法  Cllable
 *  2、是否抛异常
 *  3、是够有返回值
 */
class MyThread implements Runnable {
    @Override
    public void run() {

    }
}
class MyThread01 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return null;
    }
}