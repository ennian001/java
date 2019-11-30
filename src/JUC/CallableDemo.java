package JUC;


import java.util.concurrent.*;

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
        Integer integerA = ft.get();
        System.out.println(integerA);
        Integer integerB = ft.get();
        System.out.println(integerB);
    }

}
