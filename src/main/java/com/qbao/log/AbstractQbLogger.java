package com.qbao.log;

import com.qbao.util.EmailUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

/**
 * @author song.j
 * @create 2017-10-17 09:09:08
 **/
public abstract class AbstractQbLogger extends LogManager implements QbLogger {

    protected static Logger log = null;

    /**
     * 是否发送短信开关。
     */
    public static boolean sendEnable = true;

    public AbstractQbLogger(Logger log) {
        this.log = log;
    }

    public AbstractQbLogger(Logger log, boolean sendEnable) {
        this.log = log;
        this.sendEnable = sendEnable;
    }

    public void sendEmail(String msg) {


        if (sendEnable)
        //send email
        EmailUtil.getInstance().sendEmail(msg);


    }

    public boolean hasSendEmail() {
        return Constant.sendEmailEnable;
    }

    /**
     * 异步发送消息
     *
     * @param msg 消息
     */
    public void asyncSendEmail(String msg) {
        new Thread(() -> {
            sendEmail(msg);
        }).start();
    }


    public void sendPhone() {
        if (Constant.sendMessageEnable) {
            //send phone message
            System.out.println("发送手机信息");
        }
    }


    @Override
    public boolean isTraceEnabled() {
        return false;
    }

    @Override
    public boolean isDebugEnabled() {
        return false;
    }

    @Override
    public boolean isInfoEnabled() {
        return false;
    }

    @Override
    public boolean isWarnEnabled() {
        return false;
    }

    @Override
    public boolean isErrorEnabled() {
        return false;
    }

    @Override
    public void trace(String msg, Object... params) {
        log.trace(msg, params);
    }

    @Override
    public void trace(String msg, Throwable cause, Object... params) {
        log.trace(msg, cause, params);
    }

    @Override
    public void trace(Throwable cause) {
        log.trace(cause);
    }

    @Override
    public void debug(String msg, Object... params) {
        log.debug(msg, params);
    }

    @Override
    public void debug(String msg, Throwable cause, Object... params) {
        log.debug(msg, cause, params);
    }

    @Override
    public void debug(Throwable cause) {
        log.debug(cause);
    }

    @Override
    public void info(String msg, Object... params) {
        log.info(msg, params);
    }

    @Override
    public void searchLog(Map<String, String> map) {

    }

    @Override
    public void info(String msg, Throwable cause, Object... params) {
        log.info(msg, cause, params);
    }

    @Override
    public void info(Throwable cause) {
        log.info(cause);
    }

    @Override
    public void warn(String msg, Object... params) {
        log.warn(msg, params);
    }

    @Override
    public void warn(String msg, Throwable cause, Object... params) {
        log.warn(msg, cause, params);
    }

    @Override
    public void warn(Throwable cause) {
        log.warn(cause);
    }

    @Override
    public void error(String msg, Object... params) {
        log.error(msg, params);
    }

    @Override
    public void error(String msg, Throwable cause, Object... params) {
        log.error(msg, cause, params);
    }

    @Override
    public void error(Throwable cause) {
        log.error(cause);
    }


    @Override
    public void send(String message) {
        asyncSendEmail(message);
        info(message);
    }
}
