package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *  动态代理
 *  委托人向秋雅送花
 *  代理人 马冬梅
 */
public class TestDynamicProxy {

    public static void main(String[] args) {
        Real2 xialuo = new Real2();
        Person1 o = (Person1) Proxy.newProxyInstance(xialuo.getClass().getClassLoader(),
                xialuo.getClass().getInterfaces(), new MyInovaction(xialuo)
        );
        o.sendFlower();
    }




}
class Real2 implements Person1{
    @Override
    public void sendFlower() {
        System.out.println("夏洛向秋雅送花");
    }
}
interface Person1{
    void sendFlower();
}
class MyInovaction implements InvocationHandler{
    Object object;
    public MyInovaction(Object object){
        this.object = object;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始代理业务");
        /**
         *  Object委托人对象,
         *   args :委托人需要办的业务用到的参数
         */
        Object invoke = method.invoke(object, args);
        System.out.println("结束代理业务");
        return invoke;
    }
}