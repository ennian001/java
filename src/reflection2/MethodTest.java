package reflection2;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 获取运行时类的方法结构
 */
public class MethodTest  {

    @Test
    public void test1(){
        Class<Person2> aClass = Person2.class;
        //getMethods():获取当前运行时类集器所有父类中声明为public的方法
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
        System.out.println();
        //getDeclaredMethods():获取当前运行时类中声明的所有方法。（不包含父类中声明的方法）
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }
    }

    /*
    @Xxxx
    权限修饰符  返回值类型  方法名(参数类型1 形参名1,...) throws XxxException{}
    */
    @Test
    public void test2(){
            Class<Person2> person2Class = Person2.class;
            Method[] declaredMethods = person2Class.getDeclaredMethods();

            for (Method declaredMethod : declaredMethods) {
                //1.获取方法声明的注解
                Annotation[] annotations = declaredMethod.getAnnotations();
                for (Annotation annotation : annotations) {
                    System.out.println(annotation);
                }
                //2.权限修饰符
                System.out.print(Modifier.toString(declaredMethod.getModifiers()));

                //3.返回值类型
                System.out.print(declaredMethod.getReturnType().getName()+"\t");

                //4.方法名
                System.out.print(declaredMethod.getName());
                System.out.print("(");

                //5、形参列表
                Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
                if (!(parameterTypes == null&& parameterTypes.length == 0)){
                    for (int i = 0; i < parameterTypes.length; i++) {
                        if (parameterTypes.length -1 == i){
                            System.out.print(parameterTypes[i].getName()+"args_"+ i);
                            break;
                        }
                        System.out.print(parameterTypes[i].getName()+"args_"+i+",");
                    }
                }
                System.out.println(")");

                //6.抛出的异常
                Class[] exceptionTypes = declaredMethod.getExceptionTypes();
                if(exceptionTypes.length > 0){
                    System.out.print("throws ");
                    for(int i = 0;i < exceptionTypes.length;i++){
                        if(i == exceptionTypes.length - 1){
                            System.out.print(exceptionTypes[i].getName());
                            break;
                        }
                        System.out.print(exceptionTypes[i].getName() + ",");
                    }
                }
                System.out.println();
            }
    }
}
