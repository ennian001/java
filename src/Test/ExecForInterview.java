package Test;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *  笔试 题联系
 */
public class ExecForInterview {
    /**
     *  输入三个整数，排序
     */
    public void test1(int[] arr){
        int temp ;
        for (int i = 0 ;i<arr.length-1;i++){
             for (int j =i ; j<arr.length-1-i;j++){
                 if (arr[j]>arr[j+1]){
                     temp = arr[j];
                     arr[j] = arr[j+1];
                     arr[j+1] = temp;
                 }
             }
        }
        for (int i  = 0 ;i<arr.length;i++){ System.out.println(arr[i]); }
    }
    /**
     *  判断当前日期是星期几
     */
    public void test2(){
        Date date = new Date();
        //E对于创建SimpleDateFormat传入的参数：EEEE代表星期，
        // 如“星期四”；MMMM代表中文月份，如“七月”；
        // MM代表月份，如“07”；yyyy代表年份，如“2017”；
        // dd代表天，如“05”
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
        String currSun = dateFm.format(date);
        System.out.println(currSun);
    }
    /**
     *  输入字母数字空格的数量
     */
    void test3(String str){
        int shuzi = 0; int zimu = 0; int kongge = 0;
        char[] c = new char[str.length()];
        for (int i = 0 ; i<str.length();i++){
            //java自带的函数，意思是对于某一个字符串,取出某个位置的字符
            c[i] = (char) str.codePointAt(i);
            if (c[i]>='0'&&c[i]<='9'){
                shuzi++;
            }
            if ((c[i]>='A'&&c[i]<='Z')||(c[i]>='a'&&c[i]<='z')){
                zimu++;
            }
            if (c[i] == ' '){
                kongge++;
            }
        }
        System.out.println("数字的个数为：　" + shuzi);
        System.out.println("字母的个数为：　" + zimu);
        System.out.println("空格的数目为：　" + kongge);
    }

    /**
     *  打乱顺序，寻找数
     */
    public int findNotInput(int n , int[] nums){
        Map<Integer,String> map = new HashMap<>();
        for (int i = 1 ; i<=n;i++ ){
            map.put(i,"1");
        }
        for (int j : nums){
            if (!map.containsKey(j))
                return j;
        }
        return 0;
    }
    /**
     *  String 类型字符串倒叙排列
     */
    public String reverse(String str){
        char[] chars = str.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = chars.length-1;i>=0;i--){
            sb.append(chars[i]);
        }
        return sb.toString();
    }
    @Test
    public void printTest3(){
       test3("I am a student whose age is 20");
    }

    @Test
    public void test1(){
        String ts = "2018-09";
        String substring = ts.substring(0, 4);
        System.out.println(substring);
    }

    @Test
    public void printTest(){
        int i = 67;
        System.out.println((char)i);
        char c = 'C' ;
        System.out.println((int) c);
    }

    //按位操作符
    public static void main(String[] args) {
        System.out.println(1&1);
        System.out.println(1&0);
        System.out.println(1^1);//异或
        System.out.println(1^0);
        //左移
        System.out.println(1<<1);
        System.out.println(1<<2);
        System.out.println(1<<3);
        System.out.println(1<<4);
        System.out.println(1<<5);
        System.out.println(1<<6);
        System.out.println(1<<7);
        System.out.println(1<<8);
        System.out.println(1<<9);
    }


}
