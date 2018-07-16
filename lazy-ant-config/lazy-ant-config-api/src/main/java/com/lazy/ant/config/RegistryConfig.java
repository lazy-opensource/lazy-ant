package com.lazy.ant.config;

/**
 * <p></p>
 *
 * @author laizhiyuan
 * @date 2018/6/29.
 */
public class RegistryConfig extends AbstractConfig {

    private String address;

    public String getAddress() {
        return address;
    }

    public RegistryConfig setAddress(String address) {
        this.address = address;
        return this;
    }
}
