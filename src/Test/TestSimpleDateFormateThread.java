package Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 *  SimpleDateFormat 线程不安全 测试
 */
public class TestSimpleDateFormateThread {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String formatDate(Date date) throws ParseException
    {
        return sdf.format(date);
    }
    //线程不安全
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
    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(100);
        for (int i=0;i<20;i++){
            es.execute(
                    ()->{
                        for (int j=0;j<10;j++){
                            try {
                                //线程不安全
//                                System.out.println(parse("2018-01-02 02:02:59"));
                                System.out.println(parse2(formatDate2(LocalDateTime.now())));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
            );
        }
        es.shutdown();
        es.awaitTermination(1, TimeUnit.DAYS);
    }
    //解决方案  ali 开发手册使用ThreadLocal
    //jdk 1.8
    private static DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static String formatDate2(LocalDateTime dateTime){
        return formatter.format(dateTime);
    }
    public static LocalDateTime parse2(String dateNow)
    { return LocalDateTime.parse(dateNow, formatter); }
}
