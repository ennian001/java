package jvm;

import sun.security.ec.CurveDB;

import java.net.URL;
import java.security.Provider;

public class ClassLoaderTest1 {

    public static void main(String[] args) {
        System.out.println("*************启动类加载器***************");
        //获取bootstrapClassLoader能够加载的api的路径
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urls) {
            System.out.println(url.toExternalForm());
        }
        //从上面的路径中随意选择一个类，来看看它的类加载器是什么
        ClassLoader classLoader = Provider.class.getClassLoader();
        System.out.println(classLoader);//null

        //扩展类加载器
        System.out.println("****************扩展类加载器****************");
        String extDirs = System.getProperty("java.ext.dirs");
        for (String s : extDirs.split(";")) {
            System.out.println(s);
        }
        //从上面的路径中随意选择一个类，来看看他的类加载器是什么： 扩展类加载器
        ClassLoader classLoader1 = CurveDB.class.getClassLoader();
        System.out.println(classLoader1);//sun.misc.Launcher$ExtClassLoader@5cad8086

    }

}
