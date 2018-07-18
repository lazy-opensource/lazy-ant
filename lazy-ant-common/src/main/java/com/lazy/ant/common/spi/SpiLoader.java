package com.lazy.ant.common.spi;

import com.lazy.ant.common.exceptions.NotFoundException;
import com.lazy.ant.common.tools.AssertUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p></p>
 *
 * @author laizhiyuan
 * @date 2018/7/18.
 */
public class SpiLoader {

    private static final String spiDir = "META-INF/lazyAnt";

    private static final Map<Class, Object> spiInstances = new ConcurrentHashMap<>();

    public static Object getSpi(Class cls){
        AssertUtils.isNull(cls, "param cls is null");
        Spi spi = (Spi) cls.getAnnotation(Spi.class);
        AssertUtils.isNull(spi, "The class ".concat(cls.getName()).concat(" not found Spi Annotation"));
        Object spiInstance = spiInstances.get(cls);
        if (spiInstance != null){
            return spiInstance;
        }
        ClassLoader classLoader = SpiLoader.class.getClassLoader();
        try {
            Enumeration<URL> urls = classLoader.getResources(spiDir.concat(File.separator).concat(cls.getName()));
            if (urls == null){
                throw new NotFoundException("not found ".concat(cls.getName()).concat(" Spi"));
            }
            while (urls.hasMoreElements()){
                java.net.URL url = urls.nextElement();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
