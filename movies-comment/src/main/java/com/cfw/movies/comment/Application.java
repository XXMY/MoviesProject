package com.cfw.movies.comment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Cfw on 2017/3/10.
 */
@SpringBootApplication(scanBasePackages = {
        "com.cfw.movies.comment",
        "com.cfw.movies.commons"})
public class Application {

    public static void main(String [] args){
        SpringApplication.run(Application.class);
    }
}
