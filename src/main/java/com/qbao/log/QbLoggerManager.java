package com.qbao.log;

/**
 * @author song.j
 * @create 2017-10-16 14:14:13
 **/
public class QbLoggerManager {

    public static QbLogger getLogger(Class cla) {
        return new MonitorQbLogger(cla);
    }

    public static QbLogger getLogger(Class cla,boolean send){
        return new MonitorQbLogger(cla,send);
    }

}