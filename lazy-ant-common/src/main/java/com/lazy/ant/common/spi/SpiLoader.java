package com.lazy.ant.common.spi;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p></p>
 *
 * @author laizhiyuan
 * @date 2018/7/18.
 */
public class SpiLoader {

    private Map<String, Object> spiInfoMap = new ConcurrentHashMap<>();
    private static String dir = "/META-INF/lazyAnt";

}
