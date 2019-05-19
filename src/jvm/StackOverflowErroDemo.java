package jvm;

public class StackOverflowErroDemo {
    //默认512k~1024k
    public static void main(String[] args) {
        stackOverflowErro();
    }
    private static void stackOverflowErro() {
        stackOverflowErro();
    }
}
