package com.qbao.log;

/**
 * @author song.j
 * @create 2017-10-17 14:14:37
 **/
public class AsyncSender implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getId());
    }
}
