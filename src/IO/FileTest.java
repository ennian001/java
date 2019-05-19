package IO;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 *  File类的使用
 *  1.File类的一个对象，代表一个文件或一个文件目录（俗称：文件夹）
 *  2、File类声明在java.io包下
 *  3、File类中涉及到关于文件或文件目录的创建、删除、重命名、修改时间、大小等方法，
 *  并未涉及到写入或读取文件内容的操作。如果需要读取或写入文件内容，必须使用IO流来操作
 *  4、后续File类的对象常会作为参数传递到流的构造器中，指明读取或写入的“终点”
 */
public class FileTest {
    /**
     * 1、如何创建File类的实例
     * 2、相对路径：
     *    绝对路径
     */
    @Test
    public void test1(){
        //构造器1：
        File file1 = new File("hello.text");
        File file2 = new File("E:\\学习\\javaEE\\he.txt");
        File file = new File("E:"+File.separator+"学习"+File.separator+"hel.txt");
        //构造器2
        File file3 = new File("E:\\学习","javaEE");
        System.out.println(file3);
        //构造器3
        File file4 = new File(file3,"hi.txt");
        System.out.println(file4);
    }
    @Test
    public void test2(){
        File file1 = new File("hello.txt");
        File file2 = new File("D:\\io\\hi.txt");
        //获取绝对路径
        System.out.println(file1.getAbsolutePath());
        //获取路径
        System.out.println(file1.getPath());
        //获取名称
        System.out.println(file1.getName());
        //获取上层文件目录路径，若无，返回null
        System.out.println(file1.getParent());
        //获取文件长度
        System.out.println(file1.length());
        //获取最后一次修改的时间，毫秒值
        System.out.println(file1.lastModified());
        System.out.println("-------------");
        //获取绝对路径
        System.out.println(file2.getAbsolutePath());
        //获取路径
        System.out.println(file2.getPath());
        //获取名称
        System.out.println(file2.getName());
        //获取上层文件目录路径，若无，返回null
        System.out.println(file2.getParent());
        //获取文件长度
        System.out.println(file2.length());
        //获取最后一次修改的时间，毫秒值
        System.out.println(new Date(file2.lastModified()));
    }
    @Test
    public void test3(){
        File file1 = new File("hello.txt");
        System.out.println(file1.isDirectory());//是否时一个文件目录
        System.out.println(file1.isFile());//是否是文件
        System.out.println(file1.exists());//是否存在
        System.out.println(file1.canRead());//是否可读
        System.out.println(file1.canWrite());//是否可写
        System.out.println(file1.isHidden());//是否被隐藏
    }

    /**
     * 创建硬盘中对应的文件或文件目录
     * public boolean createNewFile() 创建文件，若文件存在，则不创建，返回false
     * public boolean mkdir() 创建文件目录 。 如果此文件目录存在 ，
     *                    就不创建了。如果上层目录不存在，就不创建了
     * public boolean mkdirs() 创建文件目录 。 如果上层文件目录不存在 ， 一并创建
     *  删除磁盘中的文件或文件目录
     *  public boolean delete() ： 删除文件或文件夹
     *  //删除注意的事项：
     *  java中的删除不走回收站
     */
    @Test
    public void test6() throws IOException {
        File file = new File("create.txt");
        if (!file.exists()) {
            file.createNewFile();
            System.out.println("创建成功");
        }
        else{
            //
            file.delete();
            System.out.println("删除成功");
        }
    }
    @Test
    public void test7() throws IOException {
        File file = new File("d:\\in\\in");
        boolean mkdirs = file.mkdirs();
        System.out.println(mkdirs);
    }
}
