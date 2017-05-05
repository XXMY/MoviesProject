package com.cfw.movies.comment.rmi;

import com.cfw.movies.comment.service.CommentService;
import com.cfw.movies.comment.service.remote.RemoteCommentService;
import com.cfw.movies.comment.service.remote.impl.RemoteCommentServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by Cfw on 2017/5/5.
 */
@Configuration
public class RMIConfig {

    private RemoteCommentService remoteCommentService;

    private final static int port = 1111;

    private Log logger = LogFactory.getLog(RMIConfig.class);

    public RMIConfig(){}

    @Autowired
    public RMIConfig(RemoteCommentService remoteCommentService){
        this.remoteCommentService = remoteCommentService;
        /*
        try {
            Registry registry = LocateRegistry.createRegistry(port);
            Naming.rebind("rmi://127.0.0.1:"+port+"/commentService",remoteCommentService);
            this.logger.info("RMI bind on port: " + port);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        */
    }

    @Bean
    public RmiServiceExporter initRmiServiceExporter(){

        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceInterface(RemoteCommentService.class);
        exporter.setServiceName("commentService");
        exporter.setService(remoteCommentService);
        exporter.setServicePort(port);

        return exporter;
    }
}
