package com.lazy.ant.common.spi;

import com.lazy.ant.common.exceptions.NotFoundException;
import com.lazy.ant.common.tools.AssertUtils;
import org.springframework.context.ApplicationContext;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 *     Spi Loader
 * </p>
 *
 * @author laizhiyuan
 * @date 2018/7/18.
 */
public class SpiLoader {

    /**
     * Spi 目录
     */
    private static final String spiDir = "META-INF/lazyAnt";
    /**
     * Spi 实例池
     */
    private static final Map<Class, Object> spiInstancePools = new ConcurrentHashMap<>();

    /**
     * 获取SPI
     * @param cls
     * @return
     */
    public static Object getSpi(Class<?> cls, ApplicationContext applicationContext) {
        AssertUtils.isNull(cls, "param cls is null");
        AssertUtils.isNull(applicationContext, "param applicationContext is null");
        Spi spi = cls.getAnnotation(Spi.class);
        AssertUtils.isNull(spi, "The class ".concat(cls.getName()).concat(" not found Spi Annotation"));
        Object spiInstance = spiInstancePools.get(cls);
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
                                spiInstancePools.put(cls, subClass.newInstance());
                                spiInstance = spiInstancePools.get(cls);
                                injectSpiField(spiInstance, applicationContext);
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
     * @param applicationContext
     */
    private static void injectSpiField(Object spiInstance, ApplicationContext applicationContext){
        Method[] methods = spiInstance.getClass().getMethods();
        for (Method method : methods) {
            if (method.getName().startsWith("set")
                    && method.getParameterTypes().length == 1
                    && Modifier.isPublic(method.getModifiers())) {
                Class<?> pt = method.getParameterTypes()[0];
                try {
                    String property = method.getName().length() > 3 ? method.getName().substring(3, 4).toLowerCase() + method.getName().substring(4) : "";
                    Object object = null;
                    if (pt.isAnnotationPresent(Spi.class)){
                        object = getSpi(pt, applicationContext);
                    }else if (applicationContext.containsBean(property)){
                        object = applicationContext.getBean(property);
                        if (!pt.isInstance(object)){
                            continue;
                        }
                    }
                    if (object != null) {
                        method.invoke(spiInstance, object);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
