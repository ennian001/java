package jvm;

/**
 *  在java中可视为GC Roots的对像有:
 *  1、虚拟机栈（本地变量表）中引用的对象
 *  2、方法区中常量引用的对象
 *  3、方法区中的静态类属性引用的对象
 *  4、本地方法栈中JNI（即一般说的Native方法）中引用的对象
 */
public class GCRootsDemo {
//    private static GCRootsDemo2 t2;
//    private static final GCRootsDemo3 t3 = new GCRootsDemo3(8);
    public static void m1(){
        GCRootsDemo t1 = new GCRootsDemo();
        System.gc();
        System.out.println("第一次GC完成");
    }
    public static void main(String[] args) {
        m1();
    }
}
