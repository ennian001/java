package IO;

import org.junit.Test;

import java.io.*;

/**
 * FileInputStreamTest\FileOutputStream的使用
 *结论：
 *  1、对于文本文件（.txt\.java\.c\.cpp）使用字符流处理
 *  2、对于非文本文件（.jpg\.mp3\.mp4\.avi\.doc\.ppt......），使用字节流处理
 */
public class FileInputStreamTest {
    //使用FileinputStream来处理文本文件可能出现乱码
    @Test
    public void testFileINputStream(){
        //有汉字会乱码，英文占一个字节，汉字占用三个字节（UTF8）
        //1、选文件
        File file = new File("hello.txt");
        //2、造流
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            byte[] bytes = new byte[5];
            int length;//记录每次读取字节的个数
            //读数据
            while ((length = fis.read(bytes))!=-1){
                String str = new String(bytes,0,length);
                System.out.print(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fis!=null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * 实现图片的复制
     */
    @Test
    public void copyPicture() {
        File srcFile = new File("test.jpg");
        File destFile = new File("test2.jpg");
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);
            byte [] bytes = new byte[5];
            int len ;
            while ((len = fis.read(bytes))!=-1){
                fos.write(bytes,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (fis!=null) {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (fos!=null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
