package com.cfw.movies.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Created by Cfw on 2017/3/10.
 */
@SpringBootApplication(scanBasePackages = {
        "com.cfw.plugins",
        "com.cfw.movies.commons",
        "com.cfw.movies.home",
        "com.cfw.movies.recommend",
        "com.cfw.movies.manage"})
@ServletComponentScan
public class Application {

    public static void main(String [] args){
        SpringApplication.run(Application.class);
    }
}
