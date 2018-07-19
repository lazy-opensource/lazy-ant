package com.lazy.ant.common.spi;

import com.lazy.ant.common.exceptions.NotFoundException;
import com.lazy.ant.common.tools.AssertUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
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

    public static Object getSpi(Class<?> cls) {
        AssertUtils.isNull(cls, "param cls is null");
        Spi spi = cls.getAnnotation(Spi.class);
        AssertUtils.isNull(spi, "The class ".concat(cls.getName()).concat(" not found Spi Annotation"));
        Object spiInstance = spiInstances.get(cls);
        if (spiInstance != null) {
            return spiInstance;
        }
        ClassLoader classLoader = SpiLoader.class.getClassLoader();
        try {
            Enumeration<URL> urls = classLoader.getResources(spiDir.concat(File.separator).concat(cls.getName()));
            if (urls == null) {
                throw new NotFoundException("not found ".concat(cls.getName()).concat(" Spi"));
            }
            while (urls.hasMoreElements()) {
                java.net.URL url = urls.nextElement();
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
                try {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        final int notesIndex = line.indexOf('#');
                        if (notesIndex >= 0) {
                            line = line.substring(0, notesIndex);
                        }
                        line = line.trim();
                        if (line.length() > 0) {
                            String name = null;
                            int splitIndex = line.indexOf('=');
                            if (splitIndex > 0) {
                                name = line.substring(0, splitIndex).trim();
                                line = line.substring(splitIndex + 1).trim();
                            }
                            if (!spi.value().equals(name)){
                                continue;
                            }
                            if (line.length() > 0) {
                                Class<?> subClass = Class.forName(line, true, classLoader);
                                if (!cls.isAssignableFrom(subClass)) {
                                    throw new IllegalStateException("class spi ".concat(subClass.getName()).concat(" not isAssignableFrom"));
                                }
                                spiInstances.put(cls, subClass.newInstance());
                                spiInstance = spiInstances.get(cls);
                                injectSpiField(spiInstance);
                            }
                        }
                    }
                }catch (Exception ex){
                    throw new RuntimeException(ex);
                }finally {
                    reader.close();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return spiInstance;
    }

    /**
     * 注入Spi 属性
     * @param spiInstance
     */
    private static void injectSpiField(Object spiInstance){

    }
}
