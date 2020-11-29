package reflection;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 了解类的加载器
 */
public class ClassLoaderTest {

    @Test
    public void test1(){
        //对于自定义类， 使用系统类加载器加载
        ClassLoader classLoader  = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);

        //调用系统类加载器的getParent(): 获取扩展类加载器
        ClassLoader classLoader1 = classLoader.getParent();
        System.out.println(classLoader1);

        //调用扩展类加载器的getParent();无法获取引导类加载器
        //引导类加载器主要加载java的核心类库 ， 无法加载自定义类的 。
        ClassLoader parent = classLoader1.getParent();
        System.out.println(parent);

        ClassLoader classLoader2 = String.class.getClassLoader();
        System.out.println(classLoader2);
    }

    /**
     * Properties ： 用来读取配置文件
     */

    public void test2() throws Exception {

        Properties pro = new Properties() ;
        //此时的文件默认在当前的module下
        FileInputStream fis = new FileInputStream("jdbc.properties") ;
        pro.load(fis);

        //读取配置文件方式二：使用Classloader
        //配置文件默认识别为：当前module的src下
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream("jdbc1.properties");
        pro.load(resourceAsStream);

        String user = pro.getProperty("user");
        String password = pro.getProperty("password");
        System.out.println(user);
        System.out.println(password);



    }
}
