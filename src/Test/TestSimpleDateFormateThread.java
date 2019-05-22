package Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  SimpleDateFormat 线程不安全 测试
 */
public class TestSimpleDateFormateThread {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String formatDate(Date date) throws ParseException
    {
        return sdf.format(date);
    }
    public static Date parse(String strDate) throws ParseException
    { return sdf.parse(strDate); }

    /**
     *  signal thread test
     * @param args
     * @throws InterruptedException
     * @throws ParseException
     */
//    public static void main(String[] args) throws InterruptedException, ParseException
//    { System.out.println(sdf . format  (new Date ()));}

    /**
     *  mutiply thread test
     * @param args
     */
    public static void main(String[] args) {

    }
}
