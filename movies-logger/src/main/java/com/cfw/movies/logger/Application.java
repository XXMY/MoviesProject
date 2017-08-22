package com.cfw.movies.logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Duskrain on 2017/8/11.
 */
@SpringBootApplication(scanBasePackages = {
        "com.cfw.plugins",
        "com.cfw.movies"}
)
public class Application {

    public static void main(String [] args){
        SpringApplication.run(Application.class);
    }
}
