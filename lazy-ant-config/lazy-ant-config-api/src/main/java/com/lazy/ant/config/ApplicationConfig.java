package com.lazy.ant.config;

/**
 * <p></p>
 *
 * @author laizhiyuan
 * @date 2018/6/29.
 */
public class ApplicationConfig extends AbstractConfig {

    private String name;

    public String getName() {
        return name;
    }

    public ApplicationConfig setName(String name) {
        this.name = name;
        return this;
    }
}
