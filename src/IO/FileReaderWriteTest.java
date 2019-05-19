package IO;

import org.junit.Test;

import java.io.*;

/**
 *  一、流的分类
 *  1、操作数据的单位：字节流、字符流
 *  2、数据的流向：输入流、输出流
 *  3、流的角色：节点流、处理流
 *  二、流的体系结构
 *  抽象基类                          节点流（或文件流）          缓冲流（处理流的一种）
 *  InputStream                      FileInputStreamTest          BufferedInputStream
 *  OutputStream                     FileOutputStream         BuffredOutputStream
 *  Reader                           FileReader               BuffredReader
 *  Writer                           FileWriter               BuffredWriter
 */
public class FileReaderWriteTest {
    final String Absolute_Path = "E:\\学习\\javaEE\\hello.txt";
    final String LINUX_Absolute_Path = "/media/deepin/Home/deepin/javaee/java/hello.txt";
    /**
     *  文件内容读入程序中，并速出到控制台
     *
     *  说明点:
     *  1、read()的理解：返回读入的一个字符。如果达到文件末尾，返回-1
     *  2、异常的处理：为了保证资源一定可以执行关闭操作。需要使用try-catch-finally处理
     *  3、读入文件要求一定要存在
     */
    @Test
        public void testFileReader() {
        FileReader fr = null;
        try {
            //1、实例化file类对象，指明要操作的文件
            File file = new File(Absolute_Path);
            //2、提供具体的流,具体的操作（给file类加上一条管道）
            fr = new FileReader(file);
            //3、数据读入  read()返回读入的一个字符。如果达到文件末尾，返回-1
//            int read = fr.read();
//            while (read!=-1){
//                //返回的是一个int数，转换成char。
//                System.out.print((char) read);
//                read = fr.read();
//            }
            //针对语法修改
            int data;
            while ((data  =fr.read())!= -1){
                System.out.print((char)data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4、流关闭操作
            try {
                //如果实例化完成，关闭，没有实例化会报空指针异常
                if (fr!=null){
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    /**
     *  对read()操作升级，使用read()重载的方法
     */
    @Test
    public void testFIleReader1(){
        //FIle类实例化
        File file = new File(LINUX_Absolute_Path);
        //FIleReader类实例化
        FileReader fr = null;
        try {
            fr = new FileReader(file);
            //读入操作
            //字符类型用char[]数组
            char[] cbuf = new char[5];
            int len;
            //read(char[] cbuf) , 返回每次读入cbuf数组中的字符的个数，如果达到文件末尾，返回-1；
            while ((len = fr.read(cbuf))!= -1){
                //正确写法，错误写法int i = 0 ； i< cbuf.length ; i
//                for (int i = 0 ; i<len ; i++){
//                    System.out.print(cbuf[i]);
//                }
                //方式2   -->错误的写法
                //String s = new String(cbuf);
                //System.out.print(s);
                String s = new String(cbuf,0,len);
                System.out.print(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //资源关闭
            try {
                if (fr != null)
                    fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
      }
    /**
     * 从内存中写出数据到硬盘的文件里
     * 说明:
     * 1、输出操作，对应的FIle可以不存在的。
     *   File对应硬盘中的文件如果不存在，输出过程中，会自动创建此文件。
     *   如果存在，
     *   FileWriter(file)/FileWrite(file.false)对原有文件覆盖,FileWriter(file,true)对原有文件基础上追加内容
     */
    @Test
    public void testFIleWrite() throws IOException {
        //1、提供File类的对象，指明写出到的文件
        File file = new File("hello.txt");
        //2、提供FIleWriter的对象。用于数据的写出
        FileWriter fw = new FileWriter(file);
//        在原有文件基础上追加
//        FileWriter fw = new FileWriter(file,true);
        //3、写出操作
        fw.write("I HAVA A DREAM\n");
        fw.write("you need have a dream too");
        fw.close();
    }
    @Test
    public void testCopy()  {
        //1、c创建File对象，指明读入写出的文件
        File srcFile = new File("hello.txt");
        File destFile = new File("hello2.txt");
        FileReader fr = null;  //创建输入流和输出对象
        FileWriter fw = null;
        try {
            fr = new FileReader(srcFile);
            fw = new FileWriter(destFile);
            char[] cbur = new char[5];  //数据读入和写出的操作
            int len; //记录每次读入到char[]数组中的个数
            while ((len = fr.read(cbur))!=-1){
                //每次写出len个字符
                fw.write(cbur,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭流
            try {
                if (fw!=null)
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fr!=null)
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
