package delayqueuedemo;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Message implements Delayed {

    private int id;
    private String body;//消息内容
    private long excuteTime;// 延迟时长，这个是必须的属性因为要按照这个判断延时时长

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getExcuteTime() {
        return excuteTime;
    }

    public void setExcuteTime(long excuteTime) {
        this.excuteTime = excuteTime;
    }

    public Message(int id, String body, long delayTime) {
        this.id = id;
        this.body = body;
        //获取千分之一微秒时间单位
        this.excuteTime = TimeUnit.NANOSECONDS.convert(delayTime,TimeUnit.MILLISECONDS)+System.nanoTime();
    }

    /**
     * getDelay定义了剩余到期时间
     * 延迟任务是否到时，就是按照这个方法判断，如果返回的是负数，则说明到期否则还没到期
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.excuteTime-System.nanoTime(),TimeUnit.NANOSECONDS);
    }
    /**
     * compareTo方法定义了元素排序规则，注意，元素的排序规则影响了元素的获取顺序
     * 注意，排序后的结果先进先出
     * @param o
     * @return
     */
    @Override
    public int compareTo(Delayed o) {
        Message msg = (Message)o;
        return Integer.valueOf(this.id)>Integer.valueOf(msg.id)?1:
                Integer.valueOf(this.id)<Integer.valueOf(msg.id)?-1:0;
    }
}
