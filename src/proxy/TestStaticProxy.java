package proxy;

/**
 *  静态代理演示---》编译器
 */
//场景: 委托人 夏洛 向秋雅送花
  //代理人: 马东梅
public class TestStaticProxy {
    public static void main(String[] args) {
        Xialuo xialuo = new Xialuo();
        MyProxy madongbei = new MyProxy(xialuo);
        madongbei.sendFlower();
    }
}
    //定义一个人的接口
    //人有送花这一个动作
    interface Person{
        void sendFlower();
    }
    //夏洛这个对象实现了人这个接口，可以具有送花的动作
    class Xialuo implements Person{
        @Override
        public void sendFlower() {
            System.out.println("夏洛夏洛 向秋雅送花");
        }
    }
    //马冬梅代理类也实现了人这个接口，具有送花的动作
    class MyProxy implements Person{
        //人的属性
        private Person person;
        //有参构造
        public MyProxy(Person person) {
            this.person = person;
        }
        @Override
        public void sendFlower() {
            System.out.println("马冬梅开始代理业务----");
            person.sendFlower();
            System.out.println("马冬梅结束代理业务----");
        }
    }

