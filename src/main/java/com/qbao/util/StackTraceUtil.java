package com.qbao.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author song.j
 * @create 2017-10-17 16:16:00
 **/
public class StackTraceUtil {

    public static String stackTraceToString(Throwable t){
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw, true));
        return sw.getBuffer().toString();
    }


}
