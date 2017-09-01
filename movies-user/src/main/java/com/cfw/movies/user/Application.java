package com.cfw.movies.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by Cfw on 2017/3/10.
 */
@SpringBootApplication(scanBasePackages = {
        "com.cfw.plugins",
        "com.cfw.movies.commons",
        "com.cfw.movies.user"})
@ImportResource(locations = {"classpath:ApplicationContext*.xml"})
public class Application {

    public static void main(String [] args){
        SpringApplication.run(Application.class);
    }
}
