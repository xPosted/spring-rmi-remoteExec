package com.jubaka.remoting.classLoader.util.lang;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.server.RMIClassLoader;
import java.rmi.server.RMIClassLoaderSpi;

public class CustomRmiClassLoader extends RMIClassLoaderSpi {
   // private DynamicClassLoader dynamicLoader = new DynamicClassLoader("/home/jubaka/dev/spring-rmi/classPath/");
    private RMIClassLoader loader;

    @Override
    public Class<?> loadClass(String codebase, String name, ClassLoader defaultLoader) throws MalformedURLException, ClassNotFoundException {
      //  return sun.rmi.server.LoaderHandler.loadClass(
      //          codebase, name, defaultLoader);
        Class clazz = null;
       // if (name.startsWith("com.jubaka.remoting.client"))
        if (isInDynamicClassPath(name)&&(!name.contains("ClassConteiner")))
        {
            DynamicClassLoader dynamicLoader = new DynamicClassLoader("/home/jubaka/dev/spring-rmi/classPath/");
            clazz = dynamicLoader.loadClass(name);
        }
        else
            clazz = sun.rmi.server.LoaderHandler.loadClass(
                          codebase, name, defaultLoader);
        if (clazz == null) {
            System.err.println("ERROR: class "+name+" not found...");
        } else {
        //    System.out.println("Class "+ name + " loaded");
        }
        return clazz;
    }

    public boolean isInDynamicClassPath(String name) {
        String fullName = "/home/jubaka/dev/spring-rmi/classPath/"+name.replace(".","/")+".class";
        if (Files.exists(Paths.get(fullName))) return true;
        return false;
    }

    @Override
    public Class<?> loadProxyClass(String codebase, String[] interfaces, ClassLoader defaultLoader) throws MalformedURLException, ClassNotFoundException {
        return sun.rmi.server.LoaderHandler.loadProxyClass(
                codebase, interfaces, defaultLoader);
    }

    @Override
    public ClassLoader getClassLoader(String codebase) throws MalformedURLException {
       // return sun.rmi.server.LoaderHandler.getClassLoader(codebase);
        DynamicClassLoader dynamicLoader = new DynamicClassLoader("/home/jubaka/dev/spring-rmi/classPath/");
        return dynamicLoader;
    }

    @Override
    public String getClassAnnotation(Class<?> cl) {
        //String res= sun.rmi.server.LoaderHandler.getClassAnnotation(cl);
        return "file:/home/jubaka/dev/spring-rmi/classPath/";
    }
}
