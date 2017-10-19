package com.qbao.util;

import org.apache.logging.log4j.message.ParameterizedMessage;

/**
 * @author song.j
 * @create 2017-10-17 15:15:35
 **/
public class MessageFormatUtil {

    public static String formatMsg(String msg,Object... param){
        ParameterizedMessage message = new ParameterizedMessage(msg,param);

        StringBuffer emailMsg = new StringBuffer(message.getFormattedMessage());

        if (message.getThrowable()!=null){
            emailMsg.append("\n");
            emailMsg.append(StackTraceUtil.stackTraceToString(message.getThrowable()));
        }

        return emailMsg.toString();
    }


    public static ParameterizedMessage toMessage(String msg,Throwable cause,Object... param){
        ParameterizedMessage mssage = new ParameterizedMessage(msg, new Object[]{param}, cause);
        return mssage;
    }

    public static ParameterizedMessage toMessage(String msg,Object... param){
        ParameterizedMessage mssage = new ParameterizedMessage(msg, param);
        return mssage;
    }

}
