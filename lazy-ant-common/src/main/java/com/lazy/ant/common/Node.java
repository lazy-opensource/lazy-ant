package com.lazy.ant.common;

import java.net.URL;

/**
 * <p></p>
 *
 * @author laizhiyuan
 * @date 2018/7/20.
 */
public interface Node {

    /**
     * get url.
     *
     * @return url.
     */
    URL getUrl();

    /**
     * is available.
     *
     * @return available.
     */
    boolean isAvailable();

    /**
     * destroy.
     */
    void destroy();

}
