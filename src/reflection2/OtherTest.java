package reflection2;
import org.junit.Test;
import reflection.Person;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 *
 */
public class OtherTest {

    /**
     * 获取构造器
     */
    @Test
    public void test1(){
        Class<Person2> person2Class = Person2.class;
        //getConstructors():获取当前运行时类中声明为public的构造器
        Constructor<?>[] constructors = person2Class.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }
        System.out.println();
        //getDeclaredConstructors():获取当前运行时类中声明的所有的构造器
        Constructor<?>[] declaredConstructors = person2Class.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor);
        }
    }


    @Test
    public void test2() throws IllegalAccessException, InstantiationException {
        Class<Person2> person2Class = Person2.class;
        Class<? super Person2> superclass = person2Class.getSuperclass();
        Object object = superclass.newInstance();
        System.out.println(object);
        System.out.println(superclass);
    }

    /**
     * 获取运行时类的带泛型的父类
     */
    @Test
    public void test3(){
        Class<Person2> person2Class = Person2.class;
        Type genericSuperclass = person2Class.getGenericSuperclass();
        System.out.println(genericSuperclass);
    }

    /**
     * 获取运行时类的带泛型的父类的泛型
     */
    @Test
    public void test4(){
        Class<Person2> person2Class = Person2.class;
        ParameterizedType parameterizedType = (ParameterizedType) person2Class.getGenericSuperclass();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        System.out.println(((Class)actualTypeArguments[0]).getTypeName());
    }
    /**
     * 获取运行时类实现的接口
     */
    @Test
    public void test5(){
        Class<Person2> person2Class = Person2.class;
        Class<?>[] interfaces = person2Class.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            System.out.println(anInterface);
        }
        System.out.println();
        //父类
        Class<?>[] interfaces1 = person2Class.getSuperclass().getInterfaces();
        for (Class<?> aClass : interfaces1) {
            System.out.println(aClass);
        }
    }

    /**
     * 获取运行时类所在的包
     */
    @Test
    public void test6(){
        Class<Person2> person2Class = Person2.class;
        Package aPackage = person2Class.getPackage();
        System.out.println(aPackage);
    }
    /**
     * 获取运行时类声明的注解
     */
    @Test
    public void test7(){
        Class<Person2> person2Class = Person2.class;
        Annotation[] annotations = person2Class.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
    }
}
