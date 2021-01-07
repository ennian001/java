package jvm;

public class ClassInitTest1 {

    static class Father{
        public static  int A = 1 ;
        static {
            A = 2 ;
        }
    }

    static class  Son extends Father {

        public static int B = A ;

    }

    public static void main(String[] args) {
        //加载Father类再加载Son类
        System.out.println(Son.B);
    }

}
