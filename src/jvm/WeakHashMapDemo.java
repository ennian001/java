package jvm;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.WeakHashMap;

public class WeakHashMapDemo {
    public static void main(String[] args) {
        myWeakHashMap();
    }
    private static void myWeakHashMap(){
        WeakHashMap<Integer,String> weakHashMap = new WeakHashMap<>();
        Integer key = new Integer(2);
        String value = "weakHashMap";
        weakHashMap.put(key,value);
        key = null;
        System.out.println(weakHashMap);
        System.gc();
        System.out.println(weakHashMap+"\t"+weakHashMap.size());
    }
}
