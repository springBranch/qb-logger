package com.qbao.disruptor;

import com.lmax.disruptor.EventTranslator;

/**
 *
 * 生产消息
 * @author song.j
 * @create 2017-10-17 10:10:45
 **/
public class EmailProvider implements EventTranslator<EmailEntity> {


    private EmailEntity emailEntity;

    @Override
    public void translateTo(EmailEntity event, long sequence) {

        event.setId(emailEntity.getId());
        event.setName(emailEntity.getName());
    }




    public EmailProvider(EmailEntity emailEntity) {

        this.emailEntity = emailEntity;
    }
}
