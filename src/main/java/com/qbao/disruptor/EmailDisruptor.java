package com.qbao.disruptor;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.apache.logging.log4j.core.util.Log4jThreadFactory;

import java.util.concurrent.ThreadFactory;

/**
 * @author song.j
 * @create 2017-10-17 10:10:46
 **/
public class EmailDisruptor {

    private static long backgroundThreadId; // LOG4J2-471

    private static Disruptor disruptor;

    private static final int bufferSize = 1024;

    private static EmailDisruptor emailDisruptor;

    private EmailDisruptor(Disruptor disruptor) {
        this.disruptor = disruptor;
    }

    public static EmailDisruptor getInstance() {

        if (emailDisruptor == null) {
            synchronized (EmailDisruptor.class) {
                // TODO: 17/10/17 这个要改
                final ThreadFactory threadFactory = new Log4jThreadFactory("AsyncLoggerConfig-", true, Thread.NORM_PRIORITY) {
                    @Override
                    public Thread newThread(final Runnable r) {
                        final Thread result = super.newThread(r);
                        backgroundThreadId = result.getId();
                        return result;
                    }
                };
                disruptor = new Disruptor(() -> new EmailEntity(), bufferSize, threadFactory, ProducerType.SINGLE, new BusySpinWaitStrategy());
                disruptor.handleEventsWith(new EmailConsumer());
                disruptor.start();
                
                emailDisruptor = new EmailDisruptor(disruptor);
                return emailDisruptor;
            }
        }

        return emailDisruptor;
    }

    public void pushMessage(EmailEntity emailEntity) {
        disruptor.publishEvent(new EmailProvider(emailEntity));
    }


}
