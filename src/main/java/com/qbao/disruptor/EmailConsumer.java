package com.qbao.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * @author song.j
 * @create 2017-10-17 10:10:44
 **/
public class EmailConsumer implements EventHandler<EmailEntity> {
    @Override
    public void onEvent(EmailEntity event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("消费开始");
        System.out.println(event.toString());
    }
}
