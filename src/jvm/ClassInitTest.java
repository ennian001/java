package jvm;
public class ClassInitTest {
    private static int num  = 1;
    static {
        num = 2 ;
        number = 20 ;
        System.out.println(num);
//        System.out.println(number); 报错，非法的前项引用
    }
    private static  int number = 10 ; //linking之prepare：number = 0 ---> initial:20 ---> 10
    public static void main(String[] args) {
        System.out.println(ClassInitTest.num);
        System.out.println(ClassInitTest.number);
    }
}
