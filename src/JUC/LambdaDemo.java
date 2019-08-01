package JUC;

/**
 * 1、接口中只有一个抽象方法，只关心方法的实现，方法名称不会找错
 * 2、(形参列表)->{方法体实现}
 * 3、@FunctionalInterface 编译时验证是否为函数式接口
 * 4、jdk1.8接口中可以编写1个或多个默认方法,可以有一个或多个静态方法
 */
@FunctionalInterface
interface Foo{
//    public void say886();
    public int add (int x,int y) ;

    default int div(int x,int y){
        return x/y;
    }
    public static int div2(int x, int y ){
        return x/y;
    }
}

public class LambdaDemo {


    public static void main(String[] args) {
//       Foo test1 = new Foo() {
//           @Override
//           public void say886() {
//               System.out.println("886");
//           }
//       };
//       test1.say886();
//        Foo test = ()->{
//           System.out.println("886");
//       };
//        test.say886();
        Foo test = (x,y)->{
            return x+y;
        };
        int add = test.add(1, 3);
        System.out.println(add);
        int div = test.div(4, 2);
        int i = Foo.div2(6, 3);
    }





}
