package reflection2;

import org.junit.Test;
import reflection.Person;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 获取当前运行时类的属性
 */
public class FieldTest {

    @Test
    public void test1(){
        Class<Person2> aClass = Person2.class;

        //获取属性结构
        //getFields(): 获取当前运行时类及其父类中声明为public访问权限的属性
        Field[] fields = aClass.getFields();

        for (Field field : fields){
            field.setAccessible(true);
            System.out.println(field);
        }
        System.out.println();

        //getDeclaredFields():获取当前运行时类中声明的所有属性。 （不包含父类中声明的属性）
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }
    }

    //权限修饰符 数据类型 变量名
    @Test
    public void test2(){
        Class<Person2> aClass = Person2.class;
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field f : declaredFields) {
            //1.权限修饰符
            int modifiers = f.getModifiers();
            System.out.println(Modifier.toString(modifiers));
            //2.数据类型
            Class<?> type = f.getType();
            System.out.println(type.getName()+"\t");
            //3.变量名
            String name = f.getName();
            System.out.println(name);

            System.out.println();
        }
    }

}
