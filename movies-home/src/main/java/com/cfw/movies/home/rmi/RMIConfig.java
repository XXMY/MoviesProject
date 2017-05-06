package com.cfw.movies.home.rmi;

import com.cfw.movies.comment.service.CommentService;
import com.cfw.movies.commons.properties.CommonProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

/**
 * Created by Cfw on 2017/5/5.
 */
@Configuration
public class RMIConfig {

    @Autowired
    private CommonProperties commonProperties;

    @Bean("commentService")
    public RmiProxyFactoryBean initRmiProxyFactoryBean(){
        RmiProxyFactoryBean factoryBean = new RmiProxyFactoryBean();
        factoryBean.setServiceUrl(CommonProperties.getCommentRmiUrl(null));
        factoryBean.setServiceInterface(CommentService.class);

        return factoryBean;
    }
}
