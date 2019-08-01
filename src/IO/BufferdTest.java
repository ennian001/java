package IO;

import org.junit.Test;

import java.io.*;

/**
 * 处理流之一：缓冲流的使用
 * 1、缓冲流：
 *  BufferdInputStream
 *  BufferdOutputStream
 *  BufferdReader
 *  BufferdWriter
 * 2、作用 ：提供流的读取、写入的速度
 *   原因：内部含有缓冲区 大小8192
 *   public BufferedOutputStream(OutputStream out) {
       this(out, 8192);
 }
  3、处理流，就是"套接"在已有的流的基础上

 */
public class BufferdTest {
    /**
     * 实现非文本文件的复制
     */
    @Test
    public void BufferedStreamTest() {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            //1、造文件
            File srcFile =
                    new File("test.jpg");
            File destFile =
                    new File("test2.jpg");
            //2、造流
            //造节点流
            FileInputStream  fis = new FileInputStream(srcFile);
            FileOutputStream fos = new FileOutputStream(destFile);
            //3、造缓冲流
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);
            //复制细节
            int len ;
            byte[] bytes = new byte[1024];
            while ((len = bis.read(bytes))!=-1){
                bos.write(bytes,0,len);
//                bos.flush();刷新缓冲区,不等到8192就写入
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //资源关闭先关外层流、后关内层流（可以省略）
            if (bos!=null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bis!=null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * 使用 BufferdReader 和 BufferdWriter 实现文本文件的复制
     */
    @Test
    public void testBufferdReaderBufferdWriter(){
        //创建文件和相应的流
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader(new File("1.txt")));
            bw = new BufferedWriter(new FileWriter(new File("dbcp.txt")));
            //读写操作
            //方法一：
//            char[] cbuf = new char[1024];
//            int len ;
//            while ((len = br.read(cbuf))!= -1){
//                bw.write(cbuf,0,len);
//            }
            //方式2、
            //使用String
            String data; //每次读取一行
            while ((data = br.readLine())!=null){
                System.out.println(data);
                //方法1：
                bw.write(data+"\n");//data不包含换行符
                //方法2
//                bw.write(data);
//                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭资源
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
