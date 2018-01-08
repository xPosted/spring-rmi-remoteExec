package com.jubaka.remoting.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.remoting.rmi.RmiRegistryFactoryBean;
import org.springframework.remoting.rmi.RmiServiceExporter;

import java.rmi.RMISecurityManager;

/**
 * Created by root on 04.01.18.
 */

@SpringBootApplication
@ImportResource("classpath:app-config.xml")
public class SpringBootStarter {



    public static String classPath = "/home/jubaka/dev/spring-rmi/classPath/";

    public static void main(String[] args) {
        //System.setSecurityManager(new RMISecurityManager());
        System.setSecurityManager(new SecurityManager());
        System.setProperty("java.rmi.server.useCodebaseOnly","false");
        System.setProperty("java.rmi.server.codebase","file:/home/jubaka/dev/spring-rmi/classPath/");
        SpringApplication.run(SpringBootStarter.class,args);

    }

    public static String getClassPath() {
        return classPath;
    }

    public static void setClassPath(String classPath) {
        SpringBootStarter.classPath = classPath;
    }

}
