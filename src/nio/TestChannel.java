package nio;
import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 *  一、通道（Channel）：用于源节点与目标节点的连接。
 *  在 Java NIO 中负责缓冲区中数据的传输。Channel 本身不存储数据，因此需要配合缓冲区进行传输。
 *  二、通道的主要实现类
 *   java.nio.channels.Channel 接口:
 *     FileChannel
 *     SocketChannel
 *     ServerSocketChannel
 *     DatagramChannel
 *   三、获取通道
 *     1.java 针对支持通道的类提供了 getChannel()方法
 *     本地IO：
 *      FileInputStreamTest 、 FileOutputStream
 *     网络IO:
 *      Socket
 *      ServerSocket
 *      DatagramSocket
 *     2.在 JDK 1.7 中的 NIO.2 针对各个通道提供了静态方法 open()
 *     3.在 JDK 1.7 中的 NIO.2 的 Files 工具类的 newByteChannel()
 *   四、通道之间的数据传输
 *   transferFrom()
 *   transferTo()
 *   五、分散(Scatter)与聚集（Gather）
 *   分散读取（Scattering Reads）：将通道中的数据分散到多个缓冲区中
 *   聚集写入（Gathering Writes）：将多个缓冲区中的数据聚集到通道中
 *   六、字符集：Charset
 *   编码:字符串 -> 字节数组
 *   解码：字节数组  -> 字符串
 */
public class TestChannel {
    //利用通道完成复制（非直接缓冲区）
    @Test
    public void test1(){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        //获取通道
        FileChannel inChannnel = null;
        FileChannel outChannel = null;
        try {
            fis = new FileInputStream("E:\\电影\\1.mp4");
            fos = new FileOutputStream("E:\\电影\\2.mp4");
            inChannnel = fis.getChannel();
            outChannel = fos.getChannel();
            //分配指定大小的缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);
            //将通道中的数据存入缓冲区
            while(inChannnel.read(buf) !=-1){
                //切换成数据读取模式
                buf.flip();
                //将数据写入通道中
                outChannel.write(buf);
                //清空缓冲区
                buf.clear();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (outChannel !=null ){
                try {
                    outChannel.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
            if (inChannnel !=null){
                try {
                    inChannnel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos !=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis !=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //使用直接缓冲区完成文件的复制（内存映射文件）
    @Test
    public void test2(){
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            inChannel =  FileChannel.open(Paths.get("E:\\电影\\1.mp4"), StandardOpenOption.READ);
            outChannel =  FileChannel.open(Paths.get("E:\\电影\\2.mp4"),StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);
            //内存映射文件
            MappedByteBuffer inMappedBuf
                    = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
            MappedByteBuffer outMappedBuf
                    = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());
            //直接对缓冲区进行数据的读写操作
            byte[] dst = new byte[inMappedBuf.limit()];
            inMappedBuf.get(dst);
            outMappedBuf.put(dst);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inChannel!=null){
                    inChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (outChannel!=null){
                    outChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //通道之间的数据传输（直接缓冲区）
    @Test
    public void test3(){
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            inChannel =  FileChannel.open(Paths.get("E:\\电影\\1.mp4"), StandardOpenOption.READ);
            outChannel =  FileChannel.open(Paths.get("E:\\电影\\2.mp4"),StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);
            outChannel.transferFrom(inChannel,0,inChannel.size());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (inChannel!=null)
                inChannel.close();
                if (outChannel!=null)
                outChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //分散和聚集
    @Test
    public void test4() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("1.txt","rw");
        //获取通道
        FileChannel channel = randomAccessFile.getChannel();
        //分配指定大小的缓冲区
        ByteBuffer allocate = ByteBuffer.allocate(100);
       // ByteBuffer allocate1 = ByteBuffer.allocate(1024);
        //分散读取
//        ByteBuffer[] buffers = {allocate,allocate1};
        ByteBuffer[] buffers = {allocate};
        channel.read(buffers);
        for (ByteBuffer by:
             buffers) {
            by.flip();
        }
        System.out.println(new String((buffers[0].array()),0,buffers[0].limit()));
        System.out.println("--------------------------------------------------------");
//        System.out.println(new String(buffers[1].array(), 0, buffers[1].limit()));
        //聚集写入
        RandomAccessFile randomAccessFile1 = new RandomAccessFile("2.txt","rw");
        FileChannel channel1 = randomAccessFile1.getChannel();
        channel1.write(buffers);
    }
}
