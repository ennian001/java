package IO;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *  一、流的分类
 *  1、操作数据的单位：字节流、字符流
 *  2、数据的流向：输入流、输出流
 *  3、流的角色：节点流、处理流
 *  二、流的体系结构
 *  抽象基类                          节点流（或文件流）          缓冲流（处理流的一种）
 *  InputStream                      FileInputStream          BufferedInputStream
 *  OutputStream                     FileOutputStream         BuffredOutputStream
 *  Reader                           FileReader               BuffredReader
 *  Writer                           FileWriter               BuffredWriter
 */
public class FileReaderWriteTest {
    final String Absolute_Path = "E:\\学习\\javaEE\\hello.txt";
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
}
