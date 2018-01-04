package com.jubaka.remoting.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by root on 04.01.18.
 */

@SpringBootApplication
@ImportResource("classpath:app-config.xml")
public class SpringBootStarter {

    public static void main(String[] args) {
        System.setProperty("java.rmi.server.useCodebaseOnly","false");
        SpringApplication.run(SpringBootStarter.class,args);
    }
}
