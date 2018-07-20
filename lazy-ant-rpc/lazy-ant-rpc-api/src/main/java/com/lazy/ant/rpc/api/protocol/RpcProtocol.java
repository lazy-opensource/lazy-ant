package com.lazy.ant.rpc.api.protocol;


import com.lazy.ant.common.URL;

/**
 * <p></p>
 *
 * @author laizhiyuan
 * @date 2018/7/20.
 */
public interface RpcProtocol {

    /**
     * 暴露服务
     * @param url 参数
     */
    void export(URL url);

    /**
     * 应用服务
     * @param url 参数
     */
    void ref(URL url);
}
