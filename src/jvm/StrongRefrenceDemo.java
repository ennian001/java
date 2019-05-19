package jvm;

public class StrongRefrenceDemo {
    public static void main(String[] args) {
        //这种定义就是强引用
        Object o1 = new Object();
        //o2引用赋值
        Object o2 = o1;
        //置空
        o1 = null;
        System.gc();
        System.out.println(o2);
    }
}
