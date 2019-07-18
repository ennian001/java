package thread.exec;

/**
 *  创建多线程的方式二： 实现Runnable接口
 *  1、创建一个实现了Runnable接口的类
 *  2、实现类去实现Runnable中的抽象方法：run()
 *  3、创建实现类对象
 *  4、将此对象作为参数传递到Thread类的构造器中，创建Thread类的对象
 *  5、通过Thread类的对象调用start()
 */
//1、创建一个实现了Runnable接口的类
class Mythread implements Runnable{
    //2、实现类去实现Runnbale中的抽象方法
    @Override
    public void run() {
        for (int i = 0; i <100 ; i++) {
            if (i % 2 ==0){
                System.out.println(i);
            }
        }
    }
}
public class ThreadTest1 {

    public static void main(String[] args) {
        //3、创建实现类的对象
        Mythread mythread = new Mythread();
        //4、将此对象作为参数，传递到Thread中（Thread构造器）
        Thread t1 = new Thread(mythread);
        t1.start();
    }

}
