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

}
