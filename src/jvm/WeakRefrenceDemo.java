package jvm;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.lang.ref.WeakReference;

public class WeakRefrenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference(o1);
        System.out.println(o1);
        System.out.println(weakReference.get());
        o1= null;
        System.gc();
        System.out.println(o1);
        System.out.println(weakReference.get());
    }
}
