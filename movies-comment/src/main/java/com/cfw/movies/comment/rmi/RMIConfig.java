package com.cfw.movies.comment.rmi;

import com.cfw.movies.login.service.UserService;
import com.cfw.movies.comment.service.CommentService;
import com.cfw.movies.commons.properties.CommonProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.remoting.rmi.RmiServiceExporter;


/**
 * Created by Cfw on 2017/5/5.
 */
//@Configuration
public class RMIConfig {

    @Autowired
    private CommonProperties commonProperties;

    @Autowired
    private CommentService commentService;

    public RMIConfig(){}

    //@Bean("commentServiceExporter")
    public RmiServiceExporter commentServiceExporter(){

        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceInterface(CommentService.class);
        exporter.setServiceName("commentService");
        exporter.setService(commentService);
        exporter.setRegistryPort(CommonProperties.getCommentRmiPort());

        return exporter;
    }

    //@Bean("userService")
    public RmiProxyFactoryBean initUserService(){
        RmiProxyFactoryBean factoryBean = new RmiProxyFactoryBean();
        factoryBean.setServiceUrl(CommonProperties.getLoginRmiUrl("userService"));
        factoryBean.setServiceInterface(UserService.class);
        factoryBean.setRefreshStubOnConnectFailure(true);
        //factoryBean.setLookupStubOnStartup(false);

        return factoryBean;
    }
}
