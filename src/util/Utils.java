package util;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;

/**
 * 工具类
 */
public class Utils {
    /**
     * 格式 yyyy-MM-dd
     * @return 时间间隔数组 返回
     */
    public void getTimeInterval(String startTime,String endTime){
        //获取年月日 int类型
        int start_year = Integer.parseInt(startTime.substring(0, 4));
        int start_month = Integer.parseInt(startTime.substring(5, 7));
        int start_day = Integer.parseInt(startTime.substring(8, 10));
        //获取年月日 int类型
        int end_year = Integer.parseInt(endTime.substring(0, 4));
        int end_month = Integer.parseInt(endTime.substring(5, 7));
        int end_day = Integer.parseInt(endTime.substring(8, 10));
        LocalDate startLocal = LocalDate.of(start_year,start_month,start_day);
        LocalDate  endLocal= LocalDate.of(end_year,end_month,end_day);
        Period between = Period.between(startLocal, endLocal);
        //相隔多少年
        int years = between.getYears();
        //相隔多少月
        int months = between.getMonths();
        //初始化数组大小
        String [] time = new String[months];

        int mounth = start_month;
        int year = start_year;
        for (int i = 0; i<months;i++){
            if (mounth == 12){
                year++;
                mounth = 1;
            }
             time[i] = year +"-"+mounth;
            mounth++;
        }
        Arrays.asList(time).forEach(System.out::println);
    }
    @Test
    public void test(){
        getTimeInterval("2018-09-01","2019-01-01");
    }
}
