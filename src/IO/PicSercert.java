package IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *  图片加密
 */
public class PicSercert {
    //图片加密
    public void test(){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream("未加密的图片.jpg");
            fos = new FileOutputStream("加密后的图片");
            byte[] bytes = new byte[1024];
            int len;
            while ((len = fis.read(bytes))!=-1){
                //文件加密---错误的写法,增强for实际并没有修改
//                for (byte b:bytes) {
//                    b = (byte)(b^5);
//                }
                //正确的
                for (int i = 0;i<len;i++){
                    bytes[i] = (byte)(bytes[i]^5);
                }
                fos.write(bytes,0,len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //图片解密，再^一下
    public void test2(){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream("未加密的图片.jpg");
            fos = new FileOutputStream("加密后的图片");
            byte[] bytes = new byte[1024];
            int len;
            while ((len = fis.read(bytes))!=-1){
                //文件加密---错误的写法,增强for实际并没有修改
//                for (byte b:bytes) {
//                    b = (byte)(b^5);
//                }
                //正确的
                for (int i = 0;i<len;i++){
                    bytes[i] = (byte)(bytes[i]^5);
                }
                fos.write(bytes,0,len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
