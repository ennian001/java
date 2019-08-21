package IO;


import org.junit.Test;

import java.io.*;

/**
 *  RandomAccessFile的使用
 *  1、RandomAccessFile直接继承与java.lang.Object类，实现了DataInput和DataOutput接口
 *  2、RandomAccessFile既可以作为一个输入流，又可以作为一个输出流
 *  3、如果RandomAccessFile作为输入流时，写到的文件如果不存在，则在执行的过程中自动创建
 *     如果写到的文件存在，则会对原有的文件内容进行覆盖。（默认情况下，从头覆盖）
 *  4. 可以通过相关的操作，实现RandomAccessFile“插入”数据的效果
 */
public class RandomAccessFileTest {

    @Test
    public void test1()  {

        //1
        RandomAccessFile raf1 = null;
        RandomAccessFile raf2 = null;
        try {
            raf1 = new RandomAccessFile(new File("爱情与友情.jpg"),"r");
            raf2 = new RandomAccessFile(new File("爱情与友情1.jpg"),"rw");
            byte[] buffer = new byte[1024];
            int len ;
            while ((len = raf1.read(buffer))!=-1){
                raf2.write(buffer,0,len);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            //2
            try {
                if (raf2 == null) {
                    raf1.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (raf2 == null) {
                    raf2.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
    //实现文件的替换
    @Test
    public void test2() throws IOException {
       RandomAccessFile raf1 = new RandomAccessFile("hello.txt","rw");

       raf1.seek(3);//将指针调到角标为3的位置
       raf1.write("xyz".getBytes());
       raf1.close();
    }
    /**
     *  使用RandomAccessFile实现数据的插入操作
     */
    @Test
    public void test3() throws IOException {
       RandomAccessFile raf1 = new RandomAccessFile("hello.txt","rw");
        raf1.seek(3);//将指针调到角标为3的位置
        //保存指针3后面的所有数据到StringBuilder中
        //StringBuilder stringBuilder = new StringBuilder((int) new File("hello.txt").length());
        //方式二、改造成为ByteArrayOutputStream
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[20];
        int len ;
        while ((len = raf1.read(buffer))!=-1){
            //stringBuilder.append(new String(buffer,0,len));
            //写到ByteArrayOutputStream     字节数组中
            baos.write(buffer,0,len);
        }
        //调会指针，写入“xyz”
        raf1.seek(3);
        raf1.write("xyz".getBytes());
        //将StringBuilder中的数据写入到文件中
        //raf1.write(stringBuilder.toString().getBytes());
        raf1.write(baos.toByteArray());
        raf1.close();
    }


}
