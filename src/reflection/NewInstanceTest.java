package reflection;

import org.junit.Test;

import java.util.Random;

/**
 * 通过反射创建对应的运行时类的对象
 */
public class NewInstanceTest {

    @Test
    public void test1() throws IllegalAccessException, InstantiationException {
        Class<Person> clazz = Person.class;
        /**
         * new Instance() : 调用次方法 ， 创建对应的运行时类的对象  。 内部调用了运行时类的空参构造器 。
         * 在javaBean钟要求提供一个public的空参构造器 。 原因：
         * 1、便于通过反射 ， 创建运行时类的对象 。
         * 2、编译子类继承此运行时类时 ， 默认调用super() ,保证父类有此构造器
         */
        Person person = clazz.newInstance();
        System.out.println(person);
    }
    /**
     * 体现反射的动态性
     */
    public Object getInstance(String classPath) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> aClass = Class.forName(classPath);
        return aClass.newInstance() ;
    }
    @Test
    public void test2(){
        for (int i = 0; i < 100; i++) {
            //0 1 2
            int num = new Random().nextInt(3);
            String classPath = "" ;
            switch (num) {
                case 0 :
                    classPath = "java.util.Date" ;
                    break;
                case 1 :
                    classPath = "java.lang.Object";
                    break;
                case 2 :
                    classPath = "reflection.Person" ;
                    break;
            }
            try {
                Object instance = getInstance(classPath);
                System.out.println(instance);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }
}
