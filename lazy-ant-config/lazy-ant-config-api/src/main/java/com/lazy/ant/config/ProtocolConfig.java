package com.lazy.ant.config;

/**
 * <p>
 *     协议配置
 * </p>
 *
 * @author laizhiyuan
 * @date 2018/6/29.
 */
public class ProtocolConfig extends AbstractConfig {

    private String name;
    private String port;

    public String getName() {
        return name;
    }

    public ProtocolConfig setName(String name) {
        this.name = name;
        return this;
    }

    public String getPort() {
        return port;
    }

    public ProtocolConfig setPort(String port) {
        this.port = port;
        return this;
    }
}
