package JUC;


import java.util.concurrent.*;
/**
 一般FutureTask多用于耗时的计算，主线程可以在完成自己的任务后，再去获取结果。

 仅在计算完成时才能检索结果；如果计算尚未完成，则阻塞 get 方法。一旦计算完成，就不能再重新开始或取消计算。
 get方法而获取结果只有在计算完成时获取，否则会一直阻塞直到任务转入完成状态，然后会返回结果或者抛出异常。
 */
class MyThreadCallInstinst implements Callable<Integer>{

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Integer call() throws Exception {
        System.out.println("***************************call****************");
        return 200;
    }
}



public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*
        *  FutureTask 传入Callable实现类
        *  虽然开始两次线程，但是查看资源仅仅调用了一次
        * */
        FutureTask<Integer> ft = new FutureTask<Integer>(new MyThreadCallInstinst());
        new Thread(ft,"AA").start();
        new Thread(ft,"BB").start();
        //获取返回参数
        //get() 只能放在最后，否则其它线程会阻塞
        Integer integerA = ft.get();
        System.out.println(integerA);
        Integer integerB = ft.get();
        System.out.println(integerB);
    }

}
