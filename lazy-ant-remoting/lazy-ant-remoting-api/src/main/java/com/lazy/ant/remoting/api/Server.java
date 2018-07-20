package com.lazy.ant.remoting.api;

import com.lazy.ant.common.URL;

/**
 * <p></p>
 *
 * @author laizhiyuan
 * @date 2018/7/19.
 */
public interface Server {

    /**
     * 开启服务
     * @param url 参数
     */
    void openServer(URL url);

    /**
     * 关闭服务
     * @param url 参数
     */
    void closeServer(URL url);
}
