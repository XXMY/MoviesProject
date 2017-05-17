package com.cfw.movies.login.rmi;

import com.cfw.movies.commons.properties.CommonProperties;
import com.cfw.movies.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

/**
 * Created by Cfw on 2017/5/5.
 */
//@Configuration
public class RMIConfig {

    @Autowired
    private CommonProperties commonProperties;

    @Autowired
    private UserService userService;

    public RMIConfig(){}

    //@Bean("userServiceExporter")
    public RmiServiceExporter commentServiceExporter(){

        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceInterface(UserService.class);
        exporter.setServiceName("userService");
        exporter.setService(userService);
        exporter.setRegistryPort(CommonProperties.getLoginRmiPort());

        return exporter;
    }
}
