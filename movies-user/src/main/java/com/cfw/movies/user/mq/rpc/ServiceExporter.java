package com.cfw.movies.user.mq.rpc;

import com.cfw.plugins.mq.rabbitmq.rpc.annotation.CRpcService;
import com.cfw.plugins.mq.rabbitmq.rpc.dispatch.InboundDispatcher;
import com.cfw.plugins.mq.rabbitmq.rpc.dispatch.Selector;
import com.cfw.plugins.thread.ThreadProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

/**
 * Created by Duskrain on 2017/8/27.
 */
@Component
public class ServiceExporter {

    @Autowired
    private ThreadProperties threadProperties;

    @EventListener
    public void exposeService(ContextRefreshedEvent event){
        if(event.getApplicationContext().getParent() == null){
            Map<String,Object> servicesMap =  event.getApplicationContext().getBeansWithAnnotation(CRpcService.class);

            if(servicesMap == null || servicesMap.size() == 0)
                return ;

            Set<String> keySet = servicesMap.keySet();
            try{
                for(String serviceBeanName : keySet){
                    Object service = servicesMap.get(serviceBeanName);
                    Class [] interfaces = service.getClass().getInterfaces();
                    for(Class serviceInterface : interfaces){
                        // Search direct interface as exporter interface
                        if(service.getClass().getSimpleName().startsWith(serviceInterface.getSimpleName())){
                            Selector.put(serviceInterface.getName(),service);
                            break;
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @EventListener
    @Autowired
    public void fireDispatcher(RabbitTemplate rabbitTemplate){
        InboundDispatcher.staticStartup(rabbitTemplate,threadProperties.getInboundDispatcherThreadsNumber());
    }
}
