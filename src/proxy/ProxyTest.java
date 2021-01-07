package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理举例
 */
public class ProxyTest {

    public static void main(String[] args) {
        SuperMan superMan = new SuperMan() ;
        //proxyInstance:代理类的对象
        Human proxyInstance = (Human) ProxyFactory.getProxyInstance(superMan);
        //当通过代理类对象调用方法时，会自动的通过被代理类中的同名的方法
        String belied = proxyInstance.getBelied();
        System.out.println(belied);
        proxyInstance.eat("四川麻辣烫");
    }

}
interface Human{
    String getBelied() ;
    void eat(String food) ;
}

//被代理类
class SuperMan implements Human{

    @Override
    public String getBelied() {
        return "I believe I can fly!";
    }

    @Override
    public void eat(String food) {
        System.out.println("我喜欢吃" + food);
    }
}
/**
 * 要想实现动态代理 ， 需要解决得问题？
 * 问题一：如何根据加载到内存中的被代理类，动态创建一个代理代理类及其对象 。
 * 问题二：当通过代理类的对象调用方法a时，如何动态的去调用被代理类中的同名方法a。
 */
class ProxyFactory{
    public static Object getProxyInstance(Object obj){
        MyInvocationHandler handler = new MyInvocationHandler() ;
        handler.bind(obj);
        return Proxy.newProxyInstance(obj.getClass().getClassLoader() , obj.getClass().getInterfaces() , handler) ;
    }
}

class MyInvocationHandler implements InvocationHandler{

    private Object obj ;

    public void bind(Object obj){
        this.obj = obj ;
    }
    //当我们通过代理类的对象，调用方法a时，就会自动的调用如下的方法：invoke()
    //将被代理类要执行的方法a的功能就声明再invoke（）中
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //method:即为代理类对象调用的方法，此方法也就作为了被代理类对象要调用的方法
        //obj:被代理类的对象
        Object invoke = method.invoke(obj,args);
        //上述方法的返回值，就作为当前类中的invoke（）的返回值。
        return invoke;
    }
}