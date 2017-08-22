package com.cfw.movies.logger.event;


import com.cfw.movies.commons.properties.CommonProperties;
import com.cfw.movies.logger.mq.RabbitLoggerStartup;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Created by Duskrain on 2017/8/13.
 */
@Component
public class StartupEvent {

    @Autowired
    private CommonProperties commonProperties;

    @EventListener
    @Autowired
    public void rabbitLoggerStartup(RabbitTemplate rabbitTemplate) throws Exception {
        RabbitLoggerStartup.startup(rabbitTemplate,commonProperties.getThreadsNumber());
    }


}
