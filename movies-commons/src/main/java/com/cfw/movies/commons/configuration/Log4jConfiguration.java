package com.cfw.movies.commons.configuration;

import com.cfw.movies.commons.properties.CommonProperties;
import com.cfw.plugins.logger.appender.mq.RabbitMQAppender;
import com.cfw.plugins.mq.rabbitmq.send.QueueSender;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Duskrain on 2017/8/6.
 */
@Configuration
public class Log4jConfiguration {

    @Autowired
    public Log4jConfiguration(CommonProperties commonProperties,RabbitTemplate rabbitTemplate){
        RabbitMQAppender.setSender(new QueueSender(CommonProperties.getModuleName(),rabbitTemplate));
    }
}
