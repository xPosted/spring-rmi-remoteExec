package com.jubaka.remoting.model.impl;

import com.jubaka.remoting.classLoader.util.lang.DynamicClassLoader;
import com.jubaka.remoting.config.SpringBootStarter;
import com.jubaka.remoting.model.RemoteClassLoader;
import com.jubaka.remoting.model.dto.ClassConteiner;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;

/**
 * Created by root on 05.01.18.
 */
public class RemoteClassLoaderImpl implements RemoteClassLoader {



    @Override
    public boolean loadClass(ClassConteiner cContainer) {
        try {
            saveClass(cContainer);
            DynamicClassLoader dcLoader = new DynamicClassLoader(SpringBootStarter.getClassPath());
            Class<?> clazz =  dcLoader.loadClass(cContainer.getClassName());
            Class.forName(cContainer.getClassName(),true,dcLoader);
            System.out.println(clazz.getName());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean saveClass(ClassConteiner cc) throws FileNotFoundException, IOException {
        String classPwd = SpringBootStarter.getClassPath() + cc.getPackageName().replace(".","/");
        String className = SpringBootStarter.getClassPath() + cc.getClassName().replace(".","/") + ".class";
        Files.deleteIfExists(Paths.get(className));

        //Files.createDirectories(Paths.get(classPwd), null);
        new File(classPwd).mkdirs();
        Files.write(Paths.get(className),cc.getBytecode(), StandardOpenOption.CREATE_NEW);
        return true;
    }
}
