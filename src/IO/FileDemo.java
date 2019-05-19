package IO;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FileDemo {
    @Test
    public void test1() throws IOException {
        //创建一个与file同目录下的另外一个文件，文件名为：haha.txt
        File file1 =
                new File("D:\\in\\in\\hello.txt");
        File destFile = new File(file1.getParent(), "haha.txt");
        boolean newFile = destFile.createNewFile();
        if (newFile){
            System.out.println("创建成功");
        }
    }
    @Test
    public void test2(){
        //判断指定目录下是否有后缀名为.jpg的文件，如果有就输出该文件名称
        File file= new File("/media/home");
        String[] list = file.list();
        for (String s :list){
            if (s.endsWith(".jpg"))
                System.out.println(s);
        }
    }
}
