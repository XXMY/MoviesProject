package com.cfw.movies.logger.event;


import com.cfw.movies.logger.mq.RabbitLoggerStartup;
import com.cfw.plugins.mq.rabbitmq.RabbitConfigurationProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Created by Duskrain on 2017/8/13.
 */
@Component
public class StartupEvent {

    @Autowired
    private RabbitConfigurationProperties rabbitConfigurationProperties;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @EventListener
    public void rabbitLoggerStartup(ContextRefreshedEvent event) throws Exception {
        RabbitLoggerStartup.startup(rabbitTemplate,rabbitConfigurationProperties.getMqQueueThreadsNumber());
    }


}
