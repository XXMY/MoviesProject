package com.cfw.movies.login.configuration;

import com.cfw.plugins.mq.rabbitmq.RabbitConfigurationProperties;
import com.cfw.plugins.mq.rabbitmq.send.RoutingSender;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Cfw on 2017/5/5.
 */
@Configuration
public class RPCConfiguration {

    @Autowired
    private RabbitConfigurationProperties configurationProperties;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Bean(name = "routingSender")
    public RoutingSender createRoutingSender(){
        String rpcBindingRelation = configurationProperties.getBindingRelations().get(0);
        String [] relationArray = rpcBindingRelation.split("/",4);

        return new RoutingSender(relationArray[0],relationArray[1],relationArray[3],rabbitTemplate);
    }


}
