package com.cfw.movies.logger.mq;

import com.cfw.plugins.thread.AbstractThread;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Map;
import java.util.Set;

/**
 * <p>RabbitMQ listener to receive log messages with work queue mode.</p>
 * Created by Duskrain on 2017/8/11.
 */

class RabbitLoggerThread extends AbstractThread {
    private Log logger = LogFactory.getLog(RabbitLoggerThread.class);

    private String queueName;

    private RabbitTemplate rabbitTemplate;

    public RabbitLoggerThread(String queueName){
        this.queueName = queueName;
    }

    public RabbitLoggerThread(String queueName, int threadsNumber, RabbitTemplate rabbitTemplate) throws Exception {
        super("RabbitLoggerThread",threadsNumber);
        this.queueName = queueName;
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
       while(true){
           Message message = this.rabbitTemplate.receive(this.queueName);
           // Append log message into buffer.
           if(message != null)
               this.logger.info(new String(message.getBody()));
       }
    }

    /**
     * Static way to startup RabbitMQ logger listeners.
     * @param rabbitTemplate Spring managed RabbitTemplate instance
     * @param queueThreadsMap This attribute declared how many threads to create to one queue.
     */
    public static void staticStartup(RabbitTemplate rabbitTemplate,Map<String,Integer> queueThreadsMap) throws Exception {
        Set<String> queueNameSet = queueThreadsMap.keySet();

        for(String queueName : queueNameSet){
            int threadsNumber = queueThreadsMap.get(queueName);
            new RabbitLoggerThread(queueName,threadsNumber,rabbitTemplate).startup();
        }
    }
}
