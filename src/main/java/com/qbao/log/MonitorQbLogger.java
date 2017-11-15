package com.qbao.log;


import com.qbao.util.MessageFormatUtil;
import com.qbao.util.StackTraceUtil;
import org.apache.logging.log4j.message.ParameterizedMessage;

/**
 * @author song.j
 * @create 2017-10-16 14:14:21
 **/
public class MonitorQbLogger extends AbstractQbLogger {


    public MonitorQbLogger(Class cla) {
        super(getLogger(cla));
    }

    public MonitorQbLogger(Class cla,Boolean send) {
        super(getLogger(cla),send);
    }

    @Override
    public void error(String msg, Object... params) {
        super.error(msg, params);

        try {
            sendFormatMsg(msg, null, params);
        } catch (Exception e) {
            super.error(e);
        }
    }

    @Override
    public void error(String msg, Throwable cause, Object... params) {
        super.error(msg, cause, params);

        try {
            sendFormatMsg(msg, cause, params);
        } catch (Exception e) {
            super.error(e);
        }
    }

    @Override
    public void error(Throwable cause) {
        super.error(cause);

        try {
            sendFormatMsg(null,cause,null);
        } catch (Exception e) {
            super.error(e);
        }
    }


    /**
     * 消息封装。进行推送
     *
     * @param msg
     * @param cause
     * @param params
     */
    private void sendFormatMsg(String msg, Throwable cause, Object... params) {

        ParameterizedMessage matMsg;
        if (cause !=null){
            matMsg = MessageFormatUtil.toMessage(msg, cause, params);
        }else {
            matMsg = MessageFormatUtil.toMessage(msg,params);
        }

        StringBuffer formatMsg = new StringBuffer(matMsg.getFormattedMessage());
        if (matMsg.getThrowable() != null){
            formatMsg.append("\n");
            formatMsg.append(StackTraceUtil.stackTraceToString(matMsg.getThrowable()));
        }

        //发送邮件
        if (hasSendEmail()) {
            asyncSendEmail(formatMsg.toString());
        }
    }


}
