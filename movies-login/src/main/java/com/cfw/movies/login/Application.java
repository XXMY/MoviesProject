package com.cfw.movies.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Created by Cfw on 2017/3/10.
 */
@SpringBootApplication(scanBasePackages = {
        "com.cfw.plugins",
        "com.cfw.movies.commons",
        "com.cfw.movies.login"})
public class Application {

    public static void main(String [] args){
        SpringApplication.run(Application.class);
    }
}
