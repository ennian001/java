package proxy;

/**
 * 静态代理
 * 特点：代理类和被代理类在编译期间，就确定下来了
 */
public class StaticProxyTest {

    public static void main(String[] args) {
        //创建被代理类的对象
        ClothFactory nike = new NikeClothFactory() ;
        //创建代理类的对象
        ClothFactory proxyClothFactory = new ProxyClothFactory(nike);

        proxyClothFactory.produceCloth();
    }


}

//代理类
class ProxyClothFactory implements ClothFactory{

    //用被代理类对象进行实例化
    private ClothFactory factory ;

    public ProxyClothFactory(ClothFactory factory) {
        this.factory = factory;
    }

    @Override
    public void produceCloth() {
        System.out.println("代理工厂做了一些准备工资");
        factory.produceCloth();
        System.out.println("代理工厂做了一些后续的收尾工作");
    }
}
//被代理类
class NikeClothFactory implements ClothFactory{

    @Override
    public void produceCloth() {
        System.out.println("Nike工厂生产一批运动服");
    }
}
interface ClothFactory{

    void produceCloth() ;

}
