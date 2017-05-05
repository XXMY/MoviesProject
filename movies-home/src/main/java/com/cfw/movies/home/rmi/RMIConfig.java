package com.cfw.movies.home.rmi;

import com.cfw.movies.home.service.remote.RemoteCommentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by Cfw on 2017/5/5.
 */
@Configuration
public class RMIConfig {

    @Bean
    public RemoteCommentService initCommentService(){
        try {
            return (RemoteCommentService) Naming.lookup("rmi://127.0.0.1:1099/commentService");
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return  null;
    }
}
