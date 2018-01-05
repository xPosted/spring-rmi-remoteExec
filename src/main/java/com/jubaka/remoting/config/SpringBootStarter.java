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



    public static String classPath = "/home/jubaka/dev/spring-rmi/classPath/";

    public static void main(String[] args) {
        System.setProperty("java.rmi.server.useCodebaseOnly","false");
        SpringApplication.run(SpringBootStarter.class,args);
    }

    public static String getClassPath() {
        return classPath;
    }

    public static void setClassPath(String classPath) {
        SpringBootStarter.classPath = classPath;
    }

}
